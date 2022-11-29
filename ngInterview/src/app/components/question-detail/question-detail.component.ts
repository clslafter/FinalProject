import { Component, OnInit } from '@angular/core';
import { Question } from 'src/app/models/question';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css']
})
export class QuestionDetailComponent implements OnInit {

  selected: Question | null = null;

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.loadQuestion();
  }

  loadQuestion () {
    this.questionService.show(1).subscribe({
      next: (data) => {
        this.selected = data;
      },
      error: (fail) => {
        console.error('Question-DetailComponent.loadQuestion: error getting question');
        console.error(fail);
      }
    })
  }
}
