import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { StudentApiService } from 'src/app/api/StudentApi.service';
import { StudentClassroomCourseApiService } from 'src/app/api/StudentClassroomCourseApi.service';
import * as moment from 'moment';

/**
 * Component to display and manage courses for a student.
 * @component
 * @selector app-student-course
 */
@Component({
  selector: 'app-student-course',
  templateUrl: './student-course.component.html',
  styleUrls: ['./student-course.component.css'],
})
export class StudentCourseComponent implements OnInit {
  /**
   * Flag to indicate if course information is being loaded.
   * @type {boolean}
   */
  isLoadingInformations = false;

  /**
   * Flag to indicate if course information modal is being loaded.
   * @type {boolean}
   */
  isloadingInfoModal = false;

  /**
   * Array of courses.
   * @type {any}
   */
  courses: any = null;

  /**
   * Error message.
   * @type {any}
   */
  errorMessage: any = null;

  /**
   * Success message.
   * @type {any}
   */
  successMessage: any = null;

  /**
   * Error message for loading course information.
   * @type {any}
   */
  loaderrorMessage: any = null;

  /**
   * Success message for loading course information.
   * @type {any}
   */
  loadSuccessMessage: any = null;

  /**
   * Description of the course.
   * @type {any}
   */
  course_description: any = null;

  /**
   * Name of the course.
   * @type {any}
   */
  course_name: any = null;

  /**
   * Data for searching a course.
   * @type {string}
   */
  dataForSearch: string = null;

  /**
   * ID of the student classroom course.
   * @type {number}
   */
  StudentClassroomCourseId: number = null;

  /**
   * Name of the course to be deleted.
   * @type {string}
   */
  courseNameForDelete: string = null;

  /**
   * Name of the course.
   * @private
   * @type {string}
   */
  _course_name: string;

  /**
   * Field of the course.
   * @private
   * @type {string}
   */
  _course_field: string;

  /**
   * Price of the course.
   * @private
   * @type {number}
   */
  _course_price: number;

  /**
   * Modality of the course.
   * @private
   * @type {string}
   */
  _course_modality: string;

  /**
   * Period of the classroom.
   * @private
   * @type {string}
   */
  _classroom_period: string;

  /**
   * Start time of the classroom.
   * @private
   * @type {string}
   */
  _classroom_start_time: string;

  /**
   * End time of the classroom.
   * @private
   * @type {string}
   */
  _classroom_end_time: string;

  /**
   * Start date of the classroom.
   * @private
   * @type {string}
   */
  _classroom_start_date: string;

  /**
   * End date of the classroom.
   * @private
   * @type {string}
   */
  _classroom_end_date: string;

  /**
   * Name of the teacher.
   * @private
   * @type {string}
   */
  _teacher_name: string;

  /**
   * ID for searching a student information.
   * @type {number}
   */
  idForSearch = null;

  /**
   * @param {StudentClassroomCourseApiService} studentClassroomCourseService - Service used to retrieve student classroom course information
   * @param {StudentApiService} studentService - Service used to retrieve student information
   */
  constructor(
    private studentClassroomCourseService: StudentClassroomCourseApiService,
    private studentService: StudentApiService
  ) {}

  /**
   * Initializes the component with the student's ID for searching and retrieves the student's courses.
   *
   * @memberof ComponentClassName
   * @function ngOnInit
   * @return {void}
   */
  ngOnInit(): void {
    this.idForSearch = this.studentService.idForGetStudentInfo;
    this.getStudentCoursesByStudentId();
  }

  /**
   * Checks if a course can be canceled.
   *
   * @param {string} startDate - The start date of the course.
   * @returns {boolean} True if the current date is before the start date of the course, false otherwise.
   */
  checkIfCourseCanBeCanceled(startDate: string): boolean {
    const currentDateMoment = moment();
    const startDateMoment = moment(startDate);

    if (currentDateMoment.isAfter(startDateMoment) || currentDateMoment.isSame(startDateMoment)) {
      return true;
    }else{
      return false;
    }

  }

  /**
   * Retrieves the courses of a student with the given ID.
   *
   * @function
   * @memberof StudentCourseComponent
   * @returns {void}
   */
  getStudentCoursesByStudentId() {
    // Sets a loading flag to true
    this.isLoadingInformations = true;

    // Calls the getStudentCoursesByStudentId() method of the StudentClassroomCourseApiService,
    // passing in the ID of the student to retrieve the courses for
    this.studentClassroomCourseService
      .getStudentCoursesByStudentId(this.idForSearch)
      .subscribe(
        // Success callback function
        (responseData) => {
          // Clears any error message
          this.errorMessage = null;
          // Sets a timeout to simulate a delay in the response, and then sets the loading flag to false,
          // assigns the response data to the courses property, and logs the response data to the console
          setTimeout(() => {
            this.isLoadingInformations = false;
            this.courses = responseData;
            console.log(responseData);
          }, 1500);
        },
        // Error callback function
        (error) => {
          // Logs the error to the console
          console.log(error);

          // Handles the error based on its status code
          if (error.status === 404) {
            // If the error is a 404 Not Found error, sets the courses property to null,
            // sets the loading flag to false, logs the error to the console, and clears any error message
            this.courses = null;
            this.isLoadingInformations = false;
            console.log(error);
            return (this.errorMessage = null);
          } else {
            // Otherwise, sets the courses property and course_description property to null,
            // sets the loading flag to false, logs the error to the console, and sets the error message
            // to a default message
            this.courses = null;
            this.isLoadingInformations = false;
            this.course_description = null;
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }

  /**
   * Retrieves the student's courses by course name.
   * If dataForSearch is null, empty, or undefined, the function calls getStudentCoursesByStudentId().
   * Otherwise, it retrieves the courses by the specified course name.
   */
  getStudentCoursesByCourseName() {
    if (!this.dataForSearch) {
      // Call getStudentCoursesByStudentId() if dataForSearch is null, empty, or undefined
      this.getStudentCoursesByStudentId();
    } else {
      // Otherwise, retrieve the courses by the specified course name
      this.isLoadingInformations = true;
      this.studentClassroomCourseService
        .getStudentCoursesByCourseName(this.idForSearch, this.dataForSearch)
        .subscribe(
          (responseData) => {
            // Handle successful response
            this.errorMessage = null;
            setTimeout(() => {
              this.isLoadingInformations = false;
              this.courses = responseData;
              console.log(responseData);
            }, 1500);
          },
          (error) => {
            // Handle error response
            console.log(error);
            if (error.status === 404) {
              this.courses = null;
              this.isLoadingInformations = false;
              console.log(error);
              return (this.errorMessage = null);
            } else {
              this.courses = null;
              this.isLoadingInformations = false;
              this.course_description = null;
              console.log(error);
              return (this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later');
            }
          }
        );
    }
  }

  /**
   * Deletes a student course by ID.
   */
  deleteStudentCourseById() {
    /**
     * Indicates whether the component is currently loading information.
     * @type {boolean}
     */
    this.isLoadingInformations = true;

    /**
     * Error message displayed to the user.
     * @type {string}
     */
    this.errorMessage = null;

    /**
     * Success message displayed to the user.
     * @type {string}
     */
    this.successMessage = null;

    this.studentClassroomCourseService
      .deleteStudentCourse(this.StudentClassroomCourseId, 1, 1)
      .subscribe(
        /**
         * Success callback for the HTTP request.
         * @param {any} responseData - The response data returned by the HTTP request.
         */
        (responseData) => {
          this.successMessage = 'Course canceled with success...';
          console.log(responseData);
          setTimeout(() => {
            this.successMessage = null;
            this.isLoadingInformations = false;
          }, 2000);
          this.getStudentCoursesByStudentId();
        },
        /**
         * Error callback for the HTTP request.
         * @param {HttpErrorResponse} error - The error response returned by the HTTP request.
         */
        (error) => {
          switch (error.status) {
            case 400:
              this.isLoadingInformations = false;
              console.log(error);
              this.errorMessage = error.error.message;
              break;
            case 500:
              this.isLoadingInformations = false;
              console.log(error);
              this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later';
              break;
            default:
              this.isLoadingInformations = false;
              console.log(error);
              this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later';
              break;
          }
        }
      );
    this.StudentClassroomCourseId = null;
  }

  /**
   * Retrieves the full information about a classroom course by its ID and assigns the values to corresponding variables.
   *
   * @param {number} id - The ID of the classroom course to retrieve information about.
   */
  getFullStudentClassroomCourseInfo(id) {

    this.isloadingInfoModal = true;
    this.loaderrorMessage = null;

    this.studentClassroomCourseService
      .getClassroomFullInfo(id)
      .pipe(
        map((responseData) => {

          console.log(responseData);

          return responseData.map((responseData) => {
            this._teacher_name = responseData.teacher_name;
            this._course_name = responseData.course_name;
            this._course_field = responseData.course_field;
            this._course_price = responseData.course_price;
            this._course_modality = responseData.course_modality;
            this._classroom_period = responseData.classroom_period;
            this._classroom_start_time =
              responseData.classroom_start_time;
            this._classroom_end_time =
              responseData.classroom_end_time;
            this._classroom_start_date =
              responseData.classroom_start_date;
            this._classroom_end_date =
              responseData.classroom_end_date;

          });
          
        })
      )
      .subscribe(
        () => {
          this.loaderrorMessage = null;

          setTimeout(() => {
            this.isloadingInfoModal = false;
          }, 1500);
        },
        /**
         * Handles the error returned from the classroom course service call.
         * If the status is 404, sets all variables to "Not Found".
         * Otherwise, sets all variables to "Not Found" and displays an error message.
         *
         * @param {any} error - The error object returned from the classroom course service call.
         * @returns {string|undefined} - If there is an error, returns an error message; otherwise, returns undefined.
         */
        (error) => {
          console.log(error);

          if (error.status === 404) {
            this.isloadingInfoModal = false;

            this._teacher_name = 'Not Found';
            this._course_name = 'Not Found';
            this._course_field = 'Not Found';
            this._course_price = 0;
            this._course_modality = 'Not Found';
            this._classroom_period = 'Not Found';
            this._classroom_start_time = 'Not Found';
            this._classroom_end_time = 'Not Found';
            this._classroom_start_date = 'Not Found';
            this._classroom_end_date = 'Not Found';

            console.log(error);

            return (this.loaderrorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          } else {
            this.isloadingInfoModal = false;

            this._teacher_name = 'ERROR';
            this._course_name = 'ERROR';
            this._course_field = 'ERROR';
            this._course_price = 0;
            this._course_modality = 'ERROR';
            this._classroom_period = 'ERROR';
            this._classroom_start_time = 'ERROR';
            this._classroom_end_time = 'ERROR';
            this._classroom_start_date = 'ERROR';
            this._classroom_end_date = 'ERROR';

            console.log(error);

            return (this.loaderrorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }

  /**
   * Sends an email with the student classroom course information.
   * Sets loading states and success/error messages accordingly.
   *
   * @return {void}
   */
  sendEmailWithClassroomInfo() {
    // Set loading states to true and clear any previous messages
    this.isloadingInfoModal = true;
    this.loadSuccessMessage = null;
    this.loaderrorMessage = null;

    // Call the service to send the email
    this.studentClassroomCourseService
      .sendEmaiWithClassroomInfo(this.StudentClassroomCourseId)
      .subscribe(
        () => {
          // On success, set loading state to false and display success message
          this.isloadingInfoModal = false;
          setTimeout(() => {
            this.loadSuccessMessage = 'Email sent with success...';
            setTimeout(() => {
              this.loadSuccessMessage = null;
            }, 1500);
          }, 2000);
        },
        (error) => {
          // On error, handle different types of errors
          if (error.status === 400) {
            // If it's a bad request error, set loading state to false and display error message
            this.isloadingInfoModal = false;
            console.log(error);
            this.loadSuccessMessage = null;
            return (this.loaderrorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          } else {
            // If it's any other type of error, set loading state to false and display error message
            switch (error.error.status) {
              case 500:
                console.log(error);
                this.loadSuccessMessage = null;
                break;
              default:
                console.log(error);
                this.loadSuccessMessage = null;
                break;
            }
            this.isloadingInfoModal = false;
            return (this.loaderrorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }
  
}
