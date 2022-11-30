import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { AnswerService } from 'src/app/services/answer.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-edit-answer',
  templateUrl: './edit-answer.component.html',
  styleUrls: ['./edit-answer.component.css']
})
export class EditAnswerComponent implements OnInit {
  // answer: Answer | null = null;
  // updateAnswer: Answer | null = null;

  @Input() selectedAnswer: Answer | null | undefined;

  @Output() returnToParent = new EventEmitter<Answer|null>();

  updateAnswer: Answer = new Answer();
  constructor(private answerService: AnswerService, private router: Router, private auth: AuthService) { }

  ngOnInit(): void {
  }

  answerToUpdate(answer: Answer){
    console.log("clicked")
    if(this.updateAnswer){
    this.answerService.update(this.updateAnswer, this.updateAnswer.id).subscribe({
      next: (data: any) => {
          this.updateAnswer = data;
          this.updateAnswer.enabled = true;
          this.updateAnswer.user;
        this.returnToParent.emit(data);
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
