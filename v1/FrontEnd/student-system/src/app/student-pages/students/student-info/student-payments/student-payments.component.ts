import { Component, OnInit } from '@angular/core';
import { PaymentApiService } from 'src/app/api/PaymentApi.service';
import { StudentApiService } from 'src/app/api/StudentApi.service';

@Component({
  selector: 'app-student-payments',
  templateUrl: './student-payments.component.html',
  styleUrls: ['./student-payments.component.css']
})
export class StudentPaymentsComponent implements OnInit{

  constructor(
    private paymentService: PaymentApiService,
    private studentService: StudentApiService
  ) {}

  isLoadingInformations = false;

  errorMessage: any = null;
  successMessage: any = null;

  fromDate: string = null;
  untilDate: string = null;

  payments: any = null;

  idForSearch = null;
  emptyInput = false;

  ngOnInit() {
    this.idForSearch = this.studentService.idForGetStudentInfo;
    this.getStudentCoursesByStudentId();
  }

  searchPaymentsByCreationDate(){

    if ((this.fromDate === null || this.fromDate === "" || this.fromDate === undefined) && (this.untilDate === null || this.untilDate === "" || this.untilDate === undefined)) {
      this.emptyInput = false;
      this.getStudentCoursesByStudentId();
    }else if (((this.fromDate === null || this.fromDate === "" || this.fromDate === undefined) && this.untilDate !== null) || (this.fromDate !== null && (this.untilDate === null || this.untilDate === "" || this.untilDate === undefined))) {
      this.emptyInput = true
    }else{
      this.emptyInput = false;
      this.getPaymentsByStudentIdAndReceipDate()
    }

  }

  getStudentCoursesByStudentId(){
    this.isLoadingInformations = true;
    this.paymentService
      .getPaymentsByStudentId(this.idForSearch)
      .subscribe(
        (responseData) => {
          this.errorMessage = null;
          setTimeout(() => {
            this.isLoadingInformations = false;
            this.payments = responseData;
            console.log(responseData);
          }, 1500);
        },
        (error) => {
          console.log(error);

          if (error.status === 404) {
            this.payments = null;
            this.isLoadingInformations = false;
            console.log(error);
            return (this.errorMessage = null);
          } else {
            this.payments = null;
            this.isLoadingInformations = false;
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }

  getPaymentsByStudentIdAndReceipDate(){
    this.isLoadingInformations = true;
    this.paymentService
      .getPaymentsByStudentIdAndReceipDate(this.idForSearch, this.fromDate, this.untilDate)
      .subscribe(
        (responseData) => {
          this.errorMessage = null;
          setTimeout(() => {
            this.isLoadingInformations = false;
            this.payments = responseData;
            console.log(responseData);
            this.fromDate = null;
            this.untilDate = null;
          }, 1500);
        },
        (error) => {
          console.log(error);

          if (error.status === 404) {
            this.payments = null;
            this.isLoadingInformations = false;
            console.log(error);
            this.fromDate = null;
            this.untilDate = null;
            return (this.errorMessage = null);
          } else {
            this.payments = null;
            this.isLoadingInformations = false;
            console.log(error);
            this.fromDate = null;
            this.untilDate = null;
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }

  getPaymentsByIdAndSendByEmail(id: number){

    this.errorMessage = null;

    this.paymentService
      .getPaymentsByIdAndSendByEmail(id)
      .subscribe(
        (responseData) => {
          this.successMessage = "Email sent with success..."
          setTimeout(() => {
            this.successMessage = null;
            console.log(responseData);
          }, 2000);
        },
        (error) => {
          console.log(error);

          if (error.status === 404) {
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          } else {
            console.log(error);
            return (this.errorMessage =
              'Opss...Something is not working but we will fix soon. Please try again later');
          }
        }
      );
  }


}
