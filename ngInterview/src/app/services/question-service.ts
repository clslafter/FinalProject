import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Question } from '../models/question';


@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'questions';

  constructor(private http: HttpClient,
    ) { }



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
}
