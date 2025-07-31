import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfficialRepositoriesComponent } from './official-repositories.component';

describe('OfficialRepositoriesComponent', () => {
  let component: OfficialRepositoriesComponent;
  let fixture: ComponentFixture<OfficialRepositoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OfficialRepositoriesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OfficialRepositoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
