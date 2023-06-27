import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Userlist } from './interface/Userlist';
import { Observable } from 'rxjs';
import { SearchFilter } from './interface/SearchFilter';
import { CustomResp } from 'src/app/Pages/components/Services/interface/customResp';
import { User } from './interface/User';

/**
 * Service for interacting with the user API.
 */
@Injectable({
  providedIn: 'root',
})
export class UserApiService {

  /**
   * The base URL for the API endpoints(Gateway Endpoint).
   */
  private readonly url: string = 'http://localhost:8084/administration-workstation/users/v1/';
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
   * Get the list of users for the user list.
   * @return {Observable<Userlist[]>} An observable of the users received.
   */
  public getAllUsers(): Observable<Userlist[]> {
    return this.http.get<Userlist[]>(
      this.url + 'getAllUsers',
      {
        headers: this.authHeaders,
      }
    );
  }

  /**
   * Search for users based on the specified filter.
   * @param {SearchFilter} searchConfig - The search filter configuration.
   * @return {Observable<Userlist[]>} An observable of the users received.
   */
  public searchUserByFilter(
    searchConfig: SearchFilter
  ): Observable<Userlist[]> {
    return this.http.post<Userlist[]>(
      this.url + 'searchUserByFilter',
      searchConfig,
      { headers: this.authHeaders }
    );
  }

  /**
   * Delete a user by their ID.
   * @param {number} id - The ID of the user to delete.
   * @return {Observable<CustomResp>} An observable representing the deletion result.
   */
  public deleteUser(id: number): Observable<CustomResp> {
    return this.http.delete<CustomResp>(this.url + 'deleteUser/' + id, {
      headers: this.authHeaders,
    });
  }

  /**
   * Create a new user.
   * @param {User} user - The user object containing the user's details.
   * @return {Observable<CustomResp>} An observable representing the creation result.
   */
  public createNewUser(user: User): Observable<CustomResp> {
    return this.http.post<CustomResp>(this.url + 'createNewUser/', user, {
      headers: this.authHeaders,
    });
  }

  /**
   * Get a user by their ID.
   * @param {number} id - The ID of the user to retrieve.
   * @return {Observable<User>} An observable representing the user data.
   */
  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.url + 'getUserById/' + id, {
      headers: this.authHeaders,
    });
  }

  /**
   * Update a user by their ID.
   * @param {number} id - The ID of the user to update.
   * @param {User} user - The updated user object containing the user's details.
   * @return {Observable<CustomResp>} An observable representing the update result.
   */
  public updateUser(id:number, user: User): Observable<CustomResp> {
    return this.http.put<CustomResp>(this.url + 'updateUser/'+id, user, {
      headers: this.authHeaders,
    });
  }

}
