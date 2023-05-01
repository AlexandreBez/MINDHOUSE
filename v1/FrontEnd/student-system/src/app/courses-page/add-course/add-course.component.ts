import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/interface/Course';
import { CourseApiService } from 'src/app/service/CoursesApi.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent {

  isLoading = false;
  errorMessage = null;
  successMessage = null;

  @ViewChild('addCourseForm')
  addCourseForm?: NgForm;

  constructor(
    private router: Router,
    private courseService: CourseApiService
  ) {}

  ngOnInit() {
  }

  trimLine(val: string) {
    return val.trim();
  }

  onAddCourse() {
    let courseName: string = this.trimLine(this.addCourseForm.value.courseName);
    let courseField: string = this.trimLine(this.addCourseForm.value.courseField);
    let coursePrice: number = this.addCourseForm.value.coursePrice;
    let courseModality: string = this.trimLine(this.addCourseForm.value.courseModality);
    let registrationDate: string = this.trimLine(this.addCourseForm.value.registrationDate);

    let new_course: Course = {
      course_name: courseName,
      course_field: courseField,
      course_price: coursePrice,
      course_modality: courseModality,
      course_creation_date: registrationDate
    };

    this.courseService
      .addCourse(new_course)
      .subscribe(
        (data) => {
          this.isLoading = true;
          setTimeout(() => {
            this.successMessage = 'Course created with success...';
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