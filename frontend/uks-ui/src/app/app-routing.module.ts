import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepositoriesComponent } from './personal-repository-pages/repositories/repositories.component';
import { CreateRepositoryComponent } from './create-repository/create-repository.component';
import { ExploreComponent } from './explore/explore.component';
import { RepositoryPageComponent } from './repository-page/repository-page.component';
import { RepositoryPageOverviewComponent } from './repository-page-overview/repository-page-overview.component';
import { RepositoryPageTagsComponent } from './repository-page-tags/repository-page-tags.component';
import { PersonalRepositoryPageComponent } from './personal-repository-pages/personal-repository-page/personal-repository-page.component';
import { PersonalRepositoryPageGeneralComponent } from './personal-repository-pages/personal-repository-page-general/personal-repository-page-general.component';
import { PersonalRepositoryPageCollaboratorsComponent } from './personal-repository-pages/personal-repository-page-collaborators/personal-repository-page-collaborators.component';
import { PersonalRepositoryPageSettingsComponent } from './personal-repository-pages/personal-repository-page-settings/personal-repository-page-settings.component';
import { PersonalRepositoryPageTagsComponent } from './personal-repository-pages/personal-repository-page-tags/personal-repository-page-tags.component';
import { AccountSettingsComponent } from './user/account-settings/account-settings.component';
import { UsagePageComponent } from './usage-page/usage-page.component';
import { OrgsPageComponent } from './organisation-pages/orgs-page/orgs-page.component';
import { OrgsPageCreateOrgComponent } from './organisation-pages/orgs-page-create-org/orgs-page-create-org.component';
import { OrganizationComponent } from './organisation-pages/organization/organization.component';
import { OrganizationMembersComponent } from './organisation-pages/organization-members/organization-members.component';
import { OrganizationRepositoriesComponent } from './organisation-pages/organization-repositories/organization-repositories.component';
import { OrganizationSettingsComponent } from './organisation-pages/organization-settings/organization-settings.component';
import { OrganizationTeamsComponent } from './organisation-pages/organization-teams/organization-teams.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { BadgeEditComponent } from './admin-board/badge-edit/badge-edit.component';
import { CreateOfficialRepoComponent } from './admin-board/create-official-repo/create-official-repo.component';
import { AdminPanelComponent } from './admin-board/admin-panel/admin-panel/admin-panel.component';
import { OfficialRepositoriesComponent } from './admin-board/official-repositories/official-repositories.component';
import { CreateAdministratorComponent } from './admin-board/create-administrator/create-administrator.component';
import { SuperAdminLoginComponent } from './admin-board/super-admin-login/super-admin-login.component';
import { LogSearchComponent } from './log-search/log-search.component';
import { LoginComponent } from './auth-pages/login/login.component';
import { RegisterComponent } from './auth-pages/register/register.component';
import { Injectable, Component, OnInit } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './services/auth/auth.service';
import { SearchpageComponent } from './searchpage/searchpage.component';

// Redirect Component to handle the authentication logic
@Component({
  template: '',
})
export class AuthRedirectComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    const userRole = this.authService.getCurrentUserRoleEnum();

    if (userRole === null || userRole === undefined) {
      // User is not authenticated, redirect to explore
      this.router.navigate(['/dockerhub/explore']);
    } else {
      // User is authenticated, redirect to repository
      this.router.navigate(['/dockerhub/repository']);
    }
  }
}

// Auth Guard for protecting routes that require authentication
@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const userRole = this.authService.getCurrentUserRoleEnum();

    if (userRole === null || userRole === undefined) {
      // User is not authenticated, redirect to explore
      this.router.navigate(['/dockerhub/explore']);
      return false;
    }

    // User is authenticated, allow access
    return true;
  }
}

@Injectable({
  providedIn: 'root',
})
export class SuperAdminGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (!this.authService.isSuperAdminInitialized()) {
      this.router.navigate(['/superadminLogin']);
      return false;
    }
    return true;
  }
}

const routes: Routes = [
  { path: '', redirectTo: 'dockerhub', pathMatch: 'full' },
  { path: 'superadminLogin', component: SuperAdminLoginComponent },
  {
    path: 'dockerhub',
    title: 'Home',
    loadComponent: () =>
      import('./shared-modules/wrapper/wrapper.component').then(
        (c) => c.WrapperComponent
      ),
    canActivate: [SuperAdminGuard],
    children: [
      {
        path: '',
        component: AuthRedirectComponent,
      },
      {
        path: 'repository',
        component: RepositoriesComponent,
        title: 'Home',
        canActivate: [AuthGuard],
      },
      {
        path: 'create',
        component: CreateRepositoryComponent,
        title: 'create',
      },
      {
        path: 'explore',
        component: ExploreComponent,
        title: 'explore',
      },
      {
        path: 'r/:id',
        component: RepositoryPageComponent,
        title: 'Repository',
        children: [
          { path: 'overview', component: RepositoryPageOverviewComponent },
          { path: 'tags', component: RepositoryPageTagsComponent },
          { path: '', redirectTo: 'overview', pathMatch: 'full' },
        ],
      },
      {
        // TODO   If the user cannot edit this repository it should be
        // TODO   redirected to /r/repositoryName where he can just view it
        path: 'repository/:id',
        component: PersonalRepositoryPageComponent,

        children: [
          {
            path: 'general',
            component: PersonalRepositoryPageGeneralComponent,
          },
          {
            path: 'tags',
            component: PersonalRepositoryPageTagsComponent,
          },
          {
            path: 'collaborators',
            component: PersonalRepositoryPageCollaboratorsComponent,
          },
          {
            path: 'settings',
            component: PersonalRepositoryPageSettingsComponent,
          },
          { path: '', redirectTo: 'general', pathMatch: 'full' },
        ],
      },
      {
        path: 'adminPanel',
        component: AdminPanelComponent,
        children: [
          { path: 'editBadge', component: BadgeEditComponent },
          {
            path: 'createOfficialRepo',
            component: CreateOfficialRepoComponent,
          },
          {
            path: 'officialRepoList',
            component: OfficialRepositoriesComponent,
          },
          { path: 'logs', component: LogSearchComponent },
        ],
      },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      {
        path: 'createAdministrator',
        component: CreateAdministratorComponent,
      },
      {
        path: 'accountSettings',
        component: AccountSettingsComponent,
      },
      {
        path: 'profile',
        component: UserProfileComponent,
      },
      {
        path: 'usage',
        component: UsagePageComponent,
        title: 'Usage',
      },

      {
        path: 'organizations',
        component: OrgsPageComponent,
        title: 'Organizations',
      },

      {
        path: 'organizations/create',
        component: OrgsPageCreateOrgComponent,
        title: 'Create new organization',
      },
      {
        path: 'search',
        component: SearchpageComponent,
      },
      {
        path: 'search/:name',
        component: SearchpageComponent,
      },
      {
        path: 'organizations/:id',
        component: OrganizationComponent,
        title: 'Organization',
        children: [
          {
            path: 'members',
            component: OrganizationMembersComponent,
          },
          {
            path: 'repositories',
            component: OrganizationRepositoriesComponent,
          },
          {
            path: 'settings',
            component: OrganizationSettingsComponent,
          },
          {
            path: 'teams',
            component: OrganizationTeamsComponent,
          },
          { path: '', redirectTo: 'members', pathMatch: 'full' },
        ],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
