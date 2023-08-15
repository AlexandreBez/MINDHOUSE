import { Component } from '@angular/core';
import { ValidateTokenService } from './api/validateToken.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-validate-token',
  templateUrl: './validate-token.component.html',
  styleUrls: ['./validate-token.component.scss'],
})
export class ValidateTokenComponent {
  isLoading: boolean = false;
  errorMessage: any = null;
  successMessage: any = null;
  token: string = null;

  constructor(
    private validateTokenService: ValidateTokenService,
    private router: Router
  ) {}

  validateToken() {
    this.errorMessage = null;
    this.isLoading = true;
    const username = localStorage.getItem('RESET_PWD');

    this.validateTokenService
      .validateToken(atob(username), this.token)
      .subscribe(
        (data) => {
          this.isLoading = false;
          this.successMessage = data.message;

          console.info(data);

          const reset_pwd_infos = {
            email: atob(username),
            token: this.token,
            is_valid: 'true',
          };
          const jsonString = JSON.stringify(reset_pwd_infos);
          const encodedJsonString = btoa(jsonString);
          localStorage.setItem('RESET_PWD', encodedJsonString);
          setTimeout(() => {
            this.successMessage = null;
            this.router.navigate(['/ResetPassword']);
          }, 2000);
        },
        (error) => {
          this.isLoading = false;
          console.warn(error);
          switch (error.status) {
            case 404:
              return (this.errorMessage = error.error.message);
            case 401:
              return (this.errorMessage = error.error.message);
            default:
              return (this.errorMessage = 'Oops... something went wrong');
          }
        }
      );
  }

  resendToken() {
    this.errorMessage = null;
    this.isLoading = true;
    const username = localStorage.getItem('RESET_PWD');

    this.validateTokenService.resendToken(atob(username)).subscribe(
      (data) => {
        this.isLoading = false;
        this.successMessage = data.message;
        console.info(data);
        setTimeout(() => {
          this.successMessage = null;
        }, 3000);
      },
      (error) => {
        console.warn(error);
        this.isLoading = false;
        switch (error.status) {
          case 404:
            return (this.errorMessage = error.message);
          default:
            return (this.errorMessage = 'Oops... something went wrong');
        }
      }
    );
  }
}
