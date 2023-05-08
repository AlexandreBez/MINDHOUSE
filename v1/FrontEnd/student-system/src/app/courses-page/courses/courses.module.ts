import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoursesComponent } from './courses.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { SpinnerModule } from 'src/app/shared/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { CourseApiService } from 'src/app/api/CoursesApi.service';

@NgModule({
  declarations: [
    CoursesComponent,
    UpdateCourseComponent,
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'Courses', component: CoursesComponent },
      { path: 'UpdateCourses', component: UpdateCourseComponent },
    ]),
    SpinnerModule,
    FormsModule
  ],
  exports: [
    CoursesComponent,
    UpdateCourseComponent
  ],
  providers: [CourseApiService]
})
export class CoursesModule { }
