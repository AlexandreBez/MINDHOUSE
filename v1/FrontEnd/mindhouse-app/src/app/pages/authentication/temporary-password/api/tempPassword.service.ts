import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { TempPasswordRequest } from './Interface/TempPasswordRequest';
import { CustomResponse } from 'src/app/pages/global/interfaces/CustomResponse';

@Injectable({
  providedIn: 'root',
})
export class TempPasswordService {

  get_auth_cookie_data = localStorage.getItem('AUTH_COOKIE');
  decripted_data = atob(this.get_auth_cookie_data);
  parsed_data = JSON.parse(this.decripted_data);

  authHeaders = new HttpHeaders({
    Authorization: 'Bearer ' + this.parsed_data.token
  });

  private readonly gateway: string = 'http://localhost:8080/TemporaryPassword';

  constructor(private http: HttpClient) {}

  public changeTemporaryPassword(
    tempPassword: TempPasswordRequest
  ): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(this.gateway, tempPassword,{
      headers: this.authHeaders
    });
  }
}
