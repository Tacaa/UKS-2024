import { Component, Inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddMembersComponent } from '../dialogs/add-members/add-members.component';

@Component({
  selector: 'app-organization-members',
  templateUrl: './organization-members.component.html',
  styleUrls: ['./organization-members.component.css'],
})
export class OrganizationMembersComponent {
  constructor(private dialog: MatDialog) {}

  openDialog(): void {
    this.dialog.open(AddMembersComponent);
  }
}
