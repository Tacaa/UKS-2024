import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { Repository } from '../shared/models/Repository';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css'],
})
export class ExploreComponent implements OnInit {
  categories?: string[] = [];
  repos: Repository[] = [];

  constructor(private repositoryService: RepositoryService) {}
  ngOnInit(): void {
    this.categories = this.repositoryService.getAllCategories();
    this.repos = this.repositoryService.getAllRepositories();
  }
}
