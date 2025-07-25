import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TeamService } from 'src/app/services/team/team.service';
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/shared/models/user.model';

@Component({
  selector: 'app-add-members-to-team',
  templateUrl: './add-members-to-team.component.html',
  styleUrls: ['./add-members-to-team.component.css'],
})
export class AddMembersToTeamComponent implements OnInit {
  orgMembers: number[] = [];
  teamMembers: number[] = [];
  usersInOrg: User[] = [];
  teamId: number = 0;

  constructor(
    private userService: UserService,
    private teamService: TeamService,
    private dialogRef: MatDialogRef<AddMembersToTeamComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: { orgMembers: number[]; teamMembers: number[]; teamId: number }
  ) {}

  ngOnInit(): void {
    this.orgMembers = this.data.orgMembers;
    this.teamMembers = this.data.teamMembers;
    this.teamId = this.data.teamId;

    this.userService.getAllUsers().subscribe((users: User[]) => {
      this.usersInOrg = users.filter((u) => this.orgMembers.includes(u.id!));
    });
  }

  isAlreadyInTeam(userId: number): boolean {
    return this.teamMembers.includes(userId);
  }

  addToTeam(user: User): void {
    // Call your backend to add the member (you can replace this with actual logic)
    console.log('Adding user to team:', user.id);
    this.teamService
      .addMemberToTeam({ teamId: this.teamId, memberId: user.id! })
      .subscribe({
        next: (res) => {
          console.log(res.message); // "Member added to team"
          // Optionally update UI
        },
        error: (err) => {
          console.error('Failed to add member', err);
          alert('Error: ' + err.error.message);
        },
      });

    // Optimistically update UI (optional)
    this.teamMembers.push(user.id!);
  }

  close(): void {
    this.dialogRef.close(true);
  }
}
