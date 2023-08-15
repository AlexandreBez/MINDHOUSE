import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { CustomResponse } from 'src/app/pages/global/interfaces/CustomResponse';

@Injectable({
  providedIn: 'root',
})
export class ValidateTokenService {

  private readonly gateway: string = 'http://localhost:8080/ResetPasswordTools/';

  constructor(private http: HttpClient) {}

  public validateToken(
    email: string,
    token: string
  ): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(this.gateway+"ValidateToken/"+email+"/"+token);
  }

  public resendToken(
    email: string
  ): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(this.gateway+"ResendToken/"+email);
  }
}
