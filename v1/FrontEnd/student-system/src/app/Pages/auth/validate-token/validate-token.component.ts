import { Component, OnInit } from '@angular/core';
import { AuthAPIService } from '../api/auth-api.service';
import { Router } from '@angular/router';

/**
 * ValidateTokenComponent for handling token validations functionality.
 */
@Component({
  selector: 'app-validate-token',
  templateUrl: './validate-token.component.html',
  styleUrls: ['./validate-token.component.css'],
})
export class ValidateTokenComponent implements OnInit {

  /**
   * Flag indicating whether the login operation is in progress.
   */
  isLoading: boolean = false;
  /**
   * Error message displayed in case of failure.
   */
  errorMsg = null;
  /**
   * Success message displayed in case of success.
   */
  successMsg = null;

  /**
   * Token typed by the email.
   */
  tokenForCheck = null;

  /**
   * The constructor for the ValidateTokenComponent class.
   *
   * @constructor
   * @param {AuthAPIService} authApiService - The authentication instance.
   * @param {Router} router - The router instance.
   */
  constructor(private authApiService: AuthAPIService, private router: Router) {}

  /**
   * Initializes the component.
   * If there is no email stored in the local storage, navigates to the 'Login' route.
   */  
  ngOnInit() {
    if (localStorage.getItem('email') == null) {
      this.router.navigate(['Login']);
    }
  }

  /**
   * Validates the token by sending a request to the server.
   * If the token is valid, sets the necessary values in the local storage and navigates to the 'UpdatePassword' route.
   * If there is an error during the validation process, displays the appropriate error message.
   */
  validateToken() {
    this.isLoading = true;
    this.errorMsg = null;
    this.successMsg = null;
    this.authApiService
      .validateToken(this.tokenForCheck, localStorage.getItem('email'))
      .subscribe(
        (data) => {
          this.isLoading = false;
          this.successMsg = data.message;
          localStorage.setItem('tokenIsValid', 'true');
          localStorage.setItem('token', this.tokenForCheck);
          setTimeout(() => {
            this.successMsg = null;
            this.router.navigate(['ValidateToken', 'UpdatePassword']);
          }, 2000);
        },
        (error) => {
          console.log(error);
          this.isLoading = false;
          if (error.status == 404) {
            this.errorMsg = error.message;
          } else if (error.status == 400) {
            this.errorMsg = error.message;
          } else {
            this.errorMsg = 'Oops... something went wrong';
          }
        }
      );
  }

  /**
   * Regenerates the token by sending a request to the server.
   * If the token regeneration is successful, displays the success message.
   * If there is an error during the token regeneration process, displays the appropriate error message.
   */
  regenerateToken() {
    this.isLoading = true;
    this.errorMsg = null;
    this.successMsg = null;
    this.authApiService.sendToken(localStorage.getItem('email')).subscribe(
      (data) => {
        this.isLoading = false;
        this.successMsg = data.message;

        setTimeout(() => {
          this.successMsg = null;
        }, 2000);
      },
      (error) => {
        console.log(error);
        this.isLoading = false;
        if (error.status == 404) {
          this.errorMsg = 'Email not found...';
        } else {
          this.errorMsg = 'Oops... something went wrong';
        }
      }
    );
  }

}
