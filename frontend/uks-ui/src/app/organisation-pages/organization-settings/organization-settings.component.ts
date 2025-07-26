import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { OrganisationService } from 'src/app/services/organisation/organisation.service';
import { UserService } from 'src/app/services/user/user.service';
import { OrganisationUpdateDTO } from 'src/app/shared/dto/organisation/organisation-update.dto';
import { OrganisationDTO } from 'src/app/shared/dto/organisation/organisation.dto';

@Component({
  selector: 'app-organization-settings',
  templateUrl: './organization-settings.component.html',
  styleUrls: ['./organization-settings.component.css'],
})
export class OrganizationSettingsComponent implements OnInit {
  organisation: OrganisationDTO = {
    id: 0,
    name: '',
    description: '',
    deactivated: false,
    ownerId: 0,
    image: null,
    members: [],
    teams: [],
  };

  description: string = '';
  image: string = '';

  constructor(
    private route: ActivatedRoute,
    private organisationService: OrganisationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const orgId = Number(params.get('id'));
      if (orgId) {
        this.organisationService.getOrganisationById(orgId).subscribe((org) => {
          this.organisation = org.data;
          this.description = this.organisation.description ?? '';
          this.image = this.organisation.image ?? '';
        });
      }
    });
  }

  updateOrganisation() {
    const dto: OrganisationUpdateDTO = {
      description: this.description,
      image: this.image,
      ownerId: this.organisation.ownerId,
    };

    this.organisationService
      .updateOrganisation(this.organisation.id as number, dto)
      .subscribe({
        next: (response) => {
          console.log('Updated successfully:', response.data);
        },
        error: (error) => {
          console.error('Update failed:', error);
          alert('Error: ' + error.error.message);
        },
      });
  }

  deactivateOrganisation() {
    this.organisationService
      .deactivateOrganisation(
        this.organisation.id as number,
        this.organisation.ownerId
      )
      .subscribe({
        next: () => {
          console.log('Organisation deactivated.');
          alert('Organisation deactivated');
          this.router.navigate(['dockerhub/organizations']);
        },
        error: (err) => {
          console.error('Failed to deactivate organisation', err);
          alert('Error: ' + err.error.message);
        },
      });
  }
}
