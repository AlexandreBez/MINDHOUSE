import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from './interface/User';
import { CustomResponse } from 'src/app/pages/global/interfaces/CustomResponse';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private readonly gateway: string = 'http://localhost:8080/UserTools/v1/';

  get_auth_data: any = localStorage.getItem('AUTH_COOKIE');
  decripted_data: any = atob(this.get_auth_data);
  parsed_data: any = JSON.parse(this.decripted_data);

  authHeaders = new HttpHeaders({
    Authorization: 'Bearer ' + this.parsed_data.token,
    Role: this.parsed_data.role,
  });

  constructor(private http: HttpClient) {}

  public createNewUSer(user: User): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(
      this.gateway + 'saveUser',
      user,
      {
        headers: this.authHeaders,
      }
    );
  }
  
}
