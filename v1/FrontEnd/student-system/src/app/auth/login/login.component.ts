import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginReqModel } from '../api/interface/loginReq';
import { AuthAPIService } from '../api/auth-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  isLoading: boolean = false;
  errorMsg: any = null;

  @ViewChild('loginForm') login: NgForm;

  constructor(private authApiService: AuthAPIService, private router: Router) {}

  ngOnInit() {
    
  }

  onLogin(){
    
    this.isLoading = true;
    
    // Get the form data
    const formData = this.login.value;

    let login_user: LoginReqModel = {
      username: formData.username.trim(),
      password: formData.password.trim()
    };

    this.authApiService.login(login_user).subscribe(
      (data) => {
        localStorage.setItem("token", data.token);
        localStorage.setItem("expiration", data.expiration);
        localStorage.setItem("role", data.role);
        this.isLoading = false;
        this.router.navigate(['/']);
      },
      (error) => {
        switch (error.error.status) {
          case 401:
            console.log(error);
            this.isLoading = false;
            return this.errorMsg = "Wrong Email and/or password...";
          case 500:
            console.log(error);
            this.isLoading = false;
            return this.errorMsg = "Oops... something went wrong";
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