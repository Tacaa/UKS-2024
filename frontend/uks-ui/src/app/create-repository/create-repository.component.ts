import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { Repository } from '../shared/models/Repository';

@Component({
  selector: 'app-create-repository',
  templateUrl: './create-repository.component.html',
  styleUrls: ['./create-repository.component.css'],
})
export class CreateRepositoryComponent implements OnInit {
  namespaces: string[] = [];

  constructor(private repositoryService: RepositoryService) {}

  ngOnInit(): void {
    this.namespaces = this.repositoryService.getAllNamespaces();
  }
}
