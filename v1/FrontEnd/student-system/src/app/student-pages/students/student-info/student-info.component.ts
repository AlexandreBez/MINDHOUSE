import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentApiService } from 'src/app/service/StudentApi.service';

/**
 * Component that displays student information.
 */
@Component({
  selector: 'app-student-info',
  templateUrl: './student-info.component.html',
  styleUrls: ['./student-info.component.css']
})
export class StudentInfoComponent implements OnInit {
  
  /** Indicates whether student information is being loaded. */
  isLoadingInformation = false;

  /** The name of the student being displayed. */
  studentName: any = null;

  /** Any error message that occurred while loading student information. */
  errorMessage: any = null;

  /**
   * Creates an instance of StudentInfoComponent.
   *
   * @param {StudentApiService} studentService The student API service.
   * @param {Router} router The router service.
   */
  constructor(
    private studentService: StudentApiService,
    private router: Router
  ) {}

  /** Initializes the component. */
  ngOnInit() {
    /**
     * If no student ID was provided, navigate back to the students page;
     */
    if (!this.studentService.idForGetStudentInfo) {
      this.router.navigate(['/Students']);
    }

    this.studentName = this.studentService.studentName
  }

  /** Navigates back to the students page. */
  goToStudentPage() {
    this.router.navigate(['Students']);
  }
}
