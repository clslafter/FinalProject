import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Answer } from '../models/answer';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'answers';

  constructor(private http: HttpClient) { }

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

  create(answer: Answer) {
    return this.http.post<Answer>(this.url, answer).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.create():error creating Todo: ' + err)
        );
      })
    );
    }
  }
