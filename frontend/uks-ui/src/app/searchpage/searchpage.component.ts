import { Component, OnInit } from '@angular/core';
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
export class SearchpageComponent implements OnInit {
  repos: RepositoryDTO[] = [];
  officialRepos: OfficialRepositoryDTO[] = [];
  starredRepos: RepositoryDTO[] = [];
  allUsers: User[] = [];

  constructor(
    private repositoryService: RepositoryService,
    private userService: UserService,
    private starService: StarService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    if (this.authService.getUserId()) {
      this.starService
        .getStarredRepositories(this.authService.getUserId() as number)
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

  isOfficialRepository(repoId: number): boolean {
    return this.officialRepos.some(
      (officialRepo) => officialRepo.repositoryDTO.id === repoId
    );
  }
}
