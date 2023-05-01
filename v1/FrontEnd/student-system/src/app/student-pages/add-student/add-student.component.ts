import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Student } from 'src/app/interface/Student';
import { StudentApiService } from 'src/app/service/StudentApi.service';

/**
 * Component responsible for adding new students.
 */
@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent {
  /** Indicates whether a request is being made to the API. */
  isLoading = false;

  /** An error message to display in case of API failure. */
  errorMessage = null;

  /** A success message to display in case of API success. */
  successMessage = null;

  /** The form containing the student data. */
  @ViewChild('addForm')
  addStudentForm?: NgForm;

  /**
   * Creates an instance of AddStudentComponent.
   * @param router The Angular router service.
   * @param studentApiService The service responsible for making requests to the API.
   */
  constructor(
    private router: Router,
    private studentApiService: StudentApiService
  ) {}

  /**
   * Submits the form to create a new student.
   */
  onAddStudent() {
    // Reset the error and success messages
    this.errorMessage = null;
    this.successMessage = null;

    // Set the loading state
    this.isLoading = true;

    // Get the form data
    const formData = this.addStudentForm.value;

    // Create the new student object
    const new_student: Student = {
      student_name: formData.name.trim(),
      student_document: formData.document.trim(),
      student_age: formData.age,
      student_birthdate: formData.birthdate,
      student_country: formData.country.trim(),
      student_state: formData.state.trim(),
      student_city: formData.city.trim(),
      student_adress: formData.adress.trim(),
      student_zipcode: formData.zipcode.trim(),
      student_email: formData.email.trim(),
      student_phone: formData.phone.trim(),
      student_phone_2: formData.phone_2 ? formData.phone_2.trim() : '',
      student_contract_term: formData.contract_term,
      student_creation_date: formData.registrationDate
    };

    // Make the API request
    this.studentApiService.addStudent(new_student).subscribe(
      (data) => {
        // Set the success message
        this.successMessage = 'Student created with success...';
        console.log(data);

        // Navigate to the student list page after 2 seconds
        setTimeout(() => {
          this.router.navigate(['Students']);
        }, 2000);
      },
      (error) => {
        // Set the error message
        console.log(error);
        this.errorMessage = 'Opss...Something is not working but we will fix soon. Please try again later';
      }
    ).add(() => {
      // Set the loading state to false after the request completes
      this.isLoading = false;
    });
  }

  /**
   * Navigates to the student list page.
   */
  onCancel() {
    this.router.navigate(['/Students']);
  }
}