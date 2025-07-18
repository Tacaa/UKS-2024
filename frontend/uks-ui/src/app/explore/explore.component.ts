import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/mock-repository/repository.service';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css'],
})
export class ExploreComponent implements OnInit {
  categories?: string[] = [];

  constructor(private repositoryService: RepositoryService) {}
  ngOnInit(): void {
    this.categories = this.repositoryService.getAllCategories();
  }
}
