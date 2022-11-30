import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Question } from '../models/question';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api';

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

  index(): Observable<Question[]> {
    return this.http.get<Question[]>(this.url + "/companies", this.getHttpOptions()).pipe(
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
