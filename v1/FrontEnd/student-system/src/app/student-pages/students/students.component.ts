import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { StudentApiService } from 'src/app/service/StudentApi.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css'],
})
export class StudentsComponent implements OnInit {

  studentData: any = null;
  deleteBtn = 0;
  updateBtn = 0;

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
  _creation_date: string;

  errorMessage: any = null;
  successMessage: any = null;

  isLoadingInformations = false;
  isLoading = false;

  searchType: string = "name";
  dataforSearch: string = null;

  idForDelete: any = null;

  constructor(
    private studentService: StudentApiService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAllStudents();
    this.studentService.idForUpdatedStudent = null;
  }

  getAllStudents() {
    this.isLoading = true;
    this.studentData = this.studentService
      .getAllStudents()
      .pipe(
        map((responseData) => {
          return responseData.map((responseData) => {
            return { ...responseData };
          });
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.studentData = responseData;
            this.isLoading = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          if (error.status === 404) {
            this.studentData = null;
            this.isLoading = false;
            console.log(error);
            this.errorMessage = null;
          } else {
            switch (error.error.status) {
              case 500:
                this.studentData = null;
                this.isLoading = false;
                console.log(error);
                this.errorMessage = 'Opss...Something is not working but we will fix soon. Please try again later';
                break;
              default:
                this.studentData = null;
                this.isLoading = false;
                console.log(error);
                this.errorMessage ='Opss...Something is not working but we will fix soon. Please try again later';
                break;
            }
          }
        }
      );
  }
  
  // -------------------------------------------------------------------------------

  getStudentById(id: number) {
    this.isLoadingInformations = true;

    this.studentService
      .getStudentById(id)
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
          this._zipcode = responseData.student_zipcode;
          this._email = responseData.student_email;
          this._phone = responseData.student_phone;
          this._phone_2 = responseData.student_phone_2;
          this._creation_date = responseData.student_creation_date;
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.isLoadingInformations = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          console.log(error);
          this.isLoadingInformations = false;
          this._name = "Error";
          this._birthdate = "Error";
          this._document = "Error";
          this._state = "Error";
          this._city = "Error";
          this._adress = "Error";
          this._email = "Error";
          this._phone = "Error";
          this._creation_date = "Error";
        }
      );
  }

  searchStudent() {
    this.errorMessage = null;
    this.successMessage = null;
    if (this.dataforSearch === null) {
      this.getAllStudents();
    }else if(this.searchType === "name") {
      this.searchStudentByName();
    }else{
      this.searchStudentByDocument();
    }

  }

  searchStudentByName() {
    this.isLoading = true;

    this.studentData = this.studentService
      .getStudentByName(this.dataforSearch.trim())
      .pipe(
        map((responseData) => {
          return responseData.map((responseData) => {
            return { ...responseData };
          });
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.studentData = responseData;
            this.isLoading = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          if (error.status === 404) {
            this.studentData = null;
            this.isLoading = false;
            this.errorMessage = 'Student not found....';
            setTimeout(() => {
              this.errorMessage = null;
            }, 2000);
            return console.log(error);
          } else {
            switch (error.error.status) {
              case 500:
                this.studentData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
              default:
                this.studentData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
            }
          }
        }
      );

    this.dataforSearch = null;
  }

  searchStudentByDocument() {
    this.isLoading = true;

    this.studentData = this.studentService
      .getStudentByDocument(this.dataforSearch.trim())
      .pipe(
        map((responseData) => {
          return responseData.map((responseData) => {
            return { ...responseData };
          });
        })
      )
      .subscribe(
        (responseData) => {
          setTimeout(() => {
            this.studentData = responseData;
            this.isLoading = false;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          if (error.status === 404) {
            this.studentData = null;
            this.isLoading = false;
            this.errorMessage = 'Student not found....';
            setTimeout(() => {
              this.errorMessage = null;
            }, 2000);
            return console.log(error);
          } else {
            switch (error.error.status) {
              case 500:
                this.studentData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
              default:
                this.studentData = null;
                this.isLoading = false;
                console.log(error);
                setTimeout(() => {
                  this.errorMessage = null;
                }, 2000);
                return console.log(error);
            }
          }
        }
      );

    this.dataforSearch = null;
  }

  deleteStudent(id: number) {
    this.isLoading = true;
    this.errorMessage = null;
    this.successMessage = null;
    
    this.studentData = this.studentService.deleteStudent(id).subscribe(
      (responseData) => {
        this.successMessage = 'Student deleted with success...';
        console.log(responseData);
        setTimeout(() => {
          this.successMessage = null;
          this.isLoading = false;
        }, 2000);
        this.getAllStudents();
      },
      (error) => {
        switch (error.error.status) {
          case 500:
            this.studentData = null;
            this.isLoading = false;
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          default:
            this.studentData = null;
            this.isLoading = false;
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
        }
      }
    );
    this.idForDelete = null;
  }

  updatedStudent(id: number) {
    this.studentService.idForUpdatedStudent = id;
    this.router.navigate(['UpdateStudent']);
  }

  goToStudentManagement(id: number, student_name: string) {
    this.studentService.idForGetStudentInfo = id;
    this.studentService.studentName = student_name;
    this.router.navigate(['StudentManagement']);
  }
  
}
