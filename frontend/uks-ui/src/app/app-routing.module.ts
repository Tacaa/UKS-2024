import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepositoriesComponent } from './menu-pages/repositories/repositories.component';
import { CreateRepositoryComponent } from './create-repository/create-repository.component';
import { ExploreComponent } from './explore/explore.component';
import { RepositoryPageComponent } from './repository-page/repository-page.component';
import { RepositoryPageOverviewComponent } from './repository-page-overview/repository-page-overview.component';
import { RepositoryPageTagsComponent } from './repository-page-tags/repository-page-tags.component';
import { PersonalRepositoryPageComponent } from './personal-repository-page/personal-repository-page.component';
import { PersonalRepositoryPageGeneralComponent } from './personal-repository-page-general/personal-repository-page-general.component';
import { PersonalRepositoryPageCollaboratorsComponent } from './personal-repository-page-collaborators/personal-repository-page-collaborators.component';
import { PersonalRepositoryPageSettingsComponent } from './personal-repository-page-settings/personal-repository-page-settings.component';
import { PersonalRepositoryPageTagsComponent } from './personal-repository-page-tags/personal-repository-page-tags.component';
import { AccountSettingsComponent } from './user/account-settings/account-settings.component';
import { UsagePageComponent } from './usage-page/usage-page.component';
import { OrgsPageComponent } from './orgs-page/orgs-page.component';
import { OrgsPageCreateOrgComponent } from './orgs-page-create-org/orgs-page-create-org.component';
import { OrganizationComponent } from './organization/organization.component';
import { OrganizationMembersComponent } from './organization-members/organization-members.component';
import { OrganizationRepositoriesComponent } from './organization-repositories/organization-repositories.component';
import { OrganizationSettingsComponent } from './organization-settings/organization-settings.component';
import { OrganizationTeamsComponent } from './organization-teams/organization-teams.component';


const routes: Routes = [
  { path: '', redirectTo: 'dockerhub', pathMatch: 'full' },
  {
    path: 'dockerhub',
    loadComponent: () =>
      import('./shared-modules/wrapper/wrapper.component').then(
        (c) => c.WrapperComponent
      ),
    title: 'Home',
    children: [
      {
        path: '',
        redirectTo: 'repository',
        pathMatch: 'full',
      },
      {
        path: 'repository',
        component: RepositoriesComponent,
        title: 'Home',
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
        path: 'r/:user/:repository',
        component: RepositoryPageComponent,
        title: ':user/:repository',
        children: [
          { path: 'overview', component: RepositoryPageOverviewComponent },
          { path: 'tags', component: RepositoryPageTagsComponent },
          { path: '', redirectTo: 'overview', pathMatch: 'full' },
        ],
      },
      {
        // TODO   If the user cannot edit this repository it should be
        // TODO   redirected to /r/repositoryName where he can just view it
        path: 'repository/:user/:repository',
        component: PersonalRepositoryPageComponent,
        title: ':user/:repository',
        children: [
          {
            path: 'general',
            component: PersonalRepositoryPageGeneralComponent,
          },
          {
            path: 'tags',
            component: PersonalRepositoryPageTagsComponent,
            title: ':user/:repository',
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
        path: 'accountSettings',
        component: AccountSettingsComponent
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
        path: 'organizations/:orgNamespace',
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
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
