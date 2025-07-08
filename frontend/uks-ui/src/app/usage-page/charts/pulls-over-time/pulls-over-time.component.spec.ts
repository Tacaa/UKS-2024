import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PullsOverTimeComponent } from './pulls-over-time.component';

describe('PullsOverTimeComponent', () => {
  let component: PullsOverTimeComponent;
  let fixture: ComponentFixture<PullsOverTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PullsOverTimeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PullsOverTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
