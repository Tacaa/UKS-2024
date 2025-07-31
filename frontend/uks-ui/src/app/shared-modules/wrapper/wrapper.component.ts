import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { UserAvatarComponent } from '../components/user-avatar/user-avatar.component';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Role } from 'src/app/shared/enum/Role';

@Component({
  selector: 'app-wrapper',
  standalone: true,
  imports: [
    CommonModule,
    UserAvatarComponent,
    RouterModule,
    MatMenuModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatDividerModule,
  ],
  templateUrl: './wrapper.component.html',
  styleUrls: ['./wrapper.component.css'],
})
export class WrapperComponent implements OnInit {
  selectedButton: string = 'dockerhub/repository';
  userRole: Role | null = null;
  Role = Role;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.userRole = this.authService.getCurrentUserRole();
  }

  setActive(buttonName: string): void {
    this.selectedButton = buttonName;
  }

  hasRole(role: Role): boolean {
    return this.userRole === role;
  }

  hasAtLeast(role: Role): boolean {
    const roleOrder: Role[] = [Role.USER, Role.ADMIN, Role.SUPER_ADMIN];
    return (
      this.userRole != null &&
      roleOrder.indexOf(this.userRole) >= roleOrder.indexOf(role)
    );
  }
}
