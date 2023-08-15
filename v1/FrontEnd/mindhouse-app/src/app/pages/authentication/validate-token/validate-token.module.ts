import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ValidateTokenComponent } from './validate-token.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SpinnerModule } from '../../global/loader/spinner.module';
import { ValidateTokenService } from './api/validateToken.service';



@NgModule({
  declarations: [
    ValidateTokenComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'ValidateToken', component: ValidateTokenComponent},
    ]),
    FormsModule,
    SpinnerModule
  ],
  providers: [
    ValidateTokenService
  ]
})
export class ValidateTokenModule { }
