import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { Repository } from 'src/app/shared/models/repository.model';

@Component({
  selector: 'app-repositories',
  templateUrl: './repositories.component.html',
  styleUrls: ['./repositories.component.css'],
})
export class RepositoriesComponent implements OnInit {
  sortedRepos: Repository[] = [];
  namespaces: string[] = [];
  loadedRepos?: number;
  testRepository?: Repository;

  sortField: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(
    private repositoryService: RepositoryService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.sortedRepos = this.repositoryService.getAllRepositories(); // Initialize with unsorted data
    this.loadedRepos = this.sortedRepos.length;

    this.namespaces = this.repositoryService.getAllNamespaces();
  }

  sortTable(field: 'name' | 'updated') {
    if (this.sortField === field) {
      // Toggle direction if sorting the same field
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      // Set new field and reset direction
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.sortedRepos.sort((a, b) => {
      let valueA: any;
      let valueB: any;

      if (field === 'updated') {
        // For "Last Pushed", prioritize `updated`, fallback to `created`
        valueA = a.updated ?? a.created;
        valueB = b.updated ?? b.created;
      } else {
        valueA = a[field];
        valueB = b[field];
      }

      if (valueA < valueB) return this.sortDirection === 'asc' ? -1 : 1;
      if (valueA > valueB) return this.sortDirection === 'asc' ? 1 : -1;
      return 0;
    });
  }

  openRepo(repoName: string): void {
    // const url = `https://your-repository-domain.com/${repoName}`; // Replace with your repo URL logic
    // window.open(url, '_blank');
    console.log('Redirect to ' + repoName);
  }

  getRepository() {
    var id = 1;
    this.repositoryService.getRepository(id).subscribe((result: any) => {
      if (result != null) {
        this.testRepository = result;
        console.log(this.testRepository?.id);
      }
    });
  }
}
