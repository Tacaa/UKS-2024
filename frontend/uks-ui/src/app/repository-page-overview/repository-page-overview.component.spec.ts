import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepositoryPageOverviewComponent } from './repository-page-overview.component';

describe('RepositoryPageOverviewComponent', () => {
  let component: RepositoryPageOverviewComponent;
  let fixture: ComponentFixture<RepositoryPageOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RepositoryPageOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepositoryPageOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
