import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { SpinnerModule } from 'src/app/Pages/components/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { AuthAPIService } from '../api/auth-api.service';
import { SendTokenComponent } from './send-token.component';

/**
 * The SendTokenModule is responsible for declaring, importing, and exporting the SendTokenComponent.
 * @summary This module is used to handle the generate token and send to email in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [
    SendTokenComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'ResetPassword', component:  SendTokenComponent},
    ]),
    SpinnerModule,
    FormsModule,
  ],
  providers:[
    AuthAPIService
  ]
})
export class SendTokenModule { }
