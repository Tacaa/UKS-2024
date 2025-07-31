import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';

@Component({
  selector: 'app-personal-repository-page-general',
  templateUrl: './personal-repository-page-general.component.html',
  styleUrls: ['./personal-repository-page-general.component.css'],
})
export class PersonalRepositoryPageGeneralComponent implements OnInit {
  isEditingDescription = false;
  shortDescription = 'shortDescription';
  tempDescription = this.shortDescription;

  isEditingCategory = false;
  repoCategory = this.repositoryService.getAllCategories().splice(3, 1); // Original category
  tempCategory = this.repoCategory; // Temporary value for editing
  categories: string[] = [];

  isEditingOverview = false;
  overview = ``;
  tempOverview = this.overview; // Temporary value for editing

  constructor(private repositoryService: RepositoryService) {}

  ngOnInit(): void {
    this.categories = this.repositoryService.getAllCategories();
  }

  toggleDescriptionEdit() {
    this.isEditingDescription = true;
    this.tempDescription = this.shortDescription;
  }

  cancelDescriptionEdit() {
    this.isEditingDescription = false;
    this.tempDescription = this.shortDescription;
  }

  updateShortDescription() {
    this.shortDescription = this.tempDescription;
    console.log(this.shortDescription);
    this.isEditingDescription = false;
  }

  toggleCategoryEdit() {
    this.isEditingCategory = true;
    this.tempCategory = this.repoCategory; // Copy original category for editing
  }

  cancelCategoryEdit() {
    this.isEditingCategory = false;
    this.tempCategory = this.repoCategory; // Revert changes
  }

  updateCategory() {
    this.repoCategory = this.tempCategory; // Commit changes
    console.log(this.repoCategory);
    this.isEditingCategory = false;
  }

  toggleOverviewEdit() {
    this.isEditingOverview = true;
    this.tempOverview = this.overview; // Copy original overview for editing
  }

  cancelOverviewEdit() {
    this.isEditingOverview = false;
    this.tempOverview = this.overview; // Revert changes
  }

  updateOverview() {
    this.overview = this.tempOverview; // Commit changes
    console.log(this.overview);
    this.isEditingOverview = false;
  }
}
