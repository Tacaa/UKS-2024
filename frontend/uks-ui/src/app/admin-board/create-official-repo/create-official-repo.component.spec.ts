import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOfficialRepoComponent } from './create-official-repo.component';

describe('CreateOfficialRepoComponent', () => {
  let component: CreateOfficialRepoComponent;
  let fixture: ComponentFixture<CreateOfficialRepoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateOfficialRepoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateOfficialRepoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
