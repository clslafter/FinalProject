import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  newQuestion: Question = new Question();

  constructor(private questionService: Question, private router: Router,
    private auth: AuthService) { }



  ngOnInit(): void {
  }



  //need an addQuestion function

}
