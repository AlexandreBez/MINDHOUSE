import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

/**
 * The routing configuration for the application.
 */
const appRoutes: Routes = [
  { 
    /**
     * The default route that redirects to the app page.
     */
    path: '', 
    redirectTo: '/', 
    pathMatch: 'full' 
  },
  {
    /**
     * The lazy-loaded module for the Students page.
     */
    path: '',
    loadChildren: () => import('../auth/login/login.module').then((m) => m.LoginModule),
  },
  { 
    /**
     * The fallback route that redirects to the Not Found page.
     */
    path: '**', 
    redirectTo: '/404' 
  },
];

/**
 * The module that handles routing for the application.
 */
@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { useHash: true })],
  exports: [RouterModule],
})
export class RoutesModule {}