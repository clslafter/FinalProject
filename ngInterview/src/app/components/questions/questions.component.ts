import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Question } from 'src/app/models/question';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
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

  user: User = new User();

  constructor(private questionService: QuestionService, private router: Router, private categoryService: CategoryService,
    private auth: AuthService) { }

  ngOnInit(): void {
    this.loadQuestions();
    this.loadCategories();
    this.loadUser();
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


  deleteQuestion(id: number){
    if(confirm("Are you sure you want to delete your question?")){
    this.questionService.destroy(id).subscribe({
      next: (data: any) => {
        this.loadQuestions();
        this.loadCategories();
        this.loadUser();
        },
      error: (fail: any) => {
        console.error(
          'QuestionsComponent.disableQuestion(): error disabling question:'
        );
        console.error(fail);
      },
    });
  }
  }

}

