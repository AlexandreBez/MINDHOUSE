import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Grade } from '../interface/Grade';

/**
 * Service responsible for managing grade API requests.
 * @class
 */
@Injectable({
  providedIn: 'root',
})
export class GradeApiService {

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
   * Gets the student's grades by student ID.
   * @param id The ID of the student.
   * @returns An observable of CustomGrade[].
   */
  public getStudentGradesByStudentId(id: number): Observable<Grade[]> {
    return this.http.get<Grade[]>(
      `${this.url}getStudentGradesByStudentId/${id}`
    );
  }

  /**
   * Gets the student's grades by student ID and course name.
   * @param id The ID of the student.
   * @param name The name of the course.
   * @returns An observable of CustomGrade[].
   */
  public getStudentGradesByStudentIdAndCourseName(
    id: number,
    name: string
  ): Observable<Grade[]> {
    return this.http.get<Grade[]>(
      `${this.url}getStudentGradesByStudentIdAndCourseName/${id}/${name}`
    );
  }

  /**
   * Gets the student's grades by grade ID and sends an email with the information.
   * @param id The ID of the grade.
   * @returns An observable of null.
   */
  public getStudentGradesByGradeIdAndSendEmailWithInfo(
    id: number
  ): Observable<null> {
    return this.http.get<null>(
      `${this.url}getStudentGradesByGradeIdAndSendEmailWithInfo/${id}`
    );
  }
}
