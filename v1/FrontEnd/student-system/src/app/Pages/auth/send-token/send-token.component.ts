import { Component, OnInit } from '@angular/core';
import { AuthAPIService } from '../api/auth-api.service';
import { Router } from '@angular/router';

/**
 * SendTokenComponent for handling token generation functionality.
 */
@Component({
  selector: 'app-send-token',
  templateUrl: './send-token.component.html',
  styleUrls: ['./send-token.component.css'],
})
export class SendTokenComponent implements OnInit {

  /**
   * Flag indicating whether the login operation is in progress.
   * @type {boolean}
   */
  isLoading: boolean = false;

  /**
   * Error message displayed when an error occurs.
   * @type {string}
   */
  errorMsg = null;

  /**
   * Success message displayed when the token is sent successfully.
   * @type {string}
   */
  successMsg = null;

  /**
   * Email used for sending the token.
   * @type {string}
   */
  emailForSendToken = null;

  /**
   * The constructor for the SendTokenComponent class.
   *
   * @constructor
   * @param {AuthAPIService} authApiService - The authentication instance.
   * @param {Router} router - The router instance.
   */
  constructor(private authApiService: AuthAPIService, private router: Router) {}

  /**
   * Initializes the component.
   */
  ngOnInit() {
    localStorage.removeItem('email');
    localStorage.removeItem('tokenIsValid');
    localStorage.removeItem('token');
  }

  /**
   * Handles the token process when the send token button is clicked.
   * Retrieves the form data, sends a token geenration request, and handles the response.
   *
   * @returns {void}
   */
  onSendToken() {
    this.isLoading = true;
    this.errorMsg = null;
    this.successMsg = null;
    this.authApiService.sendToken(this.emailForSendToken).subscribe(
      (data) => {
        this.isLoading = false;
        this.successMsg = data.message;

        setTimeout(() => {
          this.successMsg = null;
          localStorage.setItem('email', this.emailForSendToken);
          this.router.navigate(['ValidateToken']);
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

  /**
   * Cancels the token sending process and navigates to the login page.
   */
  cancel() {
    localStorage.removeItem('email');
    localStorage.removeItem('tokenIsValid');
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
}
