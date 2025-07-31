import { Component, OnInit } from '@angular/core';
import { OrganisationService } from '../../services/organisation/organisation.service';
import { OrganisationDTO } from '../../shared/dto/organisation/organisation.dto';
import { AuthService } from '../../services/auth/auth.service';
import { UserService } from '../../services/user/user.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-orgs-page',
  templateUrl: './orgs-page.component.html',
  styleUrls: ['./orgs-page.component.css'],
})
export class OrgsPageComponent implements OnInit {
  organisations: OrganisationDTO[] = [];
  loading = true;
  organisationsWithOwners: { org: OrganisationDTO; ownerUsername: string }[] =
    [];

  constructor(
    private organisationService: OrganisationService,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.loadOrganisations();
  }

  loadOrganisations() {
    const userId = this.authService.getCurrentUser();
    if (!userId || !userId.id) return;

    this.organisationService.getUserOrganisations(userId.id).subscribe({
      next: (response) => {
        const orgs = response.data;

        // Prepare parallel requests for owner usernames
        const ownerCalls = orgs.map((org) =>
          this.userService.getUserById(org.ownerId)
        );

        forkJoin(ownerCalls).subscribe((ownerResponses) => {
          const currentUserId = userId.id;

          this.organisationsWithOwners = orgs.map((org, i) => {
            const owner = ownerResponses[i];
            const isCurrentUserOwner = owner.id === currentUserId;

            return {
              org,
              ownerUsername: isCurrentUserOwner ? 'YOU' : owner.username,
            };
          });

          this.loading = false;
        });
      },
      error: (error) => {
        console.error('Error loading organisations:', error);
        this.loading = false;
      },
    });
  }
}
