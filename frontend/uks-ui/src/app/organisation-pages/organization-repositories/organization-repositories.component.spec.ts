import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationRepositoriesComponent } from './organization-repositories.component';

describe('OrganizationRepositoriesComponent', () => {
  let component: OrganizationRepositoriesComponent;
  let fixture: ComponentFixture<OrganizationRepositoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrganizationRepositoriesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrganizationRepositoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
