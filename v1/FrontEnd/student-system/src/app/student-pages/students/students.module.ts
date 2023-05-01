import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentsComponent } from './students.component';
import { RouterModule } from '@angular/router';
import { StudentApiService } from 'src/app/service/StudentApi.service';
import { HttpClientModule } from '@angular/common/http';
import { SpinnerModule } from 'src/app/shared/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { StudentInfoComponent } from './student-info/student-info.component';
import { StudentCourseComponent } from './student-info/student-courses/student-course.component';
import { StudentPaymentsComponent } from './student-info/student-payments/student-payments.component';
import { StudentGradesComponent } from './student-info/student-grades/student-grades.component';
import { StudentClassroomCourseApiService } from 'src/app/service/StudentClassroomCourseApi.service';
import { GradeApiService } from 'src/app/service/GradeApi.service';
import { PaymentApiService } from 'src/app/service/PaymentApi.service';

@NgModule({
  declarations: [
    StudentsComponent,
    UpdateStudentComponent,
    StudentInfoComponent,
    StudentCourseComponent,
    StudentPaymentsComponent,
    StudentGradesComponent,
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'Students', component: StudentsComponent },
      { path: 'UpdateStudent', component: UpdateStudentComponent },
      {
        path: 'StudentManagement',
        component: StudentInfoComponent,
        children: [
          { path: 'StudentCourse', component: StudentCourseComponent },
          { path: 'StudentGrades', component: StudentGradesComponent },
          { path: 'StudentPayments', component: StudentPaymentsComponent },
        ],
      },
    ]),
    SpinnerModule,
    FormsModule,
  ],
  exports: [
    StudentsComponent,
    UpdateStudentComponent,
    StudentInfoComponent,
    StudentGradesComponent,
    StudentCourseComponent,
    StudentPaymentsComponent,
  ],
  providers: [
    StudentApiService,
    StudentClassroomCourseApiService,
    GradeApiService,
    PaymentApiService
  ],
})
export class StudentsModule {}
