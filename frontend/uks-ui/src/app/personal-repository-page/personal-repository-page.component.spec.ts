import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalRepositoryPageComponent } from './personal-repository-page.component';

describe('PersonalRepositoryPageComponent', () => {
  let component: PersonalRepositoryPageComponent;
  let fixture: ComponentFixture<PersonalRepositoryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalRepositoryPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalRepositoryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
