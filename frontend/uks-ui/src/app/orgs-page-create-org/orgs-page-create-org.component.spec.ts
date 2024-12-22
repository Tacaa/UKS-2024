import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrgsPageCreateOrgComponent } from './orgs-page-create-org.component';

describe('OrgsPageCreateOrgComponent', () => {
  let component: OrgsPageCreateOrgComponent;
  let fixture: ComponentFixture<OrgsPageCreateOrgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrgsPageCreateOrgComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrgsPageCreateOrgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
