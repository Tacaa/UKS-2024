import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrgsPageComponent } from './orgs-page.component';

describe('OrgsPageComponent', () => {
  let component: OrgsPageComponent;
  let fixture: ComponentFixture<OrgsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrgsPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrgsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
