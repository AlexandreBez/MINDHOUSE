import { NgModule } from '@angular/core';
import { UsersComponent } from './users.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { JWTTokenVerify } from 'src/app/guard/JWTTokenVerify.guard';
import { SpinnerModule } from '../../components/spinner/spinner.module';
import { UserApiService } from './api/user-api.service';
import { AppService } from 'src/app/app.service';

/**
 * The UsersModule is responsible for declaring, importing, and exporting the UsersComponent.
 * @summary This module is used to handle the Users page in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [
    UsersComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    SpinnerModule,
    RouterModule.forChild([
      {
        path: 'Users',
        component: UsersComponent,
        canActivate: [JWTTokenVerify],
      }
    ])
  ],
  providers:[
    UserApiService,
    AppService
  ]
})
export class UsersModule { }
