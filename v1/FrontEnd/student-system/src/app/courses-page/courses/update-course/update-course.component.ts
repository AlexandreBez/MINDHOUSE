import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { Course } from 'src/app/interface/Course';
import { CourseApiService } from 'src/app/service/CoursesApi.service';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css'],
})
export class UpdateCourseComponent {

  isLoading = false;
  errorMessage = null;
  successMessage = null;

  loadedCourse: any;
  _courseName: string;
  _courseField: string;
  _coursePrice: number;
  _courseModality: string;

  @ViewChild('updatedform')
  updatedForm?: NgForm;

  constructor(
    private router: Router,
    private courseService: CourseApiService
  ) {}

  ngOnInit() {
    if (
      this.courseService.idForUpdatedCourse === null ||
      this.courseService.idForUpdatedCourse === undefined
    ) {
      this.router.navigate(['Courses']);
    } else {
      this.getStudentById();
    }
  }

  getStudentById() {
    this.loadedCourse = this.courseService
      .getCourseById(this.courseService.idForUpdatedCourse)
      .pipe(
        map((responseData) => {

          this._courseName = responseData.course_name;
          this._courseField = responseData.course_field;
          this._coursePrice = responseData.course_price;
          this._courseModality = responseData.course_modality;

          return responseData;
        })
      )
      .subscribe(
        (data) => {
          this.isLoading = true;
          setTimeout(() => {
            this.isLoading = false;
            console.log(data);
          }, 1500);
        },
        (error) => {
          this.router.navigate(['Courses']);
          console.log(error);
          return (this.loadedCourse = null);
        }
      );
  }

  trimLine(val: string) {
    return val.trim();
  }

  onUpdatedCourse() {
    let courseName: string = this.trimLine(this.updatedForm.value.courseName);
    let courseField: string = this.trimLine(this.updatedForm.value.courseField);
    let coursePrice: number = this.updatedForm.value.coursePrice;
    let courseModality: string = this.trimLine(this.updatedForm.value.courseModality);

    let updated_course: Course = {
      course_name: courseName,
      course_field: courseField,
      course_price: coursePrice,
      course_modality: courseModality
    };

    this.courseService
      .updateCourse(
        this.courseService.idForUpdatedCourse,
        updated_course
      )
      .subscribe(
        (data) => {
          this.isLoading = true;
          setTimeout(() => {
            this.successMessage = 'Course updated with success...';
            this.isLoading = false;
            console.log(data);
          }, 2000);
          setTimeout(() => {
            this.successMessage = null;
            this.router.navigate(['Courses']);
          }, 4000);
        },
        (error) => {
          switch (error.error.status) {
            case 500:
              console.log(error);
              this.isLoading = false;
              return (this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later');
            default:
              console.log(error);
              this.isLoading = false;
              return (this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }

  onCancel() {
    this.router.navigate(['/Courses']);
  }
}
