import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from './Interface/LoginRequest';
import { Observable } from 'rxjs/internal/Observable';
import { LoginResponse } from './Interface/LoginResponse';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  private readonly gateway: string = 'http://localhost:8080/authentication';

  constructor(private http: HttpClient) {}

  public authenticateUser(
    loginRequest: LoginRequest
  ): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.gateway, loginRequest);
  }
}
