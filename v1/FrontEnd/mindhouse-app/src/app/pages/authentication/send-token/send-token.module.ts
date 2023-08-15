import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SendTokenComponent } from './send-token.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SpinnerModule } from '../../global/loader/spinner.module';
import { SendTokenService } from './api/sendToken.service';



@NgModule({
  declarations: [
    SendTokenComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'SendToken', component: SendTokenComponent},
    ]),
    FormsModule,
    SpinnerModule
  ],
  providers: [SendTokenService]
})
export class SendTokenModule { }
