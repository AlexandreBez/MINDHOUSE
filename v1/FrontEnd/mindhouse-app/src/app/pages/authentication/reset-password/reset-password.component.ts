import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ResetPasswordService } from './api/resetPassword.service';
import { Router } from '@angular/router';
import { ResetPasswordRequest } from './api/interface/resetPasswordRequest';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss'],
})
export class ResetPasswordComponent {
  isLoading: boolean = false;
  errorMessage: any = null;
  successMessage: any = null;
  showPassword: boolean = false;
  newPasswordData: string = null;
  confirmPasswordData: string = null;
  uppercaseRegex: any = /(?=.*[A-Z])/;
  specialCharRegex = /(?=.*[!@#$%^&*()_+|[\]{};:'",.<>?])/;
  @ViewChild('resetPasswordForm') resetPasswordFormData: NgForm;

  constructor(
    private resetPassword: ResetPasswordService,
    private router: Router
  ) {}

  showPwd() {
    this.showPassword = !this.showPassword;
  }

  changeTemporaryPassword() {
    const get_reset_data = localStorage.getItem('RESET_PWD');
    const decripted_data = atob(get_reset_data);
    const parsed_data = JSON.parse(decripted_data);

    this.errorMessage = null;
    this.isLoading = true;

    let reset_pwd_data: ResetPasswordRequest = {
      email: parsed_data.email,
      token: parsed_data.token,
      password: this.resetPasswordFormData.value.newPassword.trim(),
    };

    this.resetPassword.resetPassword(reset_pwd_data).subscribe(
      (data) => {
        this.isLoading = false;
        console.info(data);
        this.successMessage = data.message;
        setTimeout(() => {
          localStorage.removeItem('RESET_PWD');
          this.router.navigate(['/login']);
        }, 3000);
      },
      (error) => {
        console.warn(error);
        this.isLoading = false;
        this.errorMessage = 'Oops... something went wrong';
      }
    );
  }
}
