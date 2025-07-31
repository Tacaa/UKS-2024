import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth/auth.service';
import { OrganisationService } from 'src/app/services/organisation/organisation.service';
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/shared/models/user.model';

@Component({
  selector: 'app-add-members',
  templateUrl: './add-members.component.html',
  styleUrls: ['./add-members.component.css'],
})
export class AddMembersComponent implements OnInit {
  allUsers: User[] = [];
  filteredUsers: User[] = [];
  addedMembers: User[] = [];

  searchControl = new FormControl('');

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private dialogRef: MatDialogRef<AddMembersComponent>,
    private organisationService: OrganisationService,
    @Inject(MAT_DIALOG_DATA) public data: { orgId: number }
  ) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((users) => {
      this.allUsers = users;
      this.filteredUsers = users;

      this.searchControl.valueChanges.subscribe((searchText) => {
        const query = (searchText ?? '').toLowerCase();
        this.filteredUsers = this.allUsers.filter((user) =>
          user.username.toLowerCase().includes(query)
        );
      });
    });
  }

  setSearch(user: User): void {
    this.searchControl.setValue(user.username);
  }

  addMember(): void {
    const username = this.searchControl.value?.trim();
    if (!username) {
      alert('Please enter a valid username.');
      return;
    }

    const user = this.allUsers.find((u) => u.username === username);
    if (!user) {
      alert(`${username} does not exist.`);
      return;
    }

    if (this.addedMembers.find((m) => m.id === user.id)) {
      alert(`${username} is already added.`);
      return;
    }

    const currentUser = this.authService.getCurrentUser();
    if (!currentUser?.id) {
      alert('Unauthorized.');
      return;
    }

    this.organisationService
      .addMemberToOrganisation(
        this.data.orgId,
        currentUser.id,
        user.id as number
      )
      .subscribe({
        next: () => {
          this.addedMembers.push(user);
          alert(`${username} added successfully!`);
        },
        error: (err) => {
          alert(err.error?.message || 'Error adding user.');
        },
      });
  }
}
