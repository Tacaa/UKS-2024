import { Component, OnInit } from '@angular/core';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { Repository } from 'src/app/shared/models/Repository';

@Component({
  selector: 'app-repositories',
  templateUrl: './repositories.component.html',
  styleUrls: ['./repositories.component.css'],
})
export class RepositoriesComponent implements OnInit {
  repos: Repository[] = [];
  namespaces: string[] = [];

  constructor(private repositoryService: RepositoryService) {}

  ngOnInit(): void {
    this.repos = this.repositoryService.getAll();
    this.namespaces = this.repositoryService.getAllNamespaces();
  }
}
