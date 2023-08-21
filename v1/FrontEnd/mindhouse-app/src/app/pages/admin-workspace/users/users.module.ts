import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersComponent } from './users.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { Token } from '../../global/guards/Token.guard';
import { Role } from '../../global/guards/Role.guard';
import { UsersService } from './api/users.service';
import { FormsModule } from '@angular/forms';
import { SpinnerModule } from '../../global/loader/spinner.module';

@NgModule({
  declarations: [
    UsersComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'Users', component: UsersComponent, canActivate: [Token, Role]}
    ]),
    FormsModule,
    SpinnerModule
  ],
  providers: [
    UsersService
  ]
})
export class UsersModule { }
