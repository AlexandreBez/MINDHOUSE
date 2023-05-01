var ROUTES_INDEX = {"name":"<root>","kind":"module","className":"AppModule","children":[{"name":"appRoutes","filename":"src/app/routes/routing.modules.ts","module":"RoutesModule","children":[{"path":"","redirectTo":"/Students","pathMatch":"full"},{"path":"","loadChildren":"../student-pages/students/students.module#StudentsModule","children":[{"kind":"module","children":[],"module":"StudentsModule"}]},{"path":"","loadChildren":"../student-pages/add-student/add-student.module#AddStudentModule","children":[{"kind":"module","children":[],"module":"AddStudentModule"}]},{"path":"","loadChildren":"../courses-page/courses/courses.module#CoursesModule","children":[{"kind":"module","children":[],"module":"CoursesModule"}]},{"path":"","loadChildren":"../courses-page/add-course/add-course.module#AddCourseModule","children":[{"kind":"module","children":[],"module":"AddCourseModule"}]},{"path":"","loadChildren":"../shared/not-found/not-found.module#NotFoundModule","children":[{"kind":"module","children":[],"module":"NotFoundModule"}]},{"path":"**","redirectTo":"/NotFound"}],"kind":"module"}]}