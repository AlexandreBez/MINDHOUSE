import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginReqModel } from './interface/loginReq';
import { Observable } from 'rxjs';
import { LoginResModel } from './interface/loginRes';

@Injectable({
  providedIn: 'root'
})
export class AuthAPIService {

  private readonly url: string = "http://localhost:8084/users"

  constructor(private http: HttpClient) {}

    /**
   * Try to login with the data with a req to the API.
   * @param {LoginReqModel} loginReqModel - The username/password information to send.
   * @return {Observable<Student>} An observable of the username/password sent.
   */
    public login(loginReqModel: LoginReqModel): Observable<LoginResModel> {
      return this.http.post<LoginResModel>(this.url+"/login", loginReqModel);
    }

}
