import { TestBed } from '@angular/core/testing';

import { AnswerCommentService } from './answer-comment.service';

describe('AnswerCommentService', () => {
  let service: AnswerCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnswerCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
