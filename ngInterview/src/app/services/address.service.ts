import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private url = environment.baseUrl + 'api/addresses';

  constructor(private http: HttpClient,
    private auth: AuthService) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic ' + this.auth.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }


    create(address: Address): Observable<Address> {
      return this.http.post<Address>(this.url, address, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'AddressService.create(): error creating address: ' + err )
          );
        })
      );
    }


    show(addressId: number): Observable<Address> {
      return this.http.get<Address>(this.url+ '/' +addressId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('AddressService.index(): error retrieving address: ' + err)
          );
        })
      );
    }

    update(id: number, address: Address): Observable<User> {
      return this.http.put<User>(this.url + '/' + id, address, this.getHttpOptions()).pipe(
        catchError((err:any)=>{
          console.error(err);
          return throwError(
            ()=> new Error('AddressService.update(): error updating Address: ' +err)
          );
        })
      );
    }
}

