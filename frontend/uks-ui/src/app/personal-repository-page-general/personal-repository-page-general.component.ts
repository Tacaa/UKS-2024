import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';
import { RepositoryService } from '../services/repository/repository.service';
import { UpdateRepositoryDTO } from 'src/app/shared/dto/repository/update-repository.dto';
import { Category } from '../shared/enum/Category';

@Component({
  selector: 'app-personal-repository-page-general',
  templateUrl: './personal-repository-page-general.component.html',
  styleUrls: ['./personal-repository-page-general.component.css'],
})
export class PersonalRepositoryPageGeneralComponent implements OnInit {
  repository?: RepositoryDTO;

  isEditingDescription = false;
  isEditingCategory = false;
  isEditingOverview = false;

  tempDescription = '';
  tempCategory = '';
  tempOverview = '';

  categories: string[] = [];

  constructor(
    private route: ActivatedRoute,
    private repositoryService: RepositoryService
  ) {}

  ngOnInit(): void {
    this.categories = Object.values(Category);

    this.route.parent?.paramMap.subscribe((params) => {
      const repoId = Number(params.get('id'));
      if (repoId) {
        this.repositoryService.getRepositoryById(repoId).subscribe((repo) => {
          this.repository = repo;
          this.tempDescription = repo.description || '';
          this.tempCategory = repo.category;
          this.tempOverview = ''; //! if overview is stored somewhere
        });
      }
    });
  }

  // Create full update DTO from current repository and override specific fields
  buildFullUpdateDTO(
    override: Partial<UpdateRepositoryDTO>
  ): UpdateRepositoryDTO {
    if (!this.repository) throw new Error('Repository is not loaded');

    return {
      namespace: this.repository.namespace || '',
      description: this.repository.description || '',
      visibility: this.repository.visibility,
      personal: this.repository.personal,
      category: this.repository.category,
      ...override,
    };
  }

  updateShortDescription() {
    if (!this.repository) return;

    const dto = this.buildFullUpdateDTO({
      description: this.tempDescription,
    });

    this.repositoryService.updateRepository(this.repository.id, dto).subscribe(
      (updatedRepo) => {
        this.repository = updatedRepo;
        this.isEditingDescription = false;
      },
      (err) => console.error('Failed to update description', err)
    );
  }

  updateCategory() {
    if (!this.repository) return;

    const dto = this.buildFullUpdateDTO({
      category: this.tempCategory as Category,
    });

    this.repositoryService.updateRepository(this.repository.id, dto).subscribe(
      (updatedRepo) => {
        this.repository = updatedRepo;
        this.isEditingCategory = false;
      },
      (err) => console.error('Failed to update category', err)
    );
  }

  updateOverview() {
    this.isEditingOverview = false;
  }

  toggleDescriptionEdit() {
    this.isEditingDescription = true;
    this.tempDescription = this.repository?.description || '';
  }

  cancelDescriptionEdit() {
    this.isEditingDescription = false;
  }

  toggleCategoryEdit() {
    this.isEditingCategory = true;
    this.tempCategory = this.repository?.category as string;
  }

  cancelCategoryEdit() {
    this.isEditingCategory = false;
  }

  toggleOverviewEdit() {
    this.isEditingOverview = true;
    this.tempOverview = ''; //! set if overview exists
  }

  cancelOverviewEdit() {
    this.isEditingOverview = false;
  }
}
