import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header.component';
import { RoutesModule } from 'src/app/routes/routing.modules';

/**
 * This module imports the CommonModule and RoutesModule, and declares and exports the HeaderComponent.
 */
@NgModule({
  declarations: [
  HeaderComponent
  ],
  imports: [
  CommonModule,
  RoutesModule
  ],
  exports: [
  HeaderComponent
  ]
  })
  export class HeaderModule { }
