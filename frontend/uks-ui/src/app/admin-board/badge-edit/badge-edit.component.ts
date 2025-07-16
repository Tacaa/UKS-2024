import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { UserBadge } from 'src/app/shared/enum/UserBadge';
import { User } from 'src/app/shared/models/user.model';

@Component({
  selector: 'app-badge-edit',
  templateUrl: './badge-edit.component.html',
  styleUrls: ['./badge-edit.component.css'],
})
export class BadgeEditComponent implements OnInit {
  allUsers: User[] = [];
  searchParams = {
    firstName: '',
    lastName: '',
    username: '',
    role: '',
    userBadge: '',
  };

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadAllUsers();
  }

  loadAllUsers(): void {
    this.userService.getAllUsers().subscribe((data) => {
      this.allUsers = data;
    });
  }

  changeBadge(userId: number, badge: string): void {
    if (userId === undefined) return;
    this.userService
      .updateUserBadge(userId, { userBadge: badge })
      .subscribe(() => {
        this.loadAllUsers();
      });
  }

  onSearch(): void {
    const nonEmptyParams = Object.fromEntries(
      Object.entries(this.searchParams).filter(([_, v]) => v !== '')
    );

    if (Object.keys(nonEmptyParams).length === 0) {
      this.loadAllUsers(); // fallback to all users
    } else {
      this.userService.searchUsers(nonEmptyParams).subscribe((res) => {
        this.allUsers = res.content || [];
      });
    }
  }
}
