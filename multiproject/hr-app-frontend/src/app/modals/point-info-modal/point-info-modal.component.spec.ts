import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointInfoModalComponent } from './point-info-modal.component';

describe('PointInfoModalComponent', () => {
  let component: PointInfoModalComponent;
  let fixture: ComponentFixture<PointInfoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointInfoModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PointInfoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
