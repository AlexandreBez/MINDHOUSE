import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

/**
 * The routing configuration for the application.
 */
const appRoutes: Routes = [
  { 
    /**
     * The default route that redirects to the Students page.
     */
    path: '', 
    redirectTo: '/Students', 
    pathMatch: 'full' 
  },
  {
    /**
     * The lazy-loaded module for the Students page.
     */
    path: '',
    loadChildren: () => import('../student-pages/students/students.module').then((m) => m.StudentsModule),
  },
  {
    /**
     * The lazy-loaded module for the Add Student page.
     */
    path: '',
    loadChildren: () => import('../student-pages/add-student/add-student.module').then((m) => m.AddStudentModule),
  },
  {
    /**
     * The lazy-loaded module for the Courses page.
     */
    path: '',
    loadChildren: () => import('../courses-page/courses/courses.module').then((m) => m.CoursesModule),
  },
  {
    /**
     * The lazy-loaded module for the Add Course page.
     */
    path: '',
    loadChildren: () => import('../courses-page/add-course/add-course.module').then((m) => m.AddCourseModule),
  },
  {
    /**
     * The lazy-loaded module for the Not Found page.
     */
    path: '',
    loadChildren: () => import('../shared/not-found/not-found.module').then((m) => m.NotFoundModule),
  },
  { 
    /**
     * The fallback route that redirects to the Not Found page.
     */
    path: '**', 
    redirectTo: '/NotFound' 
  },
];

/**
 * The module that handles routing for the application.
 */
@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { useHash: true })],
  exports: [RouterModule],
})
export class RoutesModule {}