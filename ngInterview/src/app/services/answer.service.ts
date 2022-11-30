import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Answer } from '../models/answer';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/answers';

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Answer[]> {
    return this.http.get<Answer[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('AnswerService.index():error retrieving question list: ' + err)
        );
      })
    );
  }

  create(answer: Answer, questionId: number) {
    return this.http.post<Answer>(this.url + "/" + questionId, answer, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.create():error creating Todo: ' + err)
        );
      })
    );
    }

    update(answer: Answer): Observable<Answer> {
      return this.http.put<Answer>(this.url + '/' + answer.id, answer, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'AnswerService.update(): error updating answer: ' + err )
          );
        })
      );
      }
  }
