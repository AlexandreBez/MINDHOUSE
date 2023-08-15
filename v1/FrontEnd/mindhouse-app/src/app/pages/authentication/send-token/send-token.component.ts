import { Component } from '@angular/core';
import { SendTokenService } from './api/sendToken.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-send-token',
  templateUrl: './send-token.component.html',
  styleUrls: ['./send-token.component.scss'],
})
export class SendTokenComponent {
  isLoading: boolean = false;
  errorMessage: any = null;
  successMessage: any = null;
  username: string = null;
  specialCharRegex = /(?=.*[@])/;

  constructor(
    private sendTokenService: SendTokenService,
    private router: Router
  ) {}

  sendToken() {
    this.errorMessage = null;
    this.isLoading = true;
    this.sendTokenService.sendToken(this.username).subscribe(
      (data) => {
        this.isLoading = false;
        this.successMessage = data.message;
        console.info(data);
        localStorage.setItem("RESET_PWD", btoa(this.username));
        setTimeout(() => {
          this.successMessage = null;
          this.router.navigate(['/ValidateToken'])
        }, 3000);
      },
      (error) => {
        console.warn(error);
        this.isLoading = false;
        switch (error.status) {
          case 404:
            return (this.errorMessage = 'Email not found...');
          default:
            return (this.errorMessage = 'Oops... something went wrong');
        }
      }
    );
  }
}
