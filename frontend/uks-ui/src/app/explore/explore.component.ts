import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { Observable } from 'rxjs';
import { RepositoryDTO } from '../shared/dto/repository/repository.dto';
import { UserService } from '../services/user/user.service';
import { User } from '../shared/models/user.model';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css'],
})
export class ExploreComponent implements OnInit {
  repos: RepositoryDTO[] = [];
  categories?: string[] = [];
  selectedCategory: string | null = null;
  allUsers: User[] = [];

  constructor(
    private repositoryService: RepositoryService,
    private userService: UserService
  ) {}
  ngOnInit(): void {
    this.repositoryService.getAllRepositories().subscribe((repo) => {
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
      ];

      console.log(this.categories);
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
}
