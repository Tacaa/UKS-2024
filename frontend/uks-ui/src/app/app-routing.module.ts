import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepositoriesComponent } from './menu-pages/repositories/repositories.component';
import { CreateRepositoryComponent } from './create-repository/create-repository.component';
import { RepositoryPageComponent } from './repository-page/repository-page.component';
import { RepositoryPageOverviewComponent } from './repository-page-overview/repository-page-overview.component';
import { RepositoryPageTagsComponent } from './repository-page-tags/repository-page-tags.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
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
        path: 'r/repositoryName',
        component: RepositoryPageComponent,
        title: ':repositoryName',
        children: [
          { path: 'overview', component: RepositoryPageOverviewComponent },
          { path: 'tags', component: RepositoryPageTagsComponent },
          { path: '', redirectTo: 'overview', pathMatch: 'full' },
          // Default to Overview
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
