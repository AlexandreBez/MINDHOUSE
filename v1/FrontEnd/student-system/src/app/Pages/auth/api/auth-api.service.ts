import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginReqModel } from './interface/loginReq';
import { Observable } from 'rxjs';
import { LoginResModel } from './interface/loginRes';
import { CustomResp } from '../../components/Services/interface/customResp';
import { ResetPwdHelper } from './interface/resetPwdHelper';

/**
 * Service for handling authentication API requests.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthAPIService {
  /**
   * The base URL of the authentication API.
   */
  private readonly url: string = 'http://localhost:8084/reset-password/v1/';

  /**
   * Constructs an instance of the AuthAPIService.
   *
   * @param {HttpClient} http - The HttpClient instance for making HTTP requests.
   */
  constructor(private http: HttpClient) {}

  /**
   * @param {LoginReqModel} loginReqModel - The username/password information to send.
   * Sends a login request to the authentication API.
   * @returns {Observable<LoginResModel>} An observable of the login response.
   */
  public login(loginReqModel: LoginReqModel): Observable<LoginResModel> {
    return this.http.post<LoginResModel>(
      'http://localhost:8084/authentication',
      loginReqModel
    );
  }

  /**
   * @param {string} email - The email to send the token.
   * Sends a token request to the authentication API for
   * generate a token and send by email.
   * @returns {Observable<CustomResp>} An observable of the response.
   */
  public sendToken(email: string): Observable<CustomResp> {
    return this.http.get<CustomResp>(
      this.url + 'SendToken/' + email
    );
  }

  /**
   * @param {string} token - The token send by the user.
   * Sends the token typed by the user to the authentication API
   * and check if the token is valid.
   * @returns {Observable<CustomResp>} An observable of the response.
   */
  public validateToken(token: string, email: string): Observable<CustomResp> {
    return this.http.get<CustomResp>(
      this.url + 'ValidateToken/' + token + '/' + email
    );
  }

    /**
   * @param {ResetPwdHelper} resetPwdHelper - The object to help handle the reset password request.
   * Sends the token, email and password typed by the user to the authentication API
   * and reset the password.
   * @returns {Observable<CustomResp>} An observable of the response.
   */
    public resetPassword(resetPwdHelper: ResetPwdHelper): Observable<CustomResp> {
      return this.http.post<CustomResp>(
        this.url + 'ResetPassword/',
        resetPwdHelper
      );
    }
}
