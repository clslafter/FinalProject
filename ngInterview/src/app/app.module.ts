import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { AuthService } from './services/auth.service';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LogoutComponent } from './components/logout/logout.component';
import { UserAccountComponent } from './components/user-account/user-account.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { CategoryPipe } from './pipes/category.pipe';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { QuestionDetailComponent } from './components/question-detail/question-detail.component';
import { AnswerFormComponent } from './components/answer-form/answer-form.component';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { AdminComponent } from './components/admin/admin.component';
import { EditQuestionComponent } from './components/edit-question/edit-question.component';
import { CompanyComponent } from './components/company/company.component';
import { CompanyDetailComponent } from './components/company-detail/company-detail.component';
import { EditAnswerComponent } from './components/edit-answer/edit-answer.component';
import { AlphabetizePipe } from './pipes/alphabetize.pipe';
import { EnabledQuestionPipe } from './pipes/enabled-question.pipe';
import { AddCompanyComponent } from './components/add-company/add-company.component';
import { EditCompanyComponent } from './components/edit-company/edit-company.component';
import { EnabledCompanyPipe } from './pipes/enabled-company.pipe';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    WelcomeComponent,
    LoginComponent,
    RegisterComponent,
    NotFoundComponent,
    LogoutComponent,
    UserAccountComponent,
    QuestionsComponent,
    CategoryPipe,
    UserProfileComponent,
    QuestionDetailComponent,
    AnswerFormComponent,
    AddQuestionComponent,
    AdminComponent,
    EditQuestionComponent,
    CompanyComponent,
    CompanyDetailComponent,
    EditAnswerComponent,
    AlphabetizePipe,
    EnabledQuestionPipe,
    AddCompanyComponent,
    EditCompanyComponent,
    EnabledCompanyPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
