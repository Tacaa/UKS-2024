import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { RepositoryService } from 'src/app/services/repository/repository.service'; // Updated import
import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';

@Component({
  selector: 'app-repositories',
  templateUrl: './repositories.component.html',
  styleUrls: ['./repositories.component.css'],
})
export class RepositoriesComponent implements OnInit {
  sortedRepos: RepositoryDTO[] = [];
  namespaces: string[] = [];
  loadedRepos?: number = 0;
  testRepository?: RepositoryDTO;
  searchTerm: string = '';

  sortField: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(
    private repositoryService: RepositoryService,
    private authService: AuthService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    const ownerId = this.authService.getCurrentUser()?.id as number;

    this.repositoryService
      .getRepositoriesByOwner(ownerId)
      .subscribe((response) => {
        this.sortedRepos = response.content;
        this.loadedRepos = response.totalElements;

        // Extract unique namespaces from the repositories
        this.namespaces = [
          ...new Set(
            this.sortedRepos
              .map((repo) => repo.namespace)
              .filter(
                (namespace) => namespace !== null && namespace !== undefined
              )
          ),
        ] as string[];
      });
  }

  get filteredRepos(): RepositoryDTO[] {
    return this.sortedRepos.filter((repo) =>
      repo.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  sortTable(field: 'name' | 'updated') {
    if (this.sortField === field) {
      // Toggle direction if sorting the same field
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      // Set new field and reset direction
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.sortedRepos.sort((a, b) => {
      let valueA: any;
      let valueB: any;

      if (field === 'updated') {
        // For "Last Pushed", prioritize `updated`, fallback to `created`
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
