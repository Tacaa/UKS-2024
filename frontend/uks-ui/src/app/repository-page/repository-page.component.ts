import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { RepositoryService } from '../services/repository/repository.service';
import { RepositoryDTO } from '../shared/dto/repository/repository.dto';
import { AuthService } from '../services/auth/auth.service';
import { StarService } from '../services/star/star.service';
import { StarDTO } from '../shared/dto/star/star.dto';

@Component({
  selector: 'app-repository-page',
  templateUrl: './repository-page.component.html',
  styleUrls: ['./repository-page.component.css'],
})
export class RepositoryPageComponent implements OnInit {
  repoId: number | null = null;
  repository?: RepositoryDTO;
  starredRepoIds: number[] = [];
  isStarred: boolean = false;
  currentUserId: number;
  stars: number = 0;

  constructor(
    private route: ActivatedRoute,
    private repositoryService: RepositoryService,
    private authService: AuthService,
    private starService: StarService
  ) {
    this.currentUserId = this.authService.getCurrentUser()?.id as number;
  }

  ngOnInit() {
    this.repoId = Number(this.route.snapshot.paramMap.get('id'));
    if (!this.repoId) return;

    this.repositoryService.getRepositoryById(this.repoId).subscribe((repo) => {
      this.repository = repo;
      this.starService.countStars(repo.id).subscribe((count) => {
        this.stars = count;
        console.log(count);
      });
    });

    this.starService
      .getStarredRepositories(this.currentUserId)
      .subscribe((repos) => {
        this.starredRepoIds = repos.map((r) => r.id as number);
        this.isStarred = this.starredRepoIds.includes(this.repoId as number);
      });
  }

  starClick() {
    if (!this.repoId || !this.repository) {
      console.error('Repository ID or repository data is missing');
      return;
    }

    if (this.isStarred) {
      const starDTO: StarDTO = {
        userId: this.currentUserId,
        repositoryId: this.repoId,
      };

      this.starService.unstarRepository(starDTO).subscribe({
        next: () => {
          this.isStarred = false;
          this.stars -= 1;
        },
        error: (error) => {
          console.error('Failed to unstar repository:', error);
          alert('Failed to unstar repository: ' + error.error.message);
        },
      });
    } else {
      const starDTO: StarDTO = {
        userId: this.currentUserId,
        repositoryId: this.repoId,
      };

      this.starService.starRepository(starDTO).subscribe({
        next: () => {
          this.isStarred = true;
          this.stars += 1;
        },
        error: (error) => {
          console.error('Failed to star repository:', error);
          if (error.error.message == undefined) {
            alert('Failed to star repository: ' + error.error.error);
          } else {
            alert('Failed to star repository: ' + error.error.message);
          }
        },
      });
    }
  }
}
