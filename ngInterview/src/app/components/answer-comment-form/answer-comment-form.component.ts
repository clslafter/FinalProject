import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { AnswerComment } from 'src/app/models/answer-comment';
import { Question } from 'src/app/models/question';
import { AnswerCommentService } from 'src/app/services/answer-comment.service';
import { AnswerService } from 'src/app/services/answer.service';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-answer-comment-form',
  templateUrl: './answer-comment-form.component.html',
  styleUrls: ['./answer-comment-form.component.css']
})
export class AnswerCommentFormComponent implements OnInit {

  answers: Answer[] = [];

  answerComments: AnswerComment[] = [];

  @Input() selectedAnswer: Answer | null | undefined;

  @Output() returnToParent = new EventEmitter<Question|null>();

  newAnswerComment: AnswerComment = new AnswerComment();

  constructor(private questionService: QuestionService,
    private answerService: AnswerService,
    private answerCommentService: AnswerCommentService,
    private router: Router,
    private auth: AuthService ) { }

  ngOnInit(): void {
  }

  createAnswerComment(){
    if (this.selectedAnswer){
      this.answerCommentService.create(this.newAnswerComment, this.selectedAnswer.id).subscribe({
        next: (data: any) => {
          this.newAnswerComment = new AnswerComment();
          this.newAnswerComment.enabled = true;
          this.newAnswerComment.user;


          this.returnToParent.emit(data);

          window.location.reload();
        },
        error: (err: any) => {
          console.error('createAnswerComment: error creating answerComment:');
          console.error(err);
        }
      })
    }
    }
  }

