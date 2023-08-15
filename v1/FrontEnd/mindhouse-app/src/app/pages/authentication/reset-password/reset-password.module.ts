import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ResetPasswordComponent } from './reset-password.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SpinnerModule } from '../../global/loader/spinner.module';
import { ValidToken } from './api/guard/ValidToken.guard';
import { ResetPasswordService } from './api/resetPassword.service';

@NgModule({
  declarations: [
    ResetPasswordComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'ResetPassword', component: ResetPasswordComponent, canActivate: [ValidToken]},
    ]),
    FormsModule,
    SpinnerModule
  ],
  providers:[
    ResetPasswordService
  ]
})
export class ResetPasswordModule { }
