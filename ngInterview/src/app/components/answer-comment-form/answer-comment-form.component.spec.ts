import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerCommentFormComponent } from './answer-comment-form.component';

describe('AnswerCommentFormComponent', () => {
  let component: AnswerCommentFormComponent;
  let fixture: ComponentFixture<AnswerCommentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerCommentFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnswerCommentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
