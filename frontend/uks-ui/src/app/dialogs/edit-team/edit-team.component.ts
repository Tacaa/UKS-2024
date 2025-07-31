import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TeamService } from 'src/app/services/team/team.service';
import { UpdateTeamDTO } from 'src/app/shared/dto/team/update-team.dto';

@Component({
  selector: 'app-edit-team',
  templateUrl: './edit-team.component.html',
  styleUrls: ['./edit-team.component.css'],
})
export class EditTeamComponent implements OnInit {
  editForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<EditTeamComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private teamService: TeamService
  ) {}

  ngOnInit(): void {
    this.editForm = this.fb.group({
      name: [this.data.name, Validators.required],
      description: [this.data.description],
    });
  }

  onSubmit(): void {
    if (this.editForm.valid) {
      const updateDto: UpdateTeamDTO = this.editForm.value;
      this.teamService.updateTeam(this.data.id, updateDto).subscribe({
        next: () => this.dialogRef.close(true),
        error: (err) => {
          console.error('Update failed', err);
          alert('Error: ' + err.error.message);
        },
      });
    }
  }
}
