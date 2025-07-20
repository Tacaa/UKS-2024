import { Component } from '@angular/core';
import { OrganisationService } from '../services/organisation/organisation.service';
import { OrganisationCreateDTO } from '../shared/dto/organisation/organisation-create.dto';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orgs-page-create-org',
  templateUrl: './orgs-page-create-org.component.html',
  styleUrls: ['./orgs-page-create-org.component.css'],
})
export class OrgsPageCreateOrgComponent {
  name = '';
  description = '';
  image = '';

  constructor(
    private organisationService: OrganisationService,
    private authService: AuthService,
    private router: Router
  ) {}

  createOrganisation() {
    const currentUser = this.authService.getCurrentUser();
    if (!currentUser || !currentUser.id) return;

    const dto: OrganisationCreateDTO = {
      name: this.name,
      description: this.description,
      image: this.image,
      ownerId: currentUser.id,
    };

    this.organisationService.createOrganisation(dto).subscribe({
      next: (response) => {
        alert('Organisation created');
      },
      error: (error) => {
        console.error('Error creating organisation:', error);
        alert('Error: ' + error.error.message);
      },
    });
  }
}
