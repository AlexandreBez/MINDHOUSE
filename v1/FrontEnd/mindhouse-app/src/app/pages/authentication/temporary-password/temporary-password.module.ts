import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TemporaryPasswordComponent } from './temporary-password.component';
import { Token } from '../../global/guards/Token.guard';
import { FormsModule } from '@angular/forms';
import { SpinnerModule } from '../../global/loader/spinner.module';
import { HttpClientModule } from '@angular/common/http';
import { TempPasswordService } from './api/tempPassword.service';

@NgModule({
  declarations: [
    TemporaryPasswordComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'tempPassword', component: TemporaryPasswordComponent, canActivate: [Token]},
    ]),
    FormsModule,
    SpinnerModule
  ],
  providers: [TempPasswordService]
})
export class TemporaryPasswordModule { }
