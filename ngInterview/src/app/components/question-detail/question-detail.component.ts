import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { AnswerRating } from 'src/app/models/answer-rating';
import { Question } from 'src/app/models/question';
import { User } from 'src/app/models/user';
import { AnswerRatingService } from 'src/app/services/answer-rating.service';
import { AuthService } from 'src/app/services/auth.service';
import { AnswerService } from 'src/app/services/answer.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css'],
})
export class QuestionDetailComponent implements OnInit {
  selected: Question | null = null;
  user: User = new User();
  answer: Answer | null = null;

  constructor(
    private questionService: QuestionService,
    private answerRatingService: AnswerRatingService,
    private answerService: AnswerService,
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.loadPage();
    this.loadUser();
  }

  updateAnswer: boolean = false;
  addAnswer: boolean = false;

  setAddAnswer() {
    this.addAnswer = true;
  }

  setUpdateAnswer(){
    this.updateAnswer = true;
  }

  //method to compare logged in user against the selected qustions user
  loadUser() {
    // if (this.selected) {
    // let question: Question = this.selected;

    this.auth.getLoggedInUser().subscribe({
      next: (data) => {
        this.user = data;
        // if(question.user && this.user.id === question.user.id){
        //   return true;
        // }
        // return false;
      },
      error: (fail) => {
        console.error('UserComponent.reload: error getting user');
        console.error(fail);
      },
    });

    // }
  }

  loadPage() {
    let routeId = this.route.snapshot.paramMap.get('id');
    console.log(routeId);
    if (!this.selected && routeId) {
      let questionId = Number.parseInt(routeId);
      if (isNaN(questionId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.questionService.show(questionId).subscribe({
          next: (data) => {
            this.selected = data;
            this.selected.answers?.sort(this.answerService.sortAnswersByRating);
          },
          error: (fail) => {
            console.error(
              'QuestionDetailComponent.ngOnInit: question not found'
            );
            this.router.navigateByUrl('questionNotFound'); //doesn't exist
          },
        });
      }
    }
  }

  loadNewAnswer() {
    if (this.selected) {
      this.questionService.show(this.selected.id).subscribe({
        next: (data) => {
          this.selected = data;
          this.addAnswer = false;
          this.selected.answers?.sort(this.answerService.sortAnswersByRating);
        },
        error: (fail) => {
          console.error('QuestionDetailComponent.ngOnInit: question not found');
          this.router.navigateByUrl('questionNotFound'); //doesn't exist
        },
      });
    }
  }

  determineRatingValue(answer: Answer) {
    let up = 0;
    let down = 0;
    if (answer.ratings) {
      let ratings: AnswerRating[] = answer.ratings;
      for (let i = 0; i < ratings.length; i++) {
        if (ratings[i].upvote) {
          up++;
        } else {
          up--;
        }
      }
    }
    return up;
  }

  voteUp(answer: Answer) {
    console.log('vote up clicked');
    let ar = new AnswerRating();
    ar.upvote = true;
    this.answerRatingService.upvote(ar, answer.id).subscribe({
      next: (data) => {
        ar = data;
        if (this.selected?.id)
          this.questionService.show(this.selected?.id).subscribe({
            next: (data) => {
              this.selected = data;
              this.selected.answers?.sort(this.answerService.sortAnswersByRating);
            },
            error: (fail) => {
              console.error(
                'QuestionDetailComponent.ngOnInit: question not found'
              );
              this.router.navigateByUrl('questionNotFound'); //doesn't exist
            },
          });
      },
      error: (fail) => {
        console.error('Answer: not found');
        this.router.navigateByUrl('AnswerNotFound'); //doesn't exist
      },
    });
  }

  // sortAnswersByRating(a1: Answer, a2: Answer): number {
  //   if (a1 && a2) {
  //     let i = 0;
  //     let r1 = a1.ratings?.reduce(
  //       (a, c) => a + (c.upvote ? 1 : c.upvote === null ? 0 : -1),
  //       i
  //     );
  //     i = 0;
  //     let r2 = a2.ratings?.reduce(
  //       (a, c) => a + (c.upvote ? 1 : c.upvote === null ? 0 : -1),
  //       i
  //     );
  //     if(r1 && r2){
  //     return r2 - r1;
  //     }
  //     else{
  //       return 0;
  //     }
  //   }
  //   else{
  //     return 0;
  //   }
  // }

  voteDown(answer: Answer) {
    console.log('vote down clicked');
    let ar = new AnswerRating();
    ar.upvote = false;
    this.answerRatingService.upvote(ar, answer.id).subscribe({
      next: (data) => {
        ar = data;
        if (this.selected?.id)
          this.questionService.show(this.selected?.id).subscribe({
            next: (data) => {
              this.selected = data;
              this.selected.answers?.sort(this.answerService.sortAnswersByRating);
            },
            error: (fail) => {
              console.error(
                'QuestionDetailComponent.ngOnInit: question not found'
              );
              this.router.navigateByUrl('questionNotFound'); //doesn't exist
            },
          });
      },
      error: (fail) => {
        console.error('Answer: not found');
        this.router.navigateByUrl('AnswerNotFound'); //doesn't exist
      },
    });
  }
}
