import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Repository } from 'src/app/shared/models/mock.repository.model';
import { RepositoryService } from 'src/app/services/mock-repository/repository.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  selectedOption: string = 'repositories';
  name = this.authService.getCurrentUser();

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    const user = this.authService.getCurrentUser();
    const fullName = `${user?.firstName} ${user?.lastName}`;
  }
}
