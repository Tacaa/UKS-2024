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

const routes: Routes = [
  { path: '', redirectTo: 'features', pathMatch: 'full' },
  {
    path: 'features',
    loadComponent: () =>
      import('./shared-modules/wrapper/wrapper.component').then(
        (c) => c.WrapperComponent
      ),
    title: 'Home',
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
      },
      {
        path: 'home',
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
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
