import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Address } from '../models/address';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8090/api/';
  private url = this.baseUrl + 'users';

  constructor(private http: HttpClient,
    private auth: AuthService
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

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('UserService.index():error retrieving user list: ' + err)
        );
      })
    );
  }

  show(userId: number): Observable<User> {
    return this.http.get<User>(this.url+ '/' +userId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.index(): error retrieving user: ' + err)
        );
      })
    );
  }

  update(user: User): Observable<User> {
    return this.http.put<User>(this.url, user, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.error(err);
        return throwError(
          ()=> new Error('UserService.update(): error updating User: ' +err)
        );
      })
    );
  }
//add error messages for Address
  addAddress(addressId: number): Observable<User> {
    return this.http.put<User>(this.url + '/addresses/'+ addressId, null, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.error(err);
        return throwError(
          ()=> new Error('User.addAddress(): error adding address to User: ' +err)
        );
      })
    );
  }



  disable(id: number): Observable<User> {
    return this.http.delete<User>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.error(err);
        return throwError(
          ()=> new Error('User.delete(): error deleting User: ' +err)
        );
      })
    );
  }

  enable(id: number): Observable<User> {
    return this.http.put<User>(this.url + '/enable/' + id, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.error(err);
        return throwError(
          ()=> new Error('User.delete(): error deleting User: ' +err)
        );
      })
    );
  }

}

