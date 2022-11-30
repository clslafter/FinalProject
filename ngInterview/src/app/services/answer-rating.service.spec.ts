import { TestBed } from '@angular/core/testing';

import { AnswerRatingService } from './answer-rating.service';

describe('AnswerRatingService', () => {
  let service: AnswerRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnswerRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
