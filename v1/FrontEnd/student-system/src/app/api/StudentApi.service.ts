import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Student } from './interface/Student';
import { Observable } from 'rxjs';
import { CustomResponse } from './interface/CustomResponse';

/**
 * Service responsible for managing student API requests.
 * @class
 */
@Injectable({
  providedIn: 'root',
})
export class StudentApiService {
  
  /**
   * Base URL for the API
   */
  readonly url = 'http://localhost:8084/student-management-api/v1';

  /**
   * ID for the student to be updated
   */
  public idForUpdatedStudent: number;

  /**
   *  Student name
   */
  public studentName: string;

  /**
   * ID for the student to retrieve information
   */
  public idForGetStudentInfo: number;

  /**
   * Creates an instance of StudentApiService.
   * @param {HttpClient} http - The Angular HttpClient service to perform HTTP requests.
   */
  constructor(private http: HttpClient) {}

  /**
   * Retrieves all students from the API.
   * @return {Observable<Student[]>} An observable of the array of all students.
   */
  public getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.url}/getAllStudents`);
  }

  /**
   * Retrieves a student by ID from the API.
   * @param {number} id - The ID of the student to retrieve.
   * @return {Observable<Student>} An observable of the student with the specified ID.
   */
  public getStudentById(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.url}/getStudentById/${id}`);
  }

  /**
   * Retrieves students by name from the API.
   * @param {string} name - The name of the students to retrieve.
   * @return {Observable<Student[]>} An observable of the array of students with the specified name.
   */
  public getStudentByName(name: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.url}/searchStudentByName/${name}`);
  }

  /**
   * Retrieves students by document from the API.
   * @param {string} document - The document of the students to retrieve.
   * @return {Observable<Student[]>} An observable of the array of students with the specified document.
   */
  public getStudentByDocument(document: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.url}/searchStudentByDocument/${document}`);
  }

  /**
   * Deletes a student from the API.
   * @param {number} id - The ID of the student to delete.
   * @return {Observable<Student>} An observable of the student that was deleted.
   */
  public deleteStudent(id: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.url}/deleteStudent/${id}`);
  }

  /**
   * Updates a student in the API.
   * @param {number} id - The ID of the student to update.
   * @param {Student} student - The updated student information.
   * @return {Observable<Student>} An observable of the student with the updated information.
   */
  public updateStudent(id: number, student: Student): Observable<any> {
    return this.http.put<any>(`${this.url}/updatedStudent/${id}`, student);
  }
  /**
   * Adds a new student to the API.
   * @param {Student} student - The student information to add.
   * @return {Observable<Student>} An observable of the added student.
   */
  public addStudent(student: Student): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(`${this.url}/addNewStudent`, student);
  }

}
