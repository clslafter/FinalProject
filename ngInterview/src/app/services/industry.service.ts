import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Industry } from '../models/industry';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class IndustryService {


  private url = environment.baseUrl + 'api/industries';

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

  index(): Observable<Industry[]> {
    return this.http.get<Industry[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('IndustryService.index():error retrieving industry list: ' + err)
        );
      })
    );
  }



}
