import { Component } from '@angular/core';
import { Category } from 'src/app/model/repository';
import { RepositoryService } from 'src/app/services/repository/repository.service';
import { CreateOfficialRepositoryDTO } from 'src/app/shared/models/create-official-repository-model';

@Component({
  selector: 'app-create-official-repo',
  templateUrl: './create-official-repo.component.html',
  styleUrls: ['./create-official-repo.component.css'],
})
export class CreateOfficialRepoComponent {
  repositoryName: string = '';
  namespace: string = '';
  description: string = '';
  prefix: string = '';
  visibility: string = 'PUBLIC';
  personal: boolean = false;
  ownerId: number = 0;
  organisationId: number = 0;
  category: Category = Category.NONE;

  categories = Object.keys(Category).filter((key) => isNaN(Number(key)));

  constructor(private repositoryService: RepositoryService) {}

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
