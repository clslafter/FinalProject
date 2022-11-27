import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question';
import { QuestionService } from 'src/app/services/question-service';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  questions: Question[] = [];

  constructor(private questionService: QuestionService, private router: Router) { }

  ngOnInit(): void {

    this.loadQuestions();
  }



  loadQuestions () {
    this.questionService.index().subscribe({
      next: (data) => {
        this.questions = data;
      },
      error: (fail) => {
        console.error('WelcomeComponent.loadQuestions: error getting questions');
        console.error(fail);
      }
    })
  }
}
