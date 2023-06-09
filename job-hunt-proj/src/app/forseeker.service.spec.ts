import { TestBed } from '@angular/core/testing';

import { ForseekerService } from './forseeker.service';

describe('ForseekerService', () => {
  let service: ForseekerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ForseekerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
