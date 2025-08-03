import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import { RepositoryService } from '../services/repository/repository.service';
import { StarService } from '../services/star/star.service';
import { UserService } from '../services/user/user.service';
import {
  RepositoryDTO,
  OfficialRepositoryDTO,
} from '../shared/dto/repository/repository.dto';
import { User } from '../shared/models/user.model';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css'],
})
export class SearchpageComponent implements OnInit, OnDestroy {
  repos: RepositoryDTO[] = [];
  officialRepos: OfficialRepositoryDTO[] = [];
  starredRepos: RepositoryDTO[] = [];
  allUsers: User[] = [];
  searchTerm: string | null = null;
  private routeSubscription: Subscription = new Subscription();

  constructor(
    private repositoryService: RepositoryService,
    private userService: UserService,
    private starService: StarService,
    private authService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Subscribe to route parameter changes
    this.routeSubscription = this.route.paramMap.subscribe((params) => {
      this.searchTerm = params.get('name');
      this.loadRepositories();
    });

    if (this.authService.getUserId()) {
      this.starService
        .getStarredRepositories(this.authService.getUserId() as number)
        .subscribe((repos) => {
          this.starredRepos = repos;
        });
    }

    this.repositoryService
      .getAllPublicOfficialRepositories()
      .subscribe((repo) => {
        this.officialRepos = repo;
        console.log('PublicOfficialRepositories: ');
        console.log(repo);
      });

    this.loadAllUsers();
  }

  ngOnDestroy(): void {
    this.routeSubscription.unsubscribe();
  }

  private loadRepositories(): void {
    if (this.searchTerm) {
      // Search for repositories by name
      this.repositoryService
        .searchRepositories(this.searchTerm)
        .subscribe((response) => {
          // Filter only public repositories from search results
          const publicRepos = response.content.filter(
            (repo) => repo.visibility === 'PUBLIC' // Adjust this based on your Visibility enum
          );

          this.repos = publicRepos.map((r) => ({
            ...r,
            namespace: r.namespace ?? '',
          }));
        });
    } else {
      // Load all public repositories (default behavior)
      this.repositoryService.getAllPublicRepositories().subscribe((repo) => {
        const reposArray = Array.isArray(repo) ? repo : [repo];
        this.repos = reposArray.map((r) => ({
          ...r,
          namespace: r.namespace ?? '',
        }));
      });
    }
  }

  loadAllUsers(): void {
    this.userService.getAllUsers().subscribe((data) => {
      this.allUsers = data;
    });
  }

  isOfficialRepository(repoId: number): boolean {
    return this.officialRepos.some(
      (officialRepo) => officialRepo.repositoryDTO.id === repoId
    );
  }
}
