import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { AnswerRating } from 'src/app/models/answer-rating';
import { Question } from 'src/app/models/question';
import { AnswerRatingService } from 'src/app/services/answer-rating.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css']
})
export class QuestionDetailComponent implements OnInit {

  selected: Question | null = null;





  constructor(private questionService: QuestionService, private answerRatingService: AnswerRatingService, private route: ActivatedRoute, private router: Router) { }

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
        this.addAnswer = false;
      },
      error: (fail) => {
        console.error('QuestionDetailComponent.ngOnInit: question not found');
        this.router.navigateByUrl('questionNotFound'); //doesn't exist
      }
    });
  }
}

determineRatingValue(answer: Answer){
  let up = 0;
  let down = 0;
  if(answer.ratings){
    let ratings: AnswerRating[] = answer.ratings;
    for(let i = 0; i < ratings.length; i++){
      if(ratings[i].upvote){
        up++;
      }
      else {
        up--;
      }
    }

  }
  return up;
}

voteUp(answer: Answer){
  console.log("vote up clicked");
  let ar = new AnswerRating();
  ar.upvote = true;
  this.answerRatingService.upvote(ar, answer.id).subscribe({
    next: (data) => {
      ar = data;
      if(this.selected?.id)
      this.questionService.show(this.selected?.id).subscribe({
        next: (data) => {
          this.selected = data;
        },
        error: (fail) => {
          console.error('QuestionDetailComponent.ngOnInit: question not found');
          this.router.navigateByUrl('questionNotFound'); //doesn't exist
        }
      });
    },
    error: (fail) => {
      console.error('Answer: not found');
      this.router.navigateByUrl('AnswerNotFound'); //doesn't exist
    }
  })
}

voteDown(answer: Answer){
  console.log("vote down clicked");
  let ar = new AnswerRating();
  ar.upvote = false;
  this.answerRatingService.upvote(ar, answer.id).subscribe({
    next: (data) => {
      ar = data;
      if(this.selected?.id)
      this.questionService.show(this.selected?.id).subscribe({
        next: (data) => {
          this.selected = data;
        },
        error: (fail) => {
          console.error('QuestionDetailComponent.ngOnInit: question not found');
          this.router.navigateByUrl('questionNotFound'); //doesn't exist
        }
      });
    },
    error: (fail) => {
      console.error('Answer: not found');
      this.router.navigateByUrl('AnswerNotFound'); //doesn't exist
    }
  })
}
}

