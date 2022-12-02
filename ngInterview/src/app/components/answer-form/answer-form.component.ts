import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { Question } from 'src/app/models/question';
import { AnswerService } from 'src/app/services/answer.service';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-answer-form',
  templateUrl: './answer-form.component.html',
  styleUrls: ['./answer-form.component.css']
})
export class AnswerFormComponent implements OnInit {
  questions: Question[] = [];

  answers: Answer[] = [];

  @Input() selectedQuestion: Question | null | undefined;

  @Output() returnToParent = new EventEmitter<Question|null>();

  newAnswer: Answer = new Answer();
  constructor(private questionService: QuestionService,
              private answerService: AnswerService,
              private router: Router,
              private auth: AuthService ){ }

  ngOnInit(): void {
  }

  createAnswer(){
  if (this.selectedQuestion){
    this.answerService.create(this.newAnswer, this.selectedQuestion.id).subscribe({
      next: (data: any) => {
        this.newAnswer = new Answer();
        this.newAnswer.enabled = true;
        this.newAnswer.user;

        this.returnToParent.emit(data);
      },
      error: (err: any) => {
        console.error('createAnswer: error creating answer:');
        console.error(err);
      }
    })
  }
  }
}

