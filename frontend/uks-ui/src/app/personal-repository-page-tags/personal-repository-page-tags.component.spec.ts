import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalRepositoryPageTagsComponent } from './personal-repository-page-tags.component';

describe('PersonalRepositoryPageTagsComponent', () => {
  let component: PersonalRepositoryPageTagsComponent;
  let fixture: ComponentFixture<PersonalRepositoryPageTagsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalRepositoryPageTagsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalRepositoryPageTagsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
