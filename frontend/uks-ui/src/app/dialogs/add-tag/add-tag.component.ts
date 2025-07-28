import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth/auth.service';
import { TagService } from 'src/app/services/tag/tag.service';
import { CreateTagDTO } from 'src/app/shared/dto/tag/create-tag.dto';

@Component({
  selector: 'app-add-tag',
  templateUrl: './add-tag.component.html',
  styleUrls: ['./add-tag.component.css'],
})
export class AddTagComponent implements OnInit {
  repositoryId!: number;

  tagName: string = '';
  dockerPullCommand: string = '';

  constructor(
    private dialogRef: MatDialogRef<AddTagComponent>,
    private tagService: TagService,
    private authService: AuthService,
    @Inject(MAT_DIALOG_DATA) public data: { repositoryId: number }
  ) {}

  createTag(): void {
    const username = this.authService.getCurrentUser()?.username;
    if (!username || !this.repositoryId) {
      console.error('Missing username or repositoryId');
      return;
    }

    const dto: CreateTagDTO = {
      name: this.tagName,
      dockerPullCommand: this.dockerPullCommand,
      author: username,
      repositoryId: this.repositoryId,
    };

    this.tagService.createTag(dto).subscribe({
      next: (response) => {
        console.log('Tag created successfully:', response);
        this.dialogRef.close(true);
      },
      error: (err) => {
        console.error('Error creating tag:', err);
        alert('Error :' + err.error.message);
      },
    });
  }

  ngOnInit(): void {
    console.log('ngOnInit: ', this.data);
    this.repositoryId = this.data.repositoryId;
  }
}
