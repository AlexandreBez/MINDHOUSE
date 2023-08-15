import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { RouterModule } from '@angular/router';
import { Token } from '../../global/guards/Token.guard';
import { Role } from '../../global/guards/Role.guard';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { FormsModule } from '@angular/forms';
import { HomeService } from './api/home.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild([
      { path: 'Home', component: HomeComponent, canActivate: [Token, Role]}
    ]),
    NgxChartsModule,
    FormsModule,
  ],
  providers: [
    HomeService
  ]
})
export class HomeModule { }
