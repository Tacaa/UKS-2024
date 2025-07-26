import { Component, OnInit } from '@angular/core';
import { Repository } from '../shared/models/mock.repository.model';
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
    this.repositoryService.getAllRepositories().subscribe((repo) => {
      const reposArray = Array.isArray(repo) ? repo : [repo];
      this.repos = reposArray.map(r => ({
        ...r,
        namespace: r.namespace ?? ''
      }));
    });
  }
}
