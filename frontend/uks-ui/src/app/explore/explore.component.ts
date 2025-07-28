import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { Observable } from 'rxjs';
import { RepositoryDTO } from '../shared/dto/repository/repository.dto';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css'],
})
export class ExploreComponent implements OnInit {
  repos: RepositoryDTO[] = [];
  categories?: string[] = [];
  selectedCategory: string | null = null;

  constructor(private repositoryService: RepositoryService) {}

  ngOnInit(): void {
    this.repositoryService.getAllRepositories().subscribe((repo) => {
      const reposArray = Array.isArray(repo) ? repo : [repo];
      this.repos = reposArray.map((r) => ({
        ...r,
        namespace: r.namespace ?? '',
      }));
      this.categories = [
        ...new Set(
          this.repos
            ?.map((repo) => repo.categoryString)
            .filter((cat): cat is string => !!cat) // filters out undefined, null, empty string
        ),
      ];
      console.log(this.categories);
    });
  }

  onCategoryClick(category: string): void {
    this.selectedCategory = category;
  }
}
