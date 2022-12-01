import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Question } from 'src/app/models/question';
import { AuthService } from 'src/app/services/auth.service';
import { CategoryService } from 'src/app/services/category.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent implements OnInit {



  editQuestion: Question | null = null;
  categories: Category[] = [];

   selectedCategories: boolean[] = [];

  constructor(private questionService: QuestionService, private route: ActivatedRoute, private router: Router, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loadQuestion();
    this.loadCategories();
  }


  clearEditQuestion(): void {
    this.editQuestion = null;
  }

  backToDetail () {
    this.router.navigateByUrl(`/questionDetail/${this.editQuestion?.id}`);
    this.clearEditQuestion();


  }

  loadQuestionCategories() {
    if (this.editQuestion?.categories) {
    for (let i = 0; i < this.editQuestion.categories.length; i++) {
      for (let j = 0; j < this.categories.length; j++) {
        if(this.editQuestion.categories[i].name === this.categories[j].name) {
          this.selectedCategories[j] = true;
        }

      }


    }

  }
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

  loadCategories () {
    this.categoryService.index().subscribe({
      next: (data) => {
        this.categories = data;
        this.categories.forEach(category => {
          this.selectedCategories.push(false);
        });
        this.loadQuestionCategories();
        },
        error: (fail) => {
          console.error('EditQuestionComponent.loadCategories: error getting categories');
          console.error(fail);
        }
      })
    }

    updateQuestion(): void {
      if (this.editQuestion) {
      this.editQuestion.categories = [];
      for (let i = 0; i < this.selectedCategories.length; i++) {

        if(this.selectedCategories[i]) {
          this.editQuestion?.categories?.push(this.categories[i]);
        }

      }
      console.log(this.editQuestion);

      this.questionService.update(this.editQuestion).subscribe({
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

}






