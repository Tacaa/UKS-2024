import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalRepositoryPageCollaboratorsComponent } from './personal-repository-page-collaborators.component';

describe('PersonalRepositoryPageCollaboratorsComponent', () => {
  let component: PersonalRepositoryPageCollaboratorsComponent;
  let fixture: ComponentFixture<PersonalRepositoryPageCollaboratorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalRepositoryPageCollaboratorsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalRepositoryPageCollaboratorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
