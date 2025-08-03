import {
  Component,
  Input,
  OnInit,
  OnDestroy,
  Pipe,
  PipeTransform,
} from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
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
export class RepositoriesComponent implements OnInit, OnDestroy {
  @Input() organisationId?: number; // Optional input

  sortedRepos: RepositoryDTO[] = [];
  namespaces: string[] = [];
  loadedRepos?: number = 0;
  testRepository?: RepositoryDTO;
  searchTerm: string = '';

  sortField: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';
  officialRepoIds: Set<number> = new Set();

  private userSubscription: Subscription | null = null;

  constructor(
    private repositoryService: RepositoryService,
    private authService: AuthService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    console.log(
      '🔍 RepositoriesComponent ngOnInit - organisationId:',
      this.organisationId
    );

    if (this.organisationId) {
      // If we have organisationId, load organization repos immediately
      console.log(
        '📂 Loading repositories for organisation:',
        this.organisationId
      );
      this.loadOrganisationRepositories();
    } else {
      // If no organisationId, we need to wait for user data
      console.log('👤 Waiting for user data to load owner repositories...');
      this.subscribeToUserData();
    }
  }

  ngOnDestroy(): void {
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }

  private subscribeToUserData(): void {
    // Subscribe to user changes
    this.userSubscription = this.authService.currentUser$.subscribe((user) => {
      console.log('👤 User data received:', user);

      if (user && user.id) {
        console.log('📂 Loading repositories for owner:', user.id);
        this.loadOwnerRepositories(user.id);
      } else {
        console.log('❌ No user data available');
      }
    });

    // Ensure user restoration is triggered
    console.log('🔄 Triggering user restoration...');
    this.authService.restoreUser();
  }

  private loadOrganisationRepositories(): void {
    if (!this.organisationId) return;

    this.repositoryService
      .getRepositoriesByOrganisation(this.organisationId)
      .subscribe((response) => {
        console.log('✅ Organisation repositories loaded:', response);
        this.handleRepositoryResponse(response);
      });
  }

  private loadOwnerRepositories(ownerId: number): void {
    console.log('📂 Loading repositories for owner ID:', ownerId);

    // Load owner repositories
    this.repositoryService
      .getRepositoriesByOwner(ownerId)
      .subscribe((response) => {
        console.log('✅ Owner repositories loaded:', response);
        this.handleRepositoryResponse(response);
      });

    // Load official repositories for this owner
    this.repositoryService.getOfficialRepByOwner(ownerId).subscribe((res) => {
      console.log('✅ Official repositories loaded:', res);
      this.officialRepoIds = new Set(
        res.content.map(
          (official: OfficialRepositoryDTO) => official.repositoryDTO.id
        )
      );
      console.log('🏆 Official repo IDs:', this.officialRepoIds);
    });
  }

  isOfficial(repoId: number): boolean {
    return this.officialRepoIds.has(repoId);
  }

  private handleRepositoryResponse(response: {
    content: RepositoryDTO[];
    totalElements: number;
  }): void {
    console.log('📊 Processing repository response:', response);

    this.sortedRepos = response.content;
    this.loadedRepos = response.totalElements;

    this.namespaces = [
      ...new Set(
        this.sortedRepos
          .map((repo) => repo.namespace)
          .filter((namespace) => namespace !== null && namespace !== undefined)
      ),
    ] as string[];

    console.log('📈 Final state:', {
      reposCount: this.sortedRepos.length,
      totalElements: this.loadedRepos,
      namespaces: this.namespaces,
    });
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
