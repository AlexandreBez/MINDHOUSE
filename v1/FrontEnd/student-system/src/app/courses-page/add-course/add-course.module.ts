import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCourseComponent } from './add-course.component';
import { SpinnerModule } from 'src/app/shared/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CourseApiService } from 'src/app/service/CoursesApi.service';

@NgModule({
  declarations: [
    AddCourseComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'AddCourse', component: AddCourseComponent }
    ]),
    SpinnerModule,
    FormsModule
  ],
  providers: [
    CourseApiService
  ]
})
export class AddCourseModule { }
