import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/repository';
import { AuthService } from 'src/app/services/auth/auth.service';
import { OrganisationService } from 'src/app/services/organisation/organisation.service';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { OrganisationDTO } from 'src/app/shared/dto/organisation/organisation.dto';
import { CreateOfficialRepositoryDTO } from 'src/app/shared/dto/repository/create-repository.dto';

@Component({
  selector: 'app-create-official-repo',
  templateUrl: './create-official-repo.component.html',
  styleUrls: ['./create-official-repo.component.css'],
})
export class CreateOfficialRepoComponent implements OnInit {
  repositoryName: string = '';
  namespace: string = '';
  description: string = '';
  prefix: string = '';
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
    const dto: CreateOfficialRepositoryDTO = {
      createRepositoryDTO: {
        name: this.repositoryName,
        namespace: this.namespace,
        description: this.description,
        visibility: this.visibility,
        personal: this.personal,
        ownerId: this.ownerId,
        organisationId: this.organisationId,
        category: this.category,
      },
      prefix: this.prefix,
    };

    this.repositoryService.createOfficialRepository(dto).subscribe({
      next: (response) => {
        console.log('Repository created successfully:', response);
        alert('Official repo created succsesfully');
      },
      error: (error) => {
        console.error('Error creating repository:', error);
        alert('Error: ' + error.error.message);
      },
    });
  }
}
