import { Component, OnInit } from '@angular/core';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-answer-form',
  templateUrl: './answer-form.component.html',
  styleUrls: ['./answer-form.component.css']
})
export class AnswerFormComponent implements OnInit {

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
  }

}
