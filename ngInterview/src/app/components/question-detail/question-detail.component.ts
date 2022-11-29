import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { AnswerRating } from 'src/app/models/answer-rating';
import { Question } from 'src/app/models/question';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css']
})
export class QuestionDetailComponent implements OnInit {

  selected: Question | null = null;



  constructor(private questionService: QuestionService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.loadPage();
  }

  addAnswer: boolean = false;

  setAddAnswer(){
  this.addAnswer = true;
  }

  loadPage(){
  let routeId = this.route.snapshot.paramMap.get('id');
  console.log(routeId);
  if (!this.selected && routeId) {
    let questionId = Number.parseInt(routeId);
    if(isNaN(questionId)) {
      this.router.navigateByUrl('invalidId');
    } else {
      this.questionService.show(questionId).subscribe({
        next: (data) => {
          this.selected = data;
        },
        error: (fail) => {
          console.error('QuestionDetailComponent.ngOnInit: question not found');
          this.router.navigateByUrl('questionNotFound'); //doesn't exist
        }
      });
    }
  }
  }

  loadNewAnswer(){
    if (this.selected){
    this.questionService.show(this.selected.id).subscribe({
      next: (data) => {
        this.selected = data;
      },
      error: (fail) => {
        console.error('QuestionDetailComponent.ngOnInit: question not found');
        this.router.navigateByUrl('questionNotFound'); //doesn't exist
      }
    });
  }
}

determineRatingValue(answer: Answer){
let ratings;

}
}
