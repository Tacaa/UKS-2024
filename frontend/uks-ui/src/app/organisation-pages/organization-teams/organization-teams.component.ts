import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateTeamComponent } from '../../dialogs/create-team/create-team.component';
import { OrganisationDTO } from '../../shared/dto/organisation/organisation.dto';
import { TeamResponse, TeamService } from '../../services/team/team.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { OrganisationService } from '../../services/organisation/organisation.service';
import { UserService } from '../../services/user/user.service';
import { TeamDTO } from '../../shared/dto/team/team.dto';

@Component({
  selector: 'app-organization-teams',
  templateUrl: './organization-teams.component.html',
  styleUrls: ['./organization-teams.component.css'],
})
export class OrganizationTeamsComponent implements OnInit {
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

  isOwner: boolean = false;

  teams: TeamDTO[] = [];

  constructor(
    private dialog: MatDialog,
    private route: ActivatedRoute,
    private organisationService: OrganisationService,
    private userService: UserService,
    private authService: AuthService,
    private teamService: TeamService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const orgId = Number(params.get('id'));
      if (orgId) {
        this.organisationService.getOrganisationById(orgId).subscribe((org) => {
          this.organisation = org.data;

          const currentUser = this.authService.getCurrentUser();
          this.isOwner = currentUser?.id === this.organisation.ownerId;

          this.teamService
            .getOrganisationTeams(
              org.data.id as number,
              currentUser?.id as number
            )
            .subscribe((data) => (this.teams = data.data as TeamDTO[]));
        });
      }
    });
  }

  openDialog(): void {
    this.dialog.open(CreateTeamComponent, {
      data: {
        ownerId: this.organisation.ownerId,
        organisationId: this.organisation.id,
      },
    });
  }
}
