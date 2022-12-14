import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/admin/admin.component';
import { CompanyDetailComponent } from './components/company-detail/company-detail.component';
import { CompanyComponent } from './components/company/company.component';
import { EditCompanyComponent } from './components/edit-company/edit-company.component';
import { EditQuestionComponent } from './components/edit-question/edit-question.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { QuestionDetailComponent } from './components/question-detail/question-detail.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { RegisterComponent } from './components/register/register.component';
import { UserAccountComponent } from './components/user-account/user-account.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'welcome' },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'userAccount', component: UserAccountComponent },
  { path: 'userProfile/:id', component: UserProfileComponent },
  { path: 'questions', component: QuestionsComponent },
  { path: 'questionDetail/:id', component: QuestionDetailComponent },
  { path: 'editQuestion/:id', component: EditQuestionComponent },
  { path: 'companies', component: CompanyComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'companyDetail/:id', component: CompanyDetailComponent },
  { path: 'editCompany/:id', component: EditCompanyComponent },

  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

