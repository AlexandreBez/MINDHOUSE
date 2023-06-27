import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ValidateTokenComponent } from './validate-token.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { SpinnerModule } from '../../components/spinner/spinner.module';
import { AuthAPIService } from '../api/auth-api.service';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ResetPasswordAccessGuard } from 'src/app/guard/reset-password-access.guard';
import { FormsModule } from '@angular/forms';

/**
 * The SendTokenModule is responsible for declaring, importing, and exporting the ValidateTokenComponent, and ResetPasswordComponent.
 * @summary This module is used to handle the validate and reset the password in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [
    ValidateTokenComponent,
    ResetPasswordComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'ValidateToken', component:  ValidateTokenComponent, children: [
        { path: 'UpdatePassword', component:  ResetPasswordComponent, canActivate: [ResetPasswordAccessGuard] }
      ]},
    ]),
    SpinnerModule,
    FormsModule
  ],
  providers: [
    AuthAPIService
  ]
})
export class ValidateTokenModule { }
