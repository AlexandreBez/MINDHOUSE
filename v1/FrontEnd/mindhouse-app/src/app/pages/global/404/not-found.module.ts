import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NotFoundComponent } from './not-found.component';

/**
 * The NotFoundModule is responsible for declaring, importing, and exporting the NotFoundComponent.
 * @summary This module is used to handle the 404 Not Found error in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [NotFoundComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '404', component: NotFoundComponent }]),
  ],
  exports: [NotFoundComponent],
})
export class NotFoundModule {}
