import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FftComponent } from './fft.component';

describe('FftComponent', () => {
  let component: FftComponent;
  let fixture: ComponentFixture<FftComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FftComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
