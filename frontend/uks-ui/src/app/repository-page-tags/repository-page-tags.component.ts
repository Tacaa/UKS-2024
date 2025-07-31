import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TagService } from '../services/tag/tag.service';
import { TagDTO } from '../shared/dto/tag/tag.dto';
import { MatDialog } from '@angular/material/dialog';
import { AddTagComponent } from '../dialogs/add-tag/add-tag.component';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-repository-page-tags',
  templateUrl: './repository-page-tags.component.html',
  styleUrls: ['./repository-page-tags.component.css'],
})
export class RepositoryPageTagsComponent implements OnInit {
  @Input() canAddTags?: boolean;
  tags: TagDTO[] = [];
  filteredTags: TagDTO[] = [];
  searchTerm: string = '';
  showToast = false;
  repositoryId?: number;

  constructor(
    private route: ActivatedRoute,
    private tagService: TagService,
    private dialog: MatDialog,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const repoId = Number(params.get('id'));
      if (repoId) {
        this.loadTags(repoId);
        this.repositoryId = repoId;
      }
    });
  }

  loadTags(repositoryId: number): void {
    this.tagService.getTagsByRepository(repositoryId).subscribe((tags) => {
      this.tags = tags;
      this.applyFilter(); // Initialize filteredTags
    });
  }

  applyFilter(): void {
    const term = this.searchTerm.trim().toLowerCase();

    this.filteredTags = this.tags.filter((tag) =>
      (tag.name || '').toLowerCase().includes(term)
    );
  }

  getCompressedSize(id: number): string {
    const size = ((id * 1337) % 700) + 50; // returns value between ~50 and ~750
    return `${size} MB`;
  }

  generateDigest(id: number): string {
    const hash = btoa(
      `${id.toString()}${id.toString()}${id.toString()}s${id.toString()}`
    )
      .split('')
      .reduce((acc, char, i) => acc + char.charCodeAt(0) * (i + 1), 0)
      .toString(16);

    return hash.substring(0, 12);
  }

  copyToClipboard(text: string) {
    navigator.clipboard.writeText(text);
    this.showToast = true;
    setTimeout(() => (this.showToast = false), 2000);
  }

  get isUserLoggedIn(): boolean {
    return this.authService.getCurrentUser() !== null;
  }

  openAddTagDialog() {
    console.log('REPO ID' + this.repositoryId);
    this.dialog
      .open(AddTagComponent, {
        data: { repositoryId: this.repositoryId },
      })
      .afterClosed()
      .subscribe((result) => {
        if (result === true) {
          this.loadTags(this.repositoryId as number);
          console.log('Tag successfully created');
        }
      });
  }

  deleteTag(id: number) {
    this.tagService.deleteTag(id).subscribe({
      next: (message) => {
        console.log(message);
        this.loadTags(this.repositoryId as number);
      },
      error: (error) => {
        console.error('Error deleting tag:', error);
        alert('Error deleting tag: ' + error.error.message);
      },
    });
  }
}
