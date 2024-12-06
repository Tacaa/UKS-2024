import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalRepositoryPageGeneralComponent } from './personal-repository-page-general.component';

describe('PersonalRepositoryPageGeneralComponent', () => {
  let component: PersonalRepositoryPageGeneralComponent;
  let fixture: ComponentFixture<PersonalRepositoryPageGeneralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalRepositoryPageGeneralComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalRepositoryPageGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
