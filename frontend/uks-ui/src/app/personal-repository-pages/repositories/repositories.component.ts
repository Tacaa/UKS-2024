import { Component, Input, OnInit, Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import {
  OfficialRepositoryDTO,
  RepositoryDTO,
} from 'src/app/shared/dto/repository/repository.dto';

@Pipe({
  name: 'spaceToUnderscore',
})
export class SpaceToUnderscorePipe implements PipeTransform {
  transform(value: string | undefined | null): string {
    return value ? value.replace(/ /g, '_') : '';
  }
}

@Component({
  selector: 'app-repositories',
  templateUrl: './repositories.component.html',
  styleUrls: ['./repositories.component.css'],
})
export class RepositoriesComponent implements OnInit {
  @Input() organisationId?: number; // Optional input

  sortedRepos: RepositoryDTO[] = [];
  namespaces: string[] = [];
  loadedRepos?: number = 0;
  testRepository?: RepositoryDTO;
  searchTerm: string = '';

  sortField: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';
  officialRepoIds: Set<number> = new Set();

  constructor(
    private repositoryService: RepositoryService,
    private authService: AuthService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    const ownerId = this.organisationId
      ? null
      : (this.authService.getCurrentUser()?.id as number);

    if (this.organisationId) {
      this.repositoryService
        .getRepositoriesByOrganisation(this.organisationId)
        .subscribe((response) => {
          this.handleRepositoryResponse(response);
        });
    } else if (ownerId) {
      this.repositoryService
        .getRepositoriesByOwner(ownerId)
        .subscribe((response) => {
          this.handleRepositoryResponse(response);
        });

      // Load official repositories for this owner
      this.repositoryService.getOfficialRepByOwner(ownerId).subscribe((res) => {
        this.officialRepoIds = new Set(
          res.content.map(
            (official: OfficialRepositoryDTO) => official.repositoryDTO.id
          )
        );
      });
    }
  }

  isOfficial(repoId: number): boolean {
    return this.officialRepoIds.has(repoId);
  }

  private handleRepositoryResponse(response: {
    content: RepositoryDTO[];
    totalElements: number;
  }): void {
    this.sortedRepos = response.content;
    this.loadedRepos = response.totalElements;

    this.namespaces = [
      ...new Set(
        this.sortedRepos
          .map((repo) => repo.namespace)
          .filter((namespace) => namespace !== null && namespace !== undefined)
      ),
    ] as string[];
  }

  get filteredRepos(): RepositoryDTO[] {
    return this.sortedRepos.filter((repo) =>
      repo.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  sortTable(field: 'name' | 'updated') {
    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.sortedRepos.sort((a, b) => {
      let valueA: any;
      let valueB: any;

      if (field === 'updated') {
        valueA = new Date(a.updated ?? a.created);
        valueB = new Date(b.updated ?? b.created);
      } else {
        valueA = a[field];
        valueB = b[field];
      }

      if (valueA < valueB) return this.sortDirection === 'asc' ? -1 : 1;
      if (valueA > valueB) return this.sortDirection === 'asc' ? 1 : -1;
      return 0;
    });
  }
}
