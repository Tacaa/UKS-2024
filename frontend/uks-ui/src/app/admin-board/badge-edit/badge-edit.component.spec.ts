import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BadgeEditComponent } from './badge-edit.component';

describe('BadgeEditComponent', () => {
  let component: BadgeEditComponent;
  let fixture: ComponentFixture<BadgeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BadgeEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BadgeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
