import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from './spinner.component';

/**
 * This module imports the CommonModule and RoutesModule, and declares and exports the SpinnerComponent.
 */
@NgModule({
  declarations: [
    SpinnerComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    SpinnerComponent
  ]
})
export class SpinnerModule { }
