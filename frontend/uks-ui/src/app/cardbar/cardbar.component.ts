import { Component, Input, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import {
  OfficialRepositoryDTO,
  RepositoryDTO,
} from '../shared/dto/repository/repository.dto';
import { Visibility } from '../shared/enum/Visibility';
import { User } from '../shared/models/user.model';
import { UserBadge } from '../shared/enum/UserBadge';

@Component({
  selector: 'app-cardbar',
  templateUrl: './cardbar.component.html',
  styleUrls: ['./cardbar.component.css'],
})
export class CardbarComponent implements OnInit {
  @Input() category: string | null = null;
  @Input() repositories: RepositoryDTO[] = [];
  @Input() allUsers: User[] = [];
  @Input() ofiicialRepositories: OfficialRepositoryDTO[] = [];

  constructor() {}

  ngOnInit(): void {}

  get filteredRepositories(): RepositoryDTO[] {
    if (this.category === 'Official') {
      return this.ofiicialRepositories.map(
        (officialRepo) => officialRepo.repositoryDTO
      );
    }

    let filtered = this.repositories;

    if (this.category && this.category.trim() !== '') {
      filtered = filtered.filter(
        (repo) => repo.categoryString === this.category
      );
    }

    filtered = filtered.filter(
      (repo) => repo.visibility !== Visibility.PRIVATE
    );

    return filtered;
  }

  getDownloadCount(number: number): string {
    if (!number) return '0';
    const randomNum = ((number * 123 + 456) % 1000) / 1000;
    const downloads = Math.floor(50000 + randomNum * 1500000); // Between 50k-1.55mil

    if (downloads > 1000000) {
      return (downloads / 1000000).toFixed(1) + 'mil';
    } else {
      return Math.round(downloads / 1000) + 'k';
    }
  }

  getUserBadge(userId: number): UserBadge | undefined {
    const user = this.allUsers.find((u) => u.id === userId);
    if (user?.userBadge === UserBadge.NONE) {
      return;
    }
    return user?.userBadge;
  }

  isOfficialRepository(repoId: number): boolean {
    return this.ofiicialRepositories.some(
      (officialRepo) => officialRepo.repositoryDTO.id === repoId
    );
  }
}
