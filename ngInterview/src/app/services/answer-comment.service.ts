import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AnswerComment } from '../models/answer-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AnswerCommentService {

  private url = environment.baseUrl + 'api/comments';

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

  index(): Observable<AnswerComment[]> {
    return this.http.get<AnswerComment[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('AnswerCommentService.index():error retrieving answerComment list: ' + err)
        );
      })
    );
  }

  create(comment: AnswerComment, answerId: number) {
    return this.http.post<AnswerComment>(this.url + "/" + answerId, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AnswerCommentService.create():error creating answerComment: ' + err)
        );
      })
    );
    }

    update(answerComment: AnswerComment, answerCommentId: number): Observable<AnswerComment> {
      return this.http.put<AnswerComment>(this.url + '/' + answerCommentId, answerComment, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'AnswerCommentService.update(): error updating answerComment: ' + err )
          );
        })
      );
      }

      destroy(id: number): Observable<void> {
        return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.error(err);
            return throwError(
               () => new Error( 'AnswerCommentService.destroy(): error deleting answerComment: ' + err )
            );
          })
        );
      }

    }
