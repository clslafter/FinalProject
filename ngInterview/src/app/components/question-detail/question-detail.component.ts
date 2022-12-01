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
import { CompanyService } from 'src/app/services/company.service';
import { Company } from 'src/app/models/company';

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css'],
})
export class QuestionDetailComponent implements OnInit {
  selected: Question | null = null;
  user: User = new User();
  answer: Answer | null = null;
  companies: Company [] = [];
  selectedCompanyID: number = 0;
  // selectedCompanies: Company [] = [];

  constructor(
    private questionService: QuestionService,
    private answerRatingService: AnswerRatingService,
    private answerService: AnswerService,
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    this.loadPage();
    this.loadUser();
    this.loadCompanies();

  }

  updateAnswer: Answer | null = null;
  addAnswer: boolean = false;

  setAddAnswer() {
    this.addAnswer = true;
  }

  cancelAddAnswer() {
    this.addAnswer = false;
  }

  setUpdateAnswer(answer: Answer){
    this.updateAnswer = Object.assign({},answer);

  }

  answerToUpdate(answer: Answer){
    console.log("clicked")
    if(this.updateAnswer){
      delete this.updateAnswer.user;
    this.answerService.update(this.updateAnswer, this.updateAnswer.id).subscribe({
      next: (data: any) => {
          this.updateAnswer = data;
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

// setSelectedCompanies () {
//   if(this.selected) {
//     this.selected.companies = this.selectedCompanies;
//   }
// }

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
            console.log('********************')
            console.log(this.selected)
            console.log('********************')
            this.selected.answers?.sort(this.answerService.sortAnswersByRating);
            // this.setSelectedCompanies();
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

  loadCompanies(){
    this.companyService.index().subscribe({
      next: (data: any) => {
        this.companies = data;
      },
      error: (fail: any) => {
        console.error('QuestionComponent.loadQuestions: error getting questions');
        console.error(fail);
      }
    })
  }

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


  deleteQuestion(id: number){
    if(confirm("Are you sure you want to delete your question?")){
    this.questionService.destroy(id).subscribe({
      next: (data: any) => {
        this.selected = null;
        this.router.navigateByUrl('questions')
        },
      error: (fail: any) => {
        console.error(
          'QuestionDetailComponent.disableQuestion(): error disabling question:'
        );
        console.error(fail);
      },
    });
  }
  }

  deleteAnswer(id: number){
  if(confirm("Are you sure you want to delete your Answer?")){
    this.answerService.destroy(id).subscribe({
      next: (data: any) => {
        this.answer = null;
        this.loadNewAnswer();

        },
      error: (fail: any) => {
        console.error(
          'QuestionDetailComponent.disableAnswer(): error disabling answer:'
        );
        console.error(fail);
      },
    });
  }
}

associateQuestionWithCompany(){
  if(this.selected?.id){
  this.questionService.addQuestionToCompany(this.selected?.id, this.selectedCompanyID).subscribe({
    next: (data: any) => {
      this.loadNewAnswer();
      },
    error: (fail: any) => {
      console.error(
        'QuestionDetailComponent.associateQuestionWithCompany(): error associating:'
      );
      console.error(fail);
    },
  });
}
}

unassociateQuestionWithCompany(companyId: number){
  if(this.selected?.id){
  this.questionService.removeQuestionFromCompany(this.selected?.id, companyId).subscribe({
    next: (data: any) => {
      this.loadNewAnswer();
      },
    error: (fail: any) => {
      console.error(
        'QuestionDetailComponent.associateQuestionWithCompany(): error unassociating:'
      );
      console.error(fail);
    },
  });
}
}
}


