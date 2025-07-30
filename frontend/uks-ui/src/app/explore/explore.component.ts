import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { Observable } from 'rxjs';
import {
  OfficialRepositoryDTO,
  RepositoryDTO,
} from '../shared/dto/repository/repository.dto';
import { UserService } from '../services/user/user.service';
import { User } from '../shared/models/user.model';
import { StarService } from '../services/star/star.service';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css'],
})
export class ExploreComponent implements OnInit {
  repos: RepositoryDTO[] = [];
  officialRepos: OfficialRepositoryDTO[] = [];
  starredRepos: RepositoryDTO[] = [];
  categories?: string[] = [];
  selectedCategory: string | null = null;
  allUsers: User[] = [];

  constructor(
    private repositoryService: RepositoryService,
    private userService: UserService,
    private starService: StarService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    if (this.authService.getCurrentUser()) {
      this.starService
        .getStarredRepositories(this.authService.getCurrentUser()?.id as number)
        .subscribe((repos) => {
          this.starredRepos = repos;
        });
    }

    this.repositoryService.getAllPublicRepositories().subscribe((repo) => {
      const reposArray = Array.isArray(repo) ? repo : [repo];
      this.repos = reposArray.map((r) => ({
        ...r,
        namespace: r.namespace ?? '',
      }));

      this.categories = [
        ...new Set(
          this.repos
            ?.map((repo) => repo.categoryString || 'NONE')
            .filter((cat): cat is string => !!cat)
        ),
        'Official',
      ];

      console.log(this.categories);
    });
    this.repositoryService
      .getAllPublicOfficialRepositories()
      .subscribe((repo) => {
        this.officialRepos = repo;
        console.log('PublicOfficialRepositories: ');
        console.log(repo);
      });
    this.loadAllUsers();
  }

  loadAllUsers(): void {
    this.userService.getAllUsers().subscribe((data) => {
      this.allUsers = data;
    });
  }

  onCategoryClick(category: string): void {
    this.selectedCategory = category;
  }

  seeAllRepos() {
    this.selectedCategory = null;
  }
}
