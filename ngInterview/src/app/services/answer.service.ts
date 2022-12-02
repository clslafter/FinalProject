import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Answer } from '../models/answer';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  private url = environment.baseUrl + 'api/answers';

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

    update(answer: Answer, answerId: number): Observable<Answer> {
      return this.http.put<Answer>(this.url + '/' + answerId, answer, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'AnswerService.update(): error updating answer: ' + err )
          );
        })
      );
      }

      destroy(id: number): Observable<void> {
        return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.error(err);
            return throwError(
               () => new Error( 'AnswerService.destroy(): error deleting answer: ' + err )
            );
          })
        );
      }

      sortAnswersByRating(a1: Answer, a2: Answer): number {
        if (a1 && a2) {
          let i = 0;
          let r1 = a1.ratings?.reduce(
            (a, c) => a + (c.upvote ? 1 : c.upvote === null ? 0 : -1),
            i
          );
          i = 0;
          let r2 = a2.ratings?.reduce(
            (a, c) => a + (c.upvote ? 1 : c.upvote === null ? 0 : -1),
            i
          );
          if(r1 && r2){
          return r2 - r1;
          }
          else{
            return 0;
          }
        }
        else{
          return 0;
        }
      }
  }
