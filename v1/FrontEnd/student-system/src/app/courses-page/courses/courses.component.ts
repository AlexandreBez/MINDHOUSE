import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { CourseApiService } from 'src/app/api/CoursesApi.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
})
export class CoursesComponent implements OnInit {

  courseData?: any = null;
  deleteBtn = 0;
  updateBtn = 0;

  _name: string;
  _field: string;
  _price: number;
  _modality: string;
  _creation_date: string;

  errorMessage: any = null;
  successMessage: any = null;

  isLoadingInformations = false;
  isLoading = false;

  searchType: string = "name";
  dataforSearch: string = null;

  idForDelete: any = null;

  constructor(
    private courseService: CourseApiService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAllCourses();
    this.courseService.idForUpdatedCourse = null;
  }

  getAllCourses() {
    this.isLoading = true;
    this.courseData = this.courseService
      .getAllCourses()
      .pipe(
        map((responseData) => {
          return responseData.map((responseData) => {
            return { ...responseData };
          });
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.courseData = responseData;
            this.isLoading = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          if (error.status === 404) {
            this.courseData = null;
            this.isLoading = false;
            console.log(error);
            return (this.errorMessage = null);
          } else {
            switch (error.error.status) {
              case 500:
                this.courseData = null;
                this.isLoading = false;
                console.log(error);
                return (this.errorMessage =
                  'Opss...Something is not working but we will fix soon. Please try again later');
              default:
                this.courseData = null;
                this.isLoading = false;
                console.log(error);
                return (this.errorMessage =
                  'Opss...Something is not working but we will fix soon. Please try again later');
            }
          }
        }
      );
  }

  getCourseById(id: number) {
    this.isLoadingInformations = true;

    this.courseService
      .getCourseById(id)
      .pipe(
        map((responseData) => {
          this._name = responseData.course_name;
          this._field = responseData.course_field;
          this._price = responseData.course_price;
          this._modality = responseData.course_modality;
          this._creation_date = responseData.course_creation_date;
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.isLoadingInformations = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          console.log(error);
          this.isLoadingInformations = false;
          this._name = "Error";
          this._field = "Error";
          this._price = 0;
          this._modality = "Error";
          this._creation_date = "Error";
        }
      );
  }

  searchCourse() {

    if (this.dataforSearch === null) {
      this.getAllCourses();
    }else if(this.searchType === "name") {
      this.searchCourseByName();
    }else{
      this.searchCourseByField();
    }

  }

  searchCourseByName() {
    this.isLoading = true;

    this.courseData = this.courseService
      .getCourseByName(this.dataforSearch.trim())
      .pipe(
        map((responseData) => {
          return responseData.map((responseData) => {
            return { ...responseData };
          });
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.courseData = responseData;
            this.isLoading = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          if (error.status === 404) {
            this.courseData = null;
            this.isLoading = false;
            this.errorMessage = 'Course not found....';
            setTimeout(() => {
              this.errorMessage = null;
            }, 2000);
            return console.log(error);
          } else {
            switch (error.error.status) {
              case 500:
                this.courseData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
              default:
                this.courseData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
            }
          }
        }
      );

    this.dataforSearch = null;
  }

  searchCourseByField() {
    this.isLoading = true;

    this.courseData = this.courseService
      .getCourseByField(this.dataforSearch.trim())
      .pipe(
        map((responseData) => {
          return responseData.map((responseData) => {
            return { ...responseData };
          });
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.courseData = responseData;
            this.isLoading = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          if (error.status === 404) {
            this.courseData = null;
            this.isLoading = false;
            this.errorMessage = 'Course not found....';
            setTimeout(() => {
              this.errorMessage = null;
            }, 2000);
            return console.log(error);
          } else {
            switch (error.error.status) {
              case 500:
                this.courseData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
              default:
                this.courseData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
            }
          }
        }
      );

    this.dataforSearch = null;
  }

  deleteCourse(id: number) {
    this.isLoading = true;
    this.courseData = this.courseService.deleteCourse(id).subscribe(
      (responseData) => {
        this.successMessage = 'Course deleted with success...';
        console.log(responseData);
        setTimeout(() => {
          this.successMessage = null;
          this.isLoading = false;
        }, 2000);
        this.getAllCourses();
      },
      (error) => {
        switch (error.error.status) {
          case 500:
            this.courseData = null;
            this.isLoading = false;
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          default:
            this.courseData = null;
            this.isLoading = false;
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
        }
      }
    );
    this.idForDelete = null;
  }

  updatedCourse(id: number) {
    this.courseService.idForUpdatedCourse = id;
    this.router.navigate(['/UpdateCourses']);
  }
}
