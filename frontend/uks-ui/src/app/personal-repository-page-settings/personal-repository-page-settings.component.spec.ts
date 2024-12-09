import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalRepositoryPageSettingsComponent } from './personal-repository-page-settings.component';

describe('PersonalRepositoryPageSettingsComponent', () => {
  let component: PersonalRepositoryPageSettingsComponent;
  let fixture: ComponentFixture<PersonalRepositoryPageSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalRepositoryPageSettingsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalRepositoryPageSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
