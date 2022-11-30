import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/models/question';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent implements OnInit {


  selectedQuestion: Question | null = null;

  constructor(private questionService: QuestionService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.loadPage();
  }



  loadPage(){
    let routeId = this.route.snapshot.paramMap.get('id');
    console.log(routeId);
    if (!this.selectedQuestion && routeId) {
      let questionId = Number.parseInt(routeId);
      if(isNaN(questionId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.questionService.show(questionId).subscribe({
          next: (data) => {
            this.selectedQuestion = data;
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

