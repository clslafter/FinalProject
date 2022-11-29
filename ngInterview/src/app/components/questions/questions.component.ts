import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Question } from 'src/app/models/question';
import { CategoryService } from 'src/app/services/category.service';
import { QuestionService } from 'src/app/services/question-service';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  questions: Question[] = [];

  categories: Category[] = [];

  selectedCategory = 'all';

  addQuestion: boolean = false;

  constructor(private questionService: QuestionService, private router: Router, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loadQuestions();
    this.loadCategories();
  }

  questionAdded() {
    this.loadQuestions();
    this.loadCategories();
    this.addQuestion = false;
  }

  setAddQuestion(){
    this.addQuestion = true;
    }

  loadQuestions () {
    this.questionService.index().subscribe({
      next: (data) => {
        this.questions = data;
      },
      error: (fail) => {
        console.error('QuestionComponent.loadQuestions: error getting questions');
        console.error(fail);
      }
    })
  }

  loadCategories () {
    this.categoryService.index().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (fail) => {
        console.error('QuestionComponent.loadCategories: error getting categories');
        console.error(fail);
      }
    })
  }

}
