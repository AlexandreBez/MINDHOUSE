import { Component, ViewChild } from '@angular/core';
import { LoginRequest } from './api/Interface/LoginRequest';
import { AuthenticationService } from './api/authentication.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  isLoading: boolean = false;
  loginMessageError: any = null;
  @ViewChild('loginForm') loginFormData: NgForm;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}

  authenticateUser() {
    this.isLoading = true;
    let login_user: LoginRequest = {
      username: this.loginFormData.value.username.trim(),
      password: this.loginFormData.value.password.trim()
    };
    this.authenticationService.authenticateUser(login_user).subscribe(
      (data) => {
        this.isLoading = false;
        const cookieData = {
          id: data.id,
          name: data.name,
          role: data.role,
          token: data.token,
          expiration: data.expiration,
        };

        const jsonString = JSON.stringify(cookieData);
        const encodedJsonString = btoa(jsonString);
        localStorage.setItem("AUTH_COOKIE", encodedJsonString);
        localStorage.setItem("temp_password", data.is_temp_password);
        console.info(data);
        this.router.navigate(['app']);
      },
      (error) => {
        console.warn(error);
        this.isLoading = false;
        switch (error.status) {
          case 401:
            return (this.loginMessageError = 'Wrong Email and/or password...');
          default:
            return (this.loginMessageError = 'Oops... something went wrong');
        }
      }
    );

    setTimeout(() => {
      this.loginMessageError = null;
    }, 4000);
  }
}
