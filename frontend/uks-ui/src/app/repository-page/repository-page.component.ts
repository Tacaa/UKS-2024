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
    });

    this.starService
      .getStarredRepositories(this.currentUserId)
      .subscribe((repos) => {
        this.starredRepoIds = repos.map((r) => r.id as number);
        this.isStarred = this.starredRepoIds.includes(this.repoId as number);
      });
  }

  starClick() {
    if (!this.repoId || !this.repository) return;

    if (this.isStarred) {
      this.starService.unstarRepository(this.repoId).subscribe(() => {
        this.isStarred = false;
        this.repository!.star -= 1;
      });
    } else {
      const starDTO: StarDTO = {
        userId: this.currentUserId,
        repositoryId: this.repoId,
      };
      this.starService.starRepository(this.repoId, starDTO).subscribe(() => {
        this.isStarred = true;
        this.repository!.star += 1;
      });
    }
  }
}
