import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TeamService } from 'src/app/services/team/team.service';
import { CreateTeamDTO } from 'src/app/shared/dto/team/create-team.dto';
import { TeamPermission } from 'src/app/shared/enum/TeamPersmission';

@Component({
  selector: 'app-create-team',
  templateUrl: './create-team.component.html',
  styleUrls: ['./create-team.component.css'],
})
export class CreateTeamComponent {
  name: string = '';
  description: string = '';
  teamPermission: TeamPermission = TeamPermission.READ_ONLY; // Default
  TeamPermission = TeamPermission;

  constructor(
    private dialogRef: MatDialogRef<CreateTeamComponent>,
    private teamService: TeamService,
    @Inject(MAT_DIALOG_DATA)
    public data: { ownerId: number; organisationId: number }
  ) {}

  createTeam(): void {
    const dto: CreateTeamDTO = {
      name: this.name,
      description: this.description,
      teamPermission: this.teamPermission,
      ownerId: this.data.ownerId,
      organisationId: this.data.organisationId,
    };

    this.teamService.createTeam(dto).subscribe({
      next: (res) => {
        console.log('Team created:', res.data);
        alert('Team created');
        this.dialogRef.close(true);
      },
      error: (err) => {
        console.error('Create team failed:', err);
        alert('Create team failed: ' + err.error.message);
      },
    });
  }
}
