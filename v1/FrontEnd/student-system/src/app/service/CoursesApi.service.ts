import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../interface/Course';

@Injectable()
export class CourseApiService {

  url = "http://localhost:8084/student-management-api/v1/";

  idForUpdatedCourse: number;

  constructor(
    private http: HttpClient
  ) { }

  public getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.url + 'getAllCourses');
  }

  public getCourseById(id:number): Observable<Course> {
    return this.http.get<Course>(this.url + 'getCourseById/'+id);
  }

  public getCourseByName(name: string): Observable<Course[]> {
    return this.http.get<Course[]>(this.url + 'getCourseByName/'+name);
  }

  public getCourseByField(field: string): Observable<Course[]> {
    return this.http.get<Course[]>(this.url + 'getCourseByField/'+field);
  }

  public deleteCourse(id: number): Observable<Course> {
    return this.http.delete<Course>(this.url + 'deleteCourse/'+id);
  }

  public updateCourse(id: number, course: Course): Observable<Course> {
    return this.http.put<Course>(this.url + '/updatedCourse/'+id, course);
  }

  public addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(this.url + '/addNewCourse', course);
  }
}