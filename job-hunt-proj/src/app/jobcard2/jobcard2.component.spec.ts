import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Jobcard2Component } from './jobcard2.component';

describe('Jobcard2Component', () => {
  let component: Jobcard2Component;
  let fixture: ComponentFixture<Jobcard2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Jobcard2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Jobcard2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
