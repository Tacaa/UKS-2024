import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Repository } from 'src/app/shared/models/mock.repository.model';
import { RepositoryService } from 'src/app/services/mock-repository/repository.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { StarService } from 'src/app/services/star/star.service';
import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  selectedOption: string = 'starred';
  name = this.authService.getCurrentUser();
  starredRepos: RepositoryDTO[] = [];
  displayedColumns: string[] = ['name', 'lastPushed', 'organisation'];

  constructor(
    private authService: AuthService,
    private starService: StarService
  ) {}

  ngOnInit(): void {
    const user = this.authService.getCurrentUser();
    const fullName = `${user?.firstName} ${user?.lastName}`;
    this.starService
      .getStarredRepositories(this.authService.getUserId() as number)
      .subscribe((repos) => {
        this.starredRepos = repos;
      });
  }
}
