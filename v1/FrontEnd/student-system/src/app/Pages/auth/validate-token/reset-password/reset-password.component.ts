import { Component } from '@angular/core';
import { AuthAPIService } from '../../api/auth-api.service';
import { Router } from '@angular/router';
import { ResetPwdHelper } from '../../api/interface/resetPwdHelper';

/**
 * ResetPasswordComponent for handling user access functionality.
 */
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})
export class ResetPasswordComponent{
  /**
   * Flag indicating whether the login operation is in progress.
   */
  isLoading: boolean = false;
  /**
   * variable to handle the user password.
   */
  password_input: string = null;
  /**
   * variable to handle the password equality.
   */
  confirmPassword: string = null;
  /**
   * Flag indicating if the password is visible or not.
   */
  showPassword: boolean = false;
  /**
   * Error message displayed in case of failure.
   */
  errorMsg = null;
  /**
   * Success message displayed in case of success.
   */
  successMsg = null;

  /**
   * The constructor for the ResetPasswordComponent class.
   *
   * @constructor
   * @param {AuthAPIService} authApiService - The authentication instance.
   * @param {Router} router - The router instance.
   */
  constructor(private authApiService: AuthAPIService, private router: Router){}

  /**
   * Toggles the visibility of the password field.
   */
  changePwdVisibility() {
    this.showPassword = !this.showPassword;
    this.isLoading = false;
  }

  /**
   * Handles the reset password process when the Reset Password button is clicked.
   * Retrieves the form data, sends a reset request, and handles the response.
   *
   * @returns {void}
   */
  resetPassword(){
    this.isLoading = true;
    let resetPwdHelper: ResetPwdHelper = {
      token: localStorage.getItem('token'),
      password: this.password_input.trim(),
      email: localStorage.getItem('email')
    };

    this.authApiService.resetPassword(resetPwdHelper)
    .subscribe(
      data => {
        this.isLoading = false;
        this.successMsg = data.message;
        localStorage.clear();
        setTimeout(() => {
          this.router.navigate(['login']);
        }, 2000);
      },
      error => {
        console.log(error);
        this.isLoading = false;
        this.errorMsg = 'Oops... something went wrong';
      }
    )
  }
}
