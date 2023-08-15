import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { CustomResponse } from 'src/app/pages/global/interfaces/CustomResponse';
import { ResetPasswordRequest } from './interface/resetPasswordRequest';

@Injectable({
  providedIn: 'root',
})
export class ResetPasswordService {

  private readonly gateway: string = 'http://localhost:8080/ResetPasswordTools/';

  constructor(private http: HttpClient) {}

  public resetPassword(
    resetPasswordRequest: ResetPasswordRequest
  ): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(this.gateway+"ResetPassword", resetPasswordRequest);
  }
}
