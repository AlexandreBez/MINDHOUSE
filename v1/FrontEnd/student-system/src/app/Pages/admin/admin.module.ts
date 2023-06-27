import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { JWTTokenVerify } from 'src/app/guard/JWTTokenVerify.guard';
import { HeaderComponent } from './header/header.component';
import { ClockModule } from '../components/clock/clock.module';
import { FormsModule } from '@angular/forms';
import { AppService } from 'src/app/app.service';
import { RoleAdminCheck } from 'src/app/guard/admin/RoleAdminCheck.guard';

/**
 * The AdminModule is responsible for declaring, importing, and exporting the AdminComponent.
 * @summary This module is used to handle the administration workspace main page in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [AdminComponent, HeaderComponent],
  imports: [
    HttpClientModule,
    CommonModule,
    ClockModule,
    FormsModule,
    RouterModule.forChild([
      {
        path: 'Admin-Workstation',
        component: AdminComponent,
        canActivate: [JWTTokenVerify, RoleAdminCheck],
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./home/home.module').then((m) => m.HomeModule),
            canActivate: [RoleAdminCheck]
          },
          {
            path: '',
            loadChildren: () =>
              import('./users/users.module').then((m) => m.UsersModule),
            canActivate: [RoleAdminCheck]
          },
        ],
      },
    ])
  ],
  providers: [
    AppService
  ]
})
export class AdminModule {}
