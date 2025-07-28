import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { RepositoryService } from '../services/repository/repository.service';
import { RepositoryDTO } from '../shared/dto/repository/repository.dto';

@Component({
  selector: 'app-repository-page-overview',
  templateUrl: './repository-page-overview.component.html',
  styleUrls: ['./repository-page-overview.component.css'],
})
export class RepositoryPageOverviewComponent implements OnInit {
  repositoryService = inject(RepositoryService);
  route = inject(ActivatedRoute);

  repository$?: Observable<RepositoryDTO>;
  copied = false;

  ngOnInit(): void {
    const repoId = Number(this.route.parent?.snapshot.paramMap.get('id'));
    this.repository$ = this.repositoryService.getRepositoryById(repoId);
  }

  copyToClipboard(text: string): void {
    text = `docker pull ${text}/build`;
    navigator.clipboard
      .writeText(text)
      .then(() => {
        this.copied = true;
        setTimeout(() => (this.copied = false), 2000);
      })
      .catch((err) => {
        console.error('Failed to copy: ', err);
      });
  }
}
