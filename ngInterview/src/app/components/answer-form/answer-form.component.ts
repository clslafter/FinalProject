import { Component, OnInit } from '@angular/core';
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

  newAnswer: Answer = new Answer();
  constructor(private questionService: QuestionService,
              private answerService: AnswerService,
              private router: Router,
              private auth: AuthService ){ }

  ngOnInit(): void {
  }

//   loadQuestion(){this.questionService.index().subscribe({
//     next: (data) => {
//       this.questions = data;
//     },
//     error: (fail) => {
//       console.error('QuestionComponent.loadQuestions: error getting questions');
//       console.error(fail);
//     }
//   })
// }

//   loadAnswers(){
//     this.answerService.index().subscribe({
//       next: (data) => {
//         this.answers = data;
//       },
//       error: (fail) => {
//         console.error('AnswerComponent.loadAnswers: error getting questions');
//         console.error(fail);
//       }
//     })
//   }

  createAnswer(){

    this.answerService.create(this.newAnswer).subscribe({
      next: (data: any) => {
        this.newAnswer = new Answer();
        this.newAnswer.enabled = true;
        this.newAnswer.user
      },
      error: (err: any) => {
        console.error('createAnswer: error creating answer:');
        console.error(err);
      }
    })
  }
}
