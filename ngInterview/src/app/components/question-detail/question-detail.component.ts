import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  // loadQuestion () {
  //   this.questionService.show(1).subscribe({ //hardcoded to #1 until we have a button that passes a question id
  //     next: (data) => {
  //       this.selected = data;
  //     },
  //     error: (fail) => {
  //       console.error('Question-DetailComponent.loadQuestion: error getting question');
  //       console.error(fail);
  //     }
  //   })
  // }

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
}
