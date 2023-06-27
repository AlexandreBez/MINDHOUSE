import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthAPIService } from '../api/auth-api.service';
import { LoginReqModel } from '../api/interface/loginReq';

/**
 * LoginComponent for handling user login functionality.
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{

  /**
   * Flag indicating whether the login operation is in progress.
   */
  isLoading: boolean = false;

  /**
   * Error message displayed in case of login failure.
   */
  errorMsg: any = null;

  /**
   * Reference to the login form.
   */
  @ViewChild('loginForm') login: NgForm;

  /**
   * The constructor for the LoginComponent class.
   *
   * @constructor
   * @param {AuthAPIService} authApiService - The authentication instance.
   * @param {Router} router - The router instance.
   */
  constructor(private authApiService: AuthAPIService, private router: Router) {}

  /**
   * Performs initialization logic when the component is initialized.
   * Removes the 'email', 'tokenIsValid', and 'token' items from the local storage.
   */
  ngOnInit() {
    localStorage.removeItem('email');
    localStorage.removeItem('tokenIsValid');
    localStorage.removeItem('token');
  }

  /**
   * Handles the login process when the login button is clicked.
   * Retrieves the form data, sends a login request, and handles the response.
   *
   * @returns {void}
   */
  onLogin(){
    
    this.isLoading = true;

    let login_user: LoginReqModel = {
      username: this.login.value.username.trim(),
      password: this.login.value.password.trim()
    };

    this.authApiService.login(login_user).subscribe(
      (data) => {
        console.log(data)
        localStorage.setItem("id", data.id);
        localStorage.setItem("name", data.name);
        localStorage.setItem("token", data.token);
        localStorage.setItem("expiration", data.expiration);
        localStorage.setItem("role", data.role);
        this.isLoading = false;
        this.router.navigate(['app']);
      },
      (error) => {
        console.log(error);
        switch (error.status) {
          case 401:
            console.log(error);
            this.isLoading = false;
            return this.errorMsg = "Wrong Email and/or password...";
          default:
            console.log(error);
            this.isLoading = false;
            return this.errorMsg = "Oops... something went wrong";
        }
      }
    );

    setTimeout(() => {
      this.errorMsg = null;
    }, 4000);
    
  }

}