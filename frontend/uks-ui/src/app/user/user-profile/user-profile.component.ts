import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Repository } from 'src/app/shared/models/mock.repository.model';
import { RepositoryService } from 'src/app/services/mock-repository/repository.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  name: string = 'ImeKorisnika';
  selectedOption: string = 'repositories';
  repositories: Repository[] = [];
  filteredRepos: Repository[] = [];
  sortedRepos: Repository[] = [];
  namespaces: string[] = [];
  loadedRepos?: number;
  testRepository?: Repository;

  sortField: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(
    private repositoryService: RepositoryService,
    private router: Router,
    private sanitizer: DomSanitizer,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const user = this.authService.getCurrentUser();
    const fullName = `${user?.firstName} ${user?.lastName}`;
    this.name = fullName;
    this.repositories = this.repositoryService.getAllRepositories(); // Initialize with unsorted data
    this.loadedRepos = this.repositories.length;
    this.namespaces = this.repositoryService.getAllNamespaces();
    this.showAll();
  }

  showAll() {
    this.filteredRepos = this.repositories;
  }

  showFavorites() {
    //implementorati da li je u omiljenim
    this.filteredRepos = this.repositories.filter((repo) => repo.star > 33);
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

    this.filteredRepos.sort((a, b) => {
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
    // const url = `https://your-repository-domain.com/${repoName}`;
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
