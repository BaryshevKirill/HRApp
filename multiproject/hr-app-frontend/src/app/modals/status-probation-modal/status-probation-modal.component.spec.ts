import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusProbationModalComponent } from './status-probation-modal.component';

describe('StatusProbationModalComponent', () => {
  let component: StatusProbationModalComponent;
  let fixture: ComponentFixture<StatusProbationModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatusProbationModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StatusProbationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
