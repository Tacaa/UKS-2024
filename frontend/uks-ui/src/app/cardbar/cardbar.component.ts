import { Component, Input, OnInit } from '@angular/core';
import { Repository } from '../shared/models/mock.repository.model';
import { RepositoryService } from '../services/repository/repository.service';
import { RepositoryDTO } from '../shared/dto/repository/repository.dto';

@Component({
  selector: 'app-cardbar',
  templateUrl: './cardbar.component.html',
  styleUrls: ['./cardbar.component.css'],
})
export class CardbarComponent implements OnInit {
  @Input() category: string | null = null;
  @Input() repositories: RepositoryDTO[] = [];
  repos: RepositoryDTO[] = [];

  constructor(private repositoryService: RepositoryService) {}
  ngOnInit(): void {
    this.repositoryService.getAllRepositories().subscribe((repo) => {
      const reposArray = Array.isArray(repo) ? repo : [repo];
      this.repos = reposArray.map((r) => ({
        ...r,
        namespace: r.namespace ?? '',
      }));
    });
  }

  get filteredRepositories(): RepositoryDTO[] {
    if (!this.category || this.category.trim() === '') {
      return this.repos;
    }
    return this.repos.filter((repo) => repo.categoryString === this.category);
  }
}
