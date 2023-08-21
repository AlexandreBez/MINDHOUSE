import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from './interface/User';
import { CustomResponse } from 'src/app/pages/global/interfaces/CustomResponse';
import { UsersTableData } from './interface/UserTableData';

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

  public createNewUser(user: User): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(
      this.gateway + 'saveUser',
      user,
      {
        headers: this.authHeaders,
      }
    );
  }
  
  public userTableData(): Observable<UsersTableData[]> {
    return this.http.get<UsersTableData[]>(
      this.gateway + 'getUsersTableData',
      {
        headers: this.authHeaders,
      }
    );
  }

  public filterSearch(type: string, data: string): Observable<UsersTableData[]> {
    return this.http.get<UsersTableData[]>(
      this.gateway + 'filterSearch/'+type+"/"+data,
      {
        headers: this.authHeaders,
      }
    );
  }

  public deleteUser(id: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(
      this.gateway + 'deleteUser/'+id,
      {
        headers: this.authHeaders,
      }
    );
  }

  public updateUser(user: User): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(
      this.gateway + 'updateUser',
      user,
      {
        headers: this.authHeaders,
      }
    );
  }
}
