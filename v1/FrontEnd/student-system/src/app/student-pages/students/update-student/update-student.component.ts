import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { Student } from 'src/app/api/interface/Student';
import { StudentApiService } from 'src/app/api/StudentApi.service';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css'],
})
export class UpdateStudentComponent {

  isLoading = false;
  errorMessage = null;
  successMessage = null;

  loadedStudent: any;
  _name: string;
  _document: string;
  _age: string;
  _birthdate: string;
  _country: string;
  _state: string;
  _city: string;
  _adress: string;
  _zipcode: string;
  _email: string;
  _phone: string;
  _phone_2: string;
  _registrationDate: string

  @ViewChild('updateForm')
  updateStudentForm?: NgForm;

  constructor(
    private router: Router,
    private studentApiService: StudentApiService
  ) {}

  ngOnInit() {
    if (
      this.studentApiService.idForUpdatedStudent === null ||
      this.studentApiService.idForUpdatedStudent === undefined
    ) {
      this.router.navigate(['Students']);
    } else {
      this.getStudentById();
    }
  }

  getStudentById() {
    this.loadedStudent = this.studentApiService
      .getStudentById(this.studentApiService.idForUpdatedStudent)
      .pipe(
        map((responseData) => {

          this._name = responseData.student_name;
          this._document = responseData.student_document;
          this._age = responseData.student_age;
          this._birthdate = responseData.student_birthdate;
          this._country = responseData.student_country;
          this._state = responseData.student_state;
          this._city = responseData.student_city;
          this._adress = responseData.student_adress;
          this._email = responseData.student_email;
          this._zipcode = responseData.student_zipcode;
          this._phone = responseData.student_phone;
          this._phone_2 = responseData.student_phone_2;
          this._registrationDate = responseData.student_creation_date;

          return responseData;
        })
      )
      .subscribe(
        (data) => {
          this.isLoading = true;
          setTimeout(() => {
            this.isLoading = false;
            console.log(data);
          }, 1500);
        },
        (error) => {
          this.router.navigate(['Students']);
          console.log(error);
          return (this.loadedStudent = null);
        }
      );
  }

  onUpdatedStudent() {

    // Get the form data
    const formData = this.updateStudentForm.value;

    let updated_student: Student = {
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
    };

    this.studentApiService
      .updateStudent(
        this.studentApiService.idForUpdatedStudent,
        updated_student
      )
      .subscribe(
        (data) => {
          this.isLoading = true;
          this.errorMessage = null;
          setTimeout(() => {
            this.successMessage = 'Student updated with success...';
            this.isLoading = false;
            console.log(data);
          }, 2000);
          setTimeout(() => {
            this.successMessage = null;
            this.router.navigate(['Students']);
          }, 4000);
        },
        (error) => {
          switch (error.error.status) {
            case 500:
              console.log(error);
              this.isLoading = false;
              return (this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later');
            default:
              console.log(error);
              this.isLoading = false;
              return (this.errorMessage =
                'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }

  onCancel() {
    this.router.navigate(['/Students']);
  }
}
