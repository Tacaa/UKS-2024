import { Component, OnInit } from '@angular/core';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { UserService } from 'src/app/services/user/user.service';
import {
  RepositoryDTO,
  OfficialRepositoryDTO,
} from 'src/app/shared/dto/repository/repository.dto';
import { User } from 'src/app/shared/models/user.model';

@Component({
  selector: 'app-official-repositories',
  templateUrl: './official-repositories.component.html',
  styleUrls: ['./official-repositories.component.css'],
})
export class OfficialRepositoriesComponent implements OnInit {
  repos: RepositoryDTO[] = [];
  officialRepos: OfficialRepositoryDTO[] = [];
  categories?: string[] = [];
  selectedCategory = 'Official';
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
        'Official',
      ];

      console.log(this.categories);
    });
    this.repositoryService.getAllOfficialRepositories().subscribe((repo) => {
      this.officialRepos = repo;
      console.log(repo);
    });
    this.loadAllUsers();
  }

  loadAllUsers(): void {
    this.userService.getAllUsers().subscribe((data) => {
      this.allUsers = data;
    });
  }
}
