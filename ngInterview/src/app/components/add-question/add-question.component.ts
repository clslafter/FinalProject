import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Question } from 'src/app/models/question';
import { AuthService } from 'src/app/services/auth.service';
import { CategoryService } from 'src/app/services/category.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

   newQuestion: Question = new Question();

   categories: Category[] = [];

   selectedCategories: boolean[] = [];

   @Output() reloadParent = new EventEmitter<void>();

  constructor(private questionService: QuestionService, private router: Router,
    private auth: AuthService, private categoryService: CategoryService) { }



  ngOnInit(): void {
    this.loadCategories();
  }




  loadCategories () {
    this.categoryService.index().subscribe({
      next: (data) => {
        this.categories = data;
        this.categories.forEach(category => {
          this.selectedCategories.push(false);
        });
      },
      error: (fail) => {
        console.error('AddQuestionComponent.loadCategories: error getting categories');
        console.error(fail);
      }
    })
  }

//add error messages for required fields
    createQuestion() {
      this.newQuestion.categories = [];
      for (let i = 0; i < this.selectedCategories.length; i++) {

        if(this.selectedCategories[i]) {
          this.newQuestion.categories?.push(this.categories[i]);
        }

      }
      console.log(this.newQuestion);
      this.questionService.create(this.newQuestion).subscribe({
        next: (data) => {

          this.newQuestion = new Question();
          this.reloadParent.emit();

        },
        error: (fail) => {
          console.error('AddQuestionComponent.reload: error creating question');
          console.error(fail);
        }

      });

    }



}
