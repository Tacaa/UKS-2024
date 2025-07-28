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

  constructor(private repositoryService: RepositoryService) {}

  ngOnInit(): void {
    // Removed duplicate API call - now using the repositories input from parent
  }

  get filteredRepositories(): RepositoryDTO[] {
    if (!this.category || this.category.trim() === '') {
      return this.repositories;
    }
    return this.repositories.filter(
      (repo) => repo.categoryString === this.category
    );
  }
}
