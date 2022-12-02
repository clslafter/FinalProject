import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Company } from '../models/company';
import { Question } from '../models/question';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private url = environment.baseUrl + 'api/companies';

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

  index(): Observable<Company[]> {
    return this.http.get<Company[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('QuestionService.index():error retrieving question list: ' + err)
        );
      })
    );
  }

  show(cId: number): Observable<Company> {
    return this.http.get<Company>(this.url+ '/' + cId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CompanyService.index(): error retrieving company: ' + err)
        );
      })
    );
  }

  create(company: Company): Observable<Company> {
    return this.http.post<Company>(this.url, company, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'CompanyService.create(): error creating company: ' + err )
        );
      })
    );
  }

  update(company: Company): Observable<Company> {
    return this.http.put<Company>(this.url + '/' + company.id, company, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'CompanyService.update(): error updating company: ' + err )
        );
      })
    );
    }



  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'CompanyService.destroy(): error deleting company: ' + err )
        );
      })
    );
  }


}

