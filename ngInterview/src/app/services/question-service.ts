import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Answer } from '../models/answer';
import { Question } from '../models/question';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private url2 = environment.baseUrl + 'api/questions';

  private url = environment.baseUrl + 'questions';

  constructor(private http: HttpClient, private auth: AuthService
    ) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic ' + this.auth.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }

  addQuestionToCompany(questionId: number, companyId: number){
  return this.http.put( environment.baseUrl + 'api' + '/companies/' + companyId + '/question/' + questionId, null, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('QustionService.index(): error retrieving question: ' + err)
      );
    })
  );
}

removeQuestionFromCompany(questionId: number, companyId: number){
  return this.http.put(environment.baseUrl + 'api/companies/' + companyId + '/question/' + questionId + '/remove', null, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('QustionService.index(): error retrieving question: ' + err)
      );
    })
  );
}

  index(): Observable<Question[]> {
    return this.http.get<Question[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('QuestionService.index():error retrieving question list: ' + err)
        );
      })
    );
  }
  show(qId: number): Observable<Question> {
    return this.http.get<Question>(this.url2 + '/' + qId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('QustionService.index(): error retrieving question: ' + err)
        );
      })
    );
  }

  create(question: Question): Observable<Question> {
    return this.http.post<Question>( this.url2, question, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'QuestionService.create(): error creating question: ' + err )
        );
      })
    );
  }

  update(question: Question): Observable<Question> {
    return this.http.put<Question>(this.url2 + '/' + question.id, question, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'QuestionService.update(): error updating question: ' + err )
        );
      })
    );
    }



  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url2 + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'QuestionService.destroy(): error deleting question: ' + err )
        );
      })
    );
  }

}

