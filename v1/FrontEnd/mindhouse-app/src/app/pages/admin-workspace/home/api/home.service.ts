import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { GraphicDataResponse } from './interface/graphicDataResponse';
import { CustomResponse } from 'src/app/pages/global/interfaces/CustomResponse';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  private readonly gateway: string = 'http://localhost:8080/DataView/v1/';

  get_auth_data: any = localStorage.getItem('AUTH_COOKIE');
  decripted_data: any = atob(this.get_auth_data);
  parsed_data: any = JSON.parse(this.decripted_data);

  authHeaders = new HttpHeaders({
    Authorization: 'Bearer ' + this.parsed_data.token,
    Role: this.parsed_data.role,
  });

  constructor(private http: HttpClient) {}

  public getAmountOfRolesGraphicData(): Observable<GraphicDataResponse[]> {
    return this.http.get<GraphicDataResponse[]>(
      this.gateway + 'getAmountOfRolesGraphicData',
      {
        headers: this.authHeaders,
      }
    );
  }

  public getListOfEmails(): Observable<String[]> {
    return this.http.get<String[]>(
      this.gateway + 'getListOfEmails',
      {
        headers: this.authHeaders,
      }
    );
  }

  downloadExcelUserReport() {
    return this.http.get(this.gateway + 'downloadExcelUserReport',
    {
      headers: this.authHeaders,
      responseType: 'blob' 
    });
  }

  sendExcelUserReportForEmails(emails: string[]): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(this.gateway + 'sendExcelUserReportForEmails', emails, 
    {
      headers: this.authHeaders,
    });
  }
  
}
