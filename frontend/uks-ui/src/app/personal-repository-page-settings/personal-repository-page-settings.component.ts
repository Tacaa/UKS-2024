import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RepositoryService } from '../services/repository/repository.service';
import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';
import { UpdateRepositoryDTO } from 'src/app/shared/dto/repository/update-repository.dto';
import { Visibility } from '../shared/enum/Visibility';

@Component({
  selector: 'app-personal-repository-page-settings',
  templateUrl: './personal-repository-page-settings.component.html',
  styleUrls: ['./personal-repository-page-settings.component.css'],
})
export class PersonalRepositoryPageSettingsComponent implements OnInit {
  private repoId = 0;
  repository?: RepositoryDTO;
  visibilityEnum = Visibility; // So we can use it in the template

  constructor(
    private route: ActivatedRoute,
    private repositoryService: RepositoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      this.repoId = Number(params.get('id'));
      if (this.repoId) {
        this.repositoryService
          .getRepositoryById(this.repoId)
          .subscribe((repo) => {
            this.repository = repo;
          });
      }
    });
  }

  deleteRepository() {
    this.repositoryService.deleteRepository(this.repoId).subscribe({
      next: () => {
        this.router.navigate(['']);
      },
      error: (error) => {
        console.error('Failed to delete repository:', error);
      },
    });
  }

  toggleVisibility() {
    if (!this.repository) return;

    const newVisibility =
      this.repository.visibility === Visibility.PUBLIC
        ? Visibility.PRIVATE
        : Visibility.PUBLIC;

    const dto: UpdateRepositoryDTO = {
      namespace: this.repository.namespace || '',
      description: this.repository.description || '',
      visibility: newVisibility,
      personal: this.repository.personal,
      category: this.repository.category,
    };

    this.repositoryService.updateRepository(this.repository.id, dto).subscribe({
      next: (updatedRepo) => {
        this.repository = updatedRepo;
      },
      error: (err) => console.error('Failed to change visibility', err),
    });
  }
}
