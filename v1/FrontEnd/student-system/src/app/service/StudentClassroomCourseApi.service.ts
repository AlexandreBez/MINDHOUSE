import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { StudentClassroomCourse } from "../interface/StudentClassroomCourse";

/**
 * Service responsible for managing student_classroom_course API requests.
 * @class
 */
@Injectable({
  providedIn: 'root',
})
export class StudentClassroomCourseApiService {

  /**
   * The base URL for the API.
   */
  readonly url = "http://localhost:8084/student-management-api/v1/";

  /**
   * Constructs a new StudentClassroomCourseApiService.
   *
   * @param http - The HttpClient to use for API requests.
   */
  constructor(private http: HttpClient) { }

  /**
   * Returns a list of courses for a given student ID.
   * @param {number} id - The student ID.
   * @returns {Observable<Student_Classroom_Course[]>} - An observable of a list of courses for the student.
   */
  public getStudentCoursesByStudentId(id: number): Observable<StudentClassroomCourse[]> {
  return this.http.get<StudentClassroomCourse[]>(this.url + 'getStudentCoursesByStudentId/' + id);
  }
  
  /**
   * Returns a list of courses for a given course name and student ID.
   * @param {number} id - The student ID.
   * @param {string} name - The name of the course.
   * @returns {Observable<Student_Classroom_Course[]>} - An observable of a list of courses for the given course name and student ID.
  */
  public getStudentCoursesByCourseName(id: number, name: string): Observable<StudentClassroomCourse[]> {
  return this.http.get<StudentClassroomCourse[]>(this.url + 'getStudentCoursesByCourseName/' + id + "/" + name);
  }
  
  /**
   * Deletes a student course.
   * @param {number} id - The student course ID.
   * @returns {Observable<null>} - An observable of a null value indicating success.
   */
  public deleteStudentCourse(student_id: number, payment_id: number, grade_id: number): Observable<null> {
  return this.http.get<null>(this.url + 'cancelStudentCourse/' + student_id +"/"+payment_id+"/"+grade_id);
  }
  
  /**
   * Returns a list of full course and classroom information for a given student ID.
   * @param {number} id - The student ID.
   * @returns {Observable<Student_Classroom_Course[]>} - An observable of a list of full course and classroom information for the given student ID.
   */
  public getClassroomFullInfo(id: number): Observable<StudentClassroomCourse[]> {
  return this.http.get<StudentClassroomCourse[]>(this.url + 'getFullCourseAndClassroomInfo/' + id );
  }
  
  /**
   * Sends an email with all course information for a given student ID.
   * @param {number} id - The student ID.
   * @returns {Observable<null>} - An observable of a null value indicating success.
   */
  public sendEmaiWithClassroomInfo(id: number): Observable<null> {
  return this.http.get<null>(this.url + 'getAllInfoOfCoursesByStudentClassroomCourseIdAndSendByEmail/' + id );
  }
  
}