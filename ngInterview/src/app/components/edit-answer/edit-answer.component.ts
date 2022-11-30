import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { AnswerService } from 'src/app/services/answer.service';

@Component({
  selector: 'app-edit-answer',
  templateUrl: './edit-answer.component.html',
  styleUrls: ['./edit-answer.component.css']
})
export class EditAnswerComponent implements OnInit {
  answer: Answer | null = null;
  updateAnswer: Answer | null = null;

  constructor(private answerService: AnswerService, private router: Router) { }

  ngOnInit(): void {
  }

  answerToUpdate(answer: Answer): void{
    if(this.updateAnswer){
    this.answerService.update(answer).subscribe({
      next: (data: any) => {
          this.answer = data;

        },
      error: (fail: any) => {
        console.error(
          'EditAnswerComponent.updateAnswer(): error updating answer:'
        );
        console.error(fail);
      },
    });
  }
}

}
