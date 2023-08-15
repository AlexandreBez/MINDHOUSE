import { Component, ViewChild } from '@angular/core';
import { TempPasswordService } from './api/tempPassword.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { TempPasswordRequest } from './api/Interface/TempPasswordRequest';


@Component({
  selector: 'app-temporary-password',
  templateUrl: './temporary-password.component.html',
  styleUrls: ['./temporary-password.component.scss'],
})
export class TemporaryPasswordComponent {

  isLoading: boolean = false;
  errorMessage: any = null;
  successMessage: any = null;
  showPassword: boolean = false;
  newPasswordData: string = null;
  confirmPasswordData: string = null;
  uppercaseRegex: any = /(?=.*[A-Z])/;
  specialCharRegex = /(?=.*[!@#$%^&*()_+|[\]{};:'",.<>?])/;
  @ViewChild('tempPasswordForm') tempPasswordFormData: NgForm;

  constructor(
    private tempPassword: TempPasswordService,
    private router: Router,
  ) {}

  showPwd(){
    this.showPassword = !this.showPassword;
  }

  changeTemporaryPassword() {

    const get_auth_cookie_data = localStorage.getItem('AUTH_COOKIE');
    const decripted_data = atob(get_auth_cookie_data);
    const parsed_data = JSON.parse(decripted_data);

    this.isLoading = true;
    let changed_pwd: TempPasswordRequest = {
      id: btoa(parsed_data.id),
      new_password: btoa(this.tempPasswordFormData.value.newPassword.trim()),
      confirm_password: btoa(this.tempPasswordFormData.value.confirmPassword.trim()),
    };
    this.tempPassword.changeTemporaryPassword(changed_pwd).subscribe(
      (data) => {
        this.isLoading = false;
        console.info(data);
        this.successMessage = data.message;
        localStorage.setItem('temp_password', 'false');
        this.router.navigate(['app']);
      },
      (error) => {
        console.warn(error);
        this.isLoading = false;
        this.errorMessage = error.error.message;
      }
    );

    setTimeout(() => {
      this.errorMessage = null;
      this.successMessage = null;
    }, 3000);
  }
}
