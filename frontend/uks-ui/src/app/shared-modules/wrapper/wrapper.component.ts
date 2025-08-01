import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy } from '@angular/core';
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
import { RoleEnum } from 'src/app/shared/enum/RoleEnum';
import { Subscription } from 'rxjs';

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
export class WrapperComponent implements OnInit, OnDestroy {
  selectedButton: string = 'dockerhub/repository';
  userRoleEnum: RoleEnum | undefined | null = null;
  RoleEnum = RoleEnum;

  private userSubscription: Subscription | null = null;

  constructor(private authService: AuthService, private r: Router) {}

  ngOnInit(): void {
    this.userSubscription = this.authService.currentUser$.subscribe((user) => {
      if (user) {
        this.userRoleEnum =
          user.roleEnum || this.authService.getCurrentUserRoleEnum();
        console.log('User role updated:', this.userRoleEnum);
      } else {
        this.userRoleEnum = null;
        console.log('No user logged in');
      }
    });

    this.authService.restoreUser();
  }

  ngOnDestroy(): void {
    // Clean up subscription
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }

  setActive(buttonName: string): void {
    this.selectedButton = buttonName;
  }

  hasRoleEnum(roleEnum: RoleEnum): boolean {
    return this.userRoleEnum === roleEnum;
  }

  hasAtLeast(roleEnum: RoleEnum): boolean {
    const roleEnumOrder: RoleEnum[] = [
      RoleEnum.USER,
      RoleEnum.ADMIN,
      RoleEnum.SUPER_ADMIN,
    ];
    return (
      this.userRoleEnum != null &&
      roleEnumOrder.indexOf(this.userRoleEnum) >=
        roleEnumOrder.indexOf(roleEnum)
    );
  }

  logout() {
    this.authService.logout();
  }
}
