import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { SpinnerModule } from 'src/app/shared/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { AuthAPIService } from '../api/auth-api.service';

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forChild([
      { path: 'login', component: LoginComponent },
    ]),
    SpinnerModule,
    FormsModule,
  ],
  exports: [
    LoginComponent
  ],
  providers:[
    AuthAPIService
  ]
})
export class LoginModule {

}
