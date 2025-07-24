import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrganisationDTO } from '../../shared/dto/organisation/organisation.dto';
import { OrganisationService } from '../../services/organisation/organisation.service';
import { UserService } from '../../services/user/user.service';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css'],
})
export class OrganizationComponent implements OnInit {
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

  owner?: string;
  isOwner: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private organisationService: OrganisationService,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const orgId = Number(params.get('id'));
      if (orgId) {
        this.organisationService.getOrganisationById(orgId).subscribe((org) => {
          this.organisation = org.data;
          this.userService.getUserById(org.data.ownerId).subscribe((res) => {
            const currentUser = this.authService.getCurrentUser();
            const user = res;
            this.owner = res.username;
            this.isOwner = currentUser?.id === this.organisation.ownerId;
          });
        });
      }
    });
  }

  isActive(route: string): boolean {
    return this.router.url === route;
  }
}
