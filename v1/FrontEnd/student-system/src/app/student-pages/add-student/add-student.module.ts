import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddStudentComponent } from './add-student.component';
import { SpinnerModule } from 'src/app/shared/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { StudentApiService } from 'src/app/api/StudentApi.service';
import { HttpClientModule } from '@angular/common/http';

/**
 * @description Angular module for adding a new student to the database
 */
@NgModule({
  declarations: [AddStudentComponent],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'AddStudent', component: AddStudentComponent },
    ]),
    SpinnerModule,
    FormsModule,
  ],
  providers: [StudentApiService],
})
export class AddStudentModule {}
