import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { RouterModule } from '@angular/router';
import { AuthenticationService } from './api/authentication.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SpinnerModule } from '../../global/loader/spinner.module';
/**
 * @author Lucas Alexandre Bez Piancoski
 * @date 24/07/2023 - 16:22:22
 * @version 1.0.0
 * Login Module to handle the login page
 */
@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'login', component: LoginComponent},
    ]),
    FormsModule,
    SpinnerModule
  ],
  providers: [
    AuthenticationService
  ]
})
export class LoginModule { }
