import { Component, OnInit } from '@angular/core';
import { GradeApiService } from 'src/app/service/GradeApi.service';
import { StudentApiService } from 'src/app/service/StudentApi.service';

/**
 * Component to display and manage grades for a student.
 * @component
 * @selector app-student-grades
 */
@Component({
  selector: 'app-student-grades',
  templateUrl: './student-grades.component.html',
  styleUrls: ['./student-grades.component.css'],
})
export class StudentGradesComponent implements OnInit {
  
  isLoadingInformations: boolean = false;
  errorMessage: any = null;
  successMessage: any = null;

  dataForSearch: string = null;

  idForSearch = this.studentService.idForGetStudentInfo;
  gradeId: any = null;

  grades: any = null;

  constructor(
    private gradeService: GradeApiService,
    private studentService: StudentApiService
  ) {}

  ngOnInit() {
    this.getStudentGradesByStudentId();
  }

  getStudentGradesByStudentId() {
    this.isLoadingInformations = true;
    this.gradeService.getStudentGradesByStudentId(this.idForSearch).subscribe(
      (responseData) => {
        this.errorMessage = null;
        setTimeout(() => {
          this.isLoadingInformations = false;
          this.grades = responseData;
          console.log(responseData);
        }, 1500);
      },
      (error) => {
        console.log(error);

        if (error.status === 404) {
          this.grades = null;
          this.isLoadingInformations = false;
          console.log(error);
          return (this.errorMessage = null);
        } else {
          this.grades = null;
          this.isLoadingInformations = false;
          console.log(error);
          return (this.errorMessage =
            'Opss...Something is not working but we will fix soon. Please try again later');
        }
      }
    );
  }

  getStudentCoursesByCourseName() {
    if (
      this.dataForSearch === null ||
      this.dataForSearch === '' ||
      this.dataForSearch === undefined
    ) {
      this.getStudentGradesByStudentId();
    } else {
      this.isLoadingInformations = true;
      this.gradeService
        .getStudentGradesByStudentIdAndCourseName(
          this.idForSearch,
          this.dataForSearch
        )
        .subscribe(
          (responseData) => {
            this.errorMessage = null;
            setTimeout(() => {
              this.isLoadingInformations = false;
              this.grades = responseData;
              console.log(responseData);
            }, 1500);
          },
          (error) => {
            console.log(error);

            if (error.status === 404) {
              this.grades = null;
              this.isLoadingInformations = false;
              console.log(error);
              return (this.errorMessage = null);
            } else {
              this.grades = null;
              this.isLoadingInformations = false;
              console.log(error);
              return (this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later');
            }
          }
        );
    }
  }

  getStudentGradesByGradeIdAndSendEmailWithInfo(id: any) {
    this.errorMessage = null;
    this.successMessage = null;

    this.gradeService
      .getStudentGradesByGradeIdAndSendEmailWithInfo(id)
      .subscribe(
        (responseData) => {
          this.successMessage = 'Email sent with success...';
          setTimeout(() => {
            console.log(responseData);
            this.successMessage = null;
          }, 1500);
        },
        (error) => {
          console.log(error);

          if (error.status === 404) {
            console.log(error);
            return (this.errorMessage = null);
          } else {
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }
}
