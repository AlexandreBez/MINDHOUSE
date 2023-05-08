import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from './interface/Payment';

/**
 * Service responsible for managing payment API requests.
 * @class
 */
@Injectable({
  providedIn: 'root',
})
export class PaymentApiService {
  
  /**
   * The base URL for the API endpoints.
   */
  private readonly url = 'http://localhost:8084/student-management-api/v1/';

  /**
   * Constructs a new instance of the StudentApiService.
   * 
   * @param http - The HttpClient used to perform HTTP requests.
   */
  constructor(private http: HttpClient) {}

  /**
   * Fetches all payments for the specified student ID.
   * 
   * @param id - The ID of the student to fetch payments for.
   * @returns An Observable of an array of CustomPayment objects.
   */
  getPaymentsByStudentId(id: number): Observable<Payment[]> {
    return this.http.get<Payment[]>(
      `${this.url}getPaymentsByStudentId/${id}`
    );
  }

  /**
   * Fetches all payments for the specified student ID within a specified date range.
   * 
   * @param id - The ID of the student to fetch payments for.
   * @param from - The start date of the date range.
   * @param until - The end date of the date range.
   * @returns An Observable of an array of CustomPayment objects.
   */
  getPaymentsByStudentIdAndReceipDate(
    id: number,
    from: string,
    until: string
  ): Observable<Payment[]> {
    return this.http.get<Payment[]>(
      `${this.url}getPaymentsByStudentIdAndReceipDate/${id}/${from}/${until}`
    );
  }

  /**
   * Fetches payment data for the specified payment ID and sends it by email.
   * 
   * @param id - The ID of the payment to fetch and send.
   * @returns An Observable of null.
   */
  getPaymentsByIdAndSendByEmail(id: number): Observable<null> {
    return this.http.get<null>(
      `${this.url}getPaymentsByIdAndSendByEmail/${id}`
    );
  }
}
