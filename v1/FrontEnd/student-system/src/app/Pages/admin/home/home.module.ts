import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeService } from './services/home.service';
import { SpinnerModule } from '../../components/spinner/spinner.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { JWTTokenVerify } from 'src/app/guard/JWTTokenVerify.guard';
import { HomeComponent } from './home.component';
import { HomeApiService } from './api/home-api.service';
import { AppService } from 'src/app/app.service';

/**
 * The HomeModule is responsible for declaring, importing, and exporting the HomeComponent.
 * @summary This module is used to handle the Homepage for the AdminModule in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    FormsModule,
    SpinnerModule,
    RouterModule.forChild([
      {
        path: 'Home',
        component: HomeComponent,
        canActivate: [JWTTokenVerify],
      }
    ]),
  ],
  providers: [
    HomeService,
    HomeApiService,
    AppService
  ]
})
export class HomeModule { }
