import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepositoryPageTagsComponent } from './repository-page-tags.component';

describe('RepositoryPageTagsComponent', () => {
  let component: RepositoryPageTagsComponent;
  let fixture: ComponentFixture<RepositoryPageTagsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RepositoryPageTagsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepositoryPageTagsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
