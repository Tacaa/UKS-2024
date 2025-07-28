import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TagService } from '../services/tag/tag.service';
import { TagDTO } from '../shared/dto/tag/tag.dto';

@Component({
  selector: 'app-repository-page-tags',
  templateUrl: './repository-page-tags.component.html',
  styleUrls: ['./repository-page-tags.component.css'],
})
export class RepositoryPageTagsComponent implements OnInit {
  tags: TagDTO[] = [];
  filteredTags: TagDTO[] = [];
  searchTerm: string = '';
  showToast = false;

  constructor(private route: ActivatedRoute, private tagService: TagService) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const repoId = Number(params.get('id'));
      if (repoId) {
        this.loadTags(repoId);
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
}
