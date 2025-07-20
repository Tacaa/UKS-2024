import { Component, OnInit } from '@angular/core';
import { OrganisationService } from '../services/organisation/organisation.service';
import { OrganisationDTO } from '../shared/dto/organisation/organisation.dto';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-orgs-page',
  templateUrl: './orgs-page.component.html',
  styleUrls: ['./orgs-page.component.css'],
})
export class OrgsPageComponent implements OnInit {
  organisations: OrganisationDTO[] = [];
  loading = true;

  constructor(
    private organisationService: OrganisationService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.loadOrganisations();
  }

  loadOrganisations() {
    const userId = this.authService.getCurrentUser();
    if (!userId || !userId.id) return;

    this.organisationService.getUserOrganisations(userId.id).subscribe({
      next: (response) => {
        this.organisations = response.data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading organisations:', error);
        this.loading = false;
      },
    });
  }
}
