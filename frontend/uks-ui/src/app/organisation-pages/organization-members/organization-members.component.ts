import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddMembersComponent } from '../../dialogs/add-members/add-members.component';
import { ActivatedRoute } from '@angular/router';
import { OrganisationDTO } from '../../shared/dto/organisation/organisation.dto';
import { OrganisationService } from '../../services/organisation/organisation.service';
import { UserService } from '../../services/user/user.service';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-organization-members',
  templateUrl: './organization-members.component.html',
  styleUrls: ['./organization-members.component.css'],
})
export class OrganizationMembersComponent implements OnInit {
  orgId?: number;

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

  members: { user: any; roleEnum: string }[] = [];
  isOwner: boolean = false;

  constructor(
    private dialog: MatDialog,
    private route: ActivatedRoute,
    private organisationService: OrganisationService,
    private userService: UserService,
    private authService: AuthService
  ) {}

  openDialog(): void {
    this.dialog.open(AddMembersComponent, {
      data: { orgId: this.organisation.id },
    });
  }

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const orgId = Number(params.get('id'));
      if (orgId) {
        this.organisationService.getOrganisationById(orgId).subscribe((org) => {
          this.organisation = org.data;

          const currentUser = this.authService.getCurrentUser();
          this.isOwner = currentUser?.id === this.organisation.ownerId;

          this.members = [];
          this.organisation.members.forEach((memberId) => {
            this.userService.getUserById(memberId).subscribe((res) => {
              const user = res;
              const roleEnum =
                user.id === this.organisation.ownerId ? 'Owner' : 'Member';
              this.members.push({ user, roleEnum });
            });
          });
        });
      }
    });
  }
}
