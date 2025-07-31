import { Component, OnInit } from '@angular/core';
import { Repository } from '../shared/models/Repository';
import { RepositoryService } from '../services/repository/repository.service';

@Component({
  selector: 'app-cardbar',
  templateUrl: './cardbar.component.html',
  styleUrls: ['./cardbar.component.css'],
})
export class CardbarComponent implements OnInit {
  repos: Repository[] = [];

  constructor(private repositoryService: RepositoryService) {}
  ngOnInit(): void {
    this.repos = this.repositoryService.getAllRepositories();
  }
}
