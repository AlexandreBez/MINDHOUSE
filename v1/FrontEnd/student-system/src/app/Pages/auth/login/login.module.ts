import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { SpinnerModule } from 'src/app/Pages/components/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { AuthAPIService } from '../api/auth-api.service';

/**
 * The LoginModule is responsible for declaring, importing, and exporting the LoginComponent.
 * @summary This module is used to handle the login page in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'login', component: LoginComponent},
    ]),
    SpinnerModule,
    FormsModule,
  ],
  providers:[
    AuthAPIService
  ]
})
export class LoginModule {

}
