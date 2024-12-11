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
import { UsagePageComponent } from './usage-page/usage-page.component';

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
    UsagePageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
