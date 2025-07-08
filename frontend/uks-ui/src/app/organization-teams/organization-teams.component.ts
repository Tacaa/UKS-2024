import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateTeamComponent } from '../dialogs/create-team/create-team.component';

@Component({
  selector: 'app-organization-teams',
  templateUrl: './organization-teams.component.html',
  styleUrls: ['./organization-teams.component.css'],
})
export class OrganizationTeamsComponent {
  constructor(private dialog: MatDialog) {}

  openDialog(): void {
    this.dialog.open(CreateTeamComponent);
  }
}
