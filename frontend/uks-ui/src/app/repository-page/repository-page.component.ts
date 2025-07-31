import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { RepositoryService } from '../services/repository/repository.service';
import { RepositoryDTO } from '../shared/dto/repository/repository.dto';

@Component({
  selector: 'app-repository-page',
  templateUrl: './repository-page.component.html',
  styleUrls: ['./repository-page.component.css'],
})
export class RepositoryPageComponent implements OnInit {
  repoId: number | null = null;
  repository$?: Observable<RepositoryDTO>;

  constructor(
    private route: ActivatedRoute,
    private repositoryService: RepositoryService
  ) {}

  ngOnInit() {
    this.repoId = Number(this.route.snapshot.paramMap.get('id'));

    if (!this.repoId) return;

    this.repository$ = this.repositoryService.getRepositoryById(this.repoId);
  }
}
