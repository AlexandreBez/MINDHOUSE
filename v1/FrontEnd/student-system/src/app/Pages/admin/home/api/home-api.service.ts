import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsersQtdRolesData } from './interface/userQtdRolesData';
import { CustomResp } from 'src/app/Pages/components/Services/interface/customResp';

/**
 * Service for interacting with the home API.
 */
@Injectable({
  providedIn: 'root',
})
export class HomeApiService {
  /**
   * The base URL for the API endpoints(Gateway Endpoint).
   */
  private readonly url: string =
    'http://localhost:8084/administration-workstation/Business-Inteligence/v1/';
  
  /**
   * The authorization headers for making authenticated requests.
   */
  authHeaders = new HttpHeaders({
    Authorization: 'Bearer ' + localStorage.getItem('token'),
    Role: localStorage.getItem('role'),
  });

  /**
   * Constructs a new instance of the UserApiService.
   * @param {HttpClient} http - The HttpClient instance for making HTTP requests.
   */
  constructor(private http: HttpClient) {}

  /**
   * Get the quantity of users' roles data for the dashboard on the admin page.
   * @return {Observable<UsersQtdRolesData[]>} An observable of the role/quantity data received.
   */
  public getUserQtdRolesData(): Observable<UsersQtdRolesData[]> {
    return this.http.get<UsersQtdRolesData[]>(this.url + '/getUserQtdData', {
      headers: this.authHeaders,
    });
  }

  /**
   * Sends an email with the users' roles quantity data attached.
   * @param {number} id - The user ID.
   * @return {Observable<CustomResp>} An observable of the response received.
   */
  public sendEmailWithRolesQtdData(id: number): Observable<CustomResp> {
    return this.http.get<CustomResp>(
      this.url + '/sendUserQtdRolesDataFileTiEmail/' + id,
      { headers: this.authHeaders }
    );
  }
}
