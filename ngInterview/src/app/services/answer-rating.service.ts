import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { AnswerRating } from '../models/answer-rating';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AnswerRatingService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/answerrating';

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

  upvote(answerRating: AnswerRating, answerId: number){
    return this.http.put<AnswerRating>(this.url + "/" + answerId, answerRating, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.create():error creating Todo: ' + err)
        );
      })
    );
    }


  downvote(answerRating: AnswerRating, answerId: number){
    return this.http.put<AnswerRating>(this.url + "/" + answerId, answerRating, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.create():error creating Todo: ' + err)
        );
      })
    );
    }
  }



