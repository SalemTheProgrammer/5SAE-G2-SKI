import { TestBed } from '@angular/core/testing';

import { SkierserviceService } from './skierservice.service';

describe('SkierserviceService', () => {
  let service: SkierserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SkierserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
