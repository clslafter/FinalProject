import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/models/question';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent implements OnInit {



  editQuestion: Question | null = null;

  constructor(private questionService: QuestionService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.loadQuestion();
  }


  clearEditQuestion(): void {
    this.editQuestion = null;
  }

  loadQuestion(){
    let routeId = this.route.snapshot.paramMap.get('id');
    console.log(routeId);
    if (!this.editQuestion && routeId) {
      let questionId = Number.parseInt(routeId);
      if(isNaN(questionId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.questionService.show(questionId).subscribe({
          next: (data) => {
            this.editQuestion = data;

          },
          error: (fail) => {
            console.error('EditQuestionComponent.ngOnInit: question not found');
            this.router.navigateByUrl('questionNotFound'); //doesn't exist
          }
        });
      }
    }
    }

    updateQuestion(question: Question): void {
      this.questionService.update(question).subscribe({
          next: (data: any) => {
              this.editQuestion = data;
              this.router.navigateByUrl(`/questionDetail/${this.editQuestion?.id}`);
              this.clearEditQuestion();
            },
          error: (fail: any) => {
            console.error(
              'EditQuestionComponent.updateQuestion(): error updating question:'
            );
            console.error(fail);
          },
        });
      }

}



