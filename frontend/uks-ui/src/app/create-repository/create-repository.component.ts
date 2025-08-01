import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { Category } from '../model/repository';
import { AuthService } from '../services/auth/auth.service';
import { CreateRepositoryDTO } from '../shared/dto/repository/create-repository.dto';
import { OrganisationDTO } from '../shared/dto/organisation/organisation.dto';
import { OrganisationService } from '../services/organisation/organisation.service';

@Component({
  selector: 'app-create-repository',
  templateUrl: './create-repository.component.html',
  styleUrls: ['./create-repository.component.css'],
})
export class CreateRepositoryComponent implements OnInit {
  repositoryName: string = '';
  namespace: string = '';
  description: string = '';
  visibility: string = 'PUBLIC';
  personal: boolean = false;
  ownerId: number = this.authService.getUserId() as number;
  organisationId: number | null = null;
  category: Category = Category.NONE;

  userOrganisations: OrganisationDTO[] = [];

  categories = Object.keys(Category).filter((key) => isNaN(Number(key)));

  constructor(
    private organisationService: OrganisationService,
    private repositoryService: RepositoryService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    if (userId) {
      this.organisationService.getUserOrganisations(userId).subscribe({
        next: (res) => {
          this.userOrganisations = res.data;
        },
        error: (err) => {
          console.error('Error loading organisations:', err);
        },
      });
    }
  }

  onCreateRepository() {
    const dto: CreateRepositoryDTO = {
      name: this.repositoryName,
      namespace: this.namespace,
      description: this.description,
      visibility: this.visibility,
      personal: this.personal,
      ownerId: this.ownerId,
      organisationId: this.organisationId,
      category: this.category,
    };

    this.repositoryService.createRepository(dto).subscribe({
      next: (response) => {
        console.log('Repository created successfully:', response);
        alert('Repository created successfully!');
      },
      error: (error) => {
        console.error('Error creating repository:', error);
        alert('Error: ' + error.error.message);
      },
    });
  }
}
