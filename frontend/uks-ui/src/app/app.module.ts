import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular_material.module';

import { RepositoriesComponent } from './menu-pages/repositories/repositories.component';
import { CreateRepositoryComponent } from './create-repository/create-repository.component';
import { ExploreComponent } from './explore/explore.component';
import { CardbarComponent } from './cardbar/cardbar.component';
import { RepositoryPageComponent } from './repository-page/repository-page.component';
import { RepositoryPageOverviewComponent } from './repository-page-overview/repository-page-overview.component';
import { RepositoryPageTagsComponent } from './repository-page-tags/repository-page-tags.component';
import { PersonalRepositoryPageComponent } from './personal-repository-page/personal-repository-page.component';
import { PersonalRepositoryPageGeneralComponent } from './personal-repository-page-general/personal-repository-page-general.component';
import { PersonalRepositoryPageSettingsComponent } from './personal-repository-page-settings/personal-repository-page-settings.component';
import { PersonalRepositoryPageCollaboratorsComponent } from './personal-repository-page-collaborators/personal-repository-page-collaborators.component';
import { PersonalRepositoryPageTagsComponent } from './personal-repository-page-tags/personal-repository-page-tags.component';
import { AccountSettingsComponent } from './user/account-settings/account-settings.component';
import { UsagePageComponent } from './usage-page/usage-page.component';
import { OrgsPageComponent } from './orgs-page/orgs-page.component';
import { OrgsPageCreateOrgComponent } from './orgs-page-create-org/orgs-page-create-org.component';
import { OrganizationComponent } from './organization/organization.component';
import { OrganizationMembersComponent } from './organization-members/organization-members.component';
import { OrganizationTeamsComponent } from './organization-teams/organization-teams.component';
import { OrganizationRepositoriesComponent } from './organization-repositories/organization-repositories.component';
import { OrganizationSettingsComponent } from './organization-settings/organization-settings.component';
import { AddMembersComponent } from './dialogs/add-members/add-members.component';
import { CreateTeamComponent } from './dialogs/create-team/create-team.component';
import { PullsOverTimeComponent } from './usage-page/charts/pulls-over-time/pulls-over-time.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { UserProfileComponent } from './user/user-profile/user-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    RepositoriesComponent,
    CreateRepositoryComponent,
    ExploreComponent,
    CardbarComponent,
    RepositoryPageComponent,
    RepositoryPageOverviewComponent,
    RepositoryPageTagsComponent,
    PersonalRepositoryPageComponent,
    PersonalRepositoryPageGeneralComponent,
    PersonalRepositoryPageSettingsComponent,
    PersonalRepositoryPageCollaboratorsComponent,
    PersonalRepositoryPageTagsComponent,
    AccountSettingsComponent,
    UsagePageComponent,
    OrgsPageComponent,
    OrgsPageCreateOrgComponent,
    OrganizationComponent,
    OrganizationMembersComponent,
    OrganizationTeamsComponent,
    OrganizationRepositoriesComponent,
    OrganizationSettingsComponent,
    AddMembersComponent,
    CreateTeamComponent,
    PullsOverTimeComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxChartsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
