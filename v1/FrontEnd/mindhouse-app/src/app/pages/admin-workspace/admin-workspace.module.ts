import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminWorkspaceComponent } from './admin-workspace.component';
import { RouterModule } from '@angular/router';
import { Token } from '../global/guards/Token.guard';
import { Role } from '../global/guards/Role.guard';
import { ClockModule } from '../global/clock/clock.module';

@NgModule({
  declarations: [
    AdminWorkspaceComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'admin-workspace', component: AdminWorkspaceComponent, canActivate: [Token, Role], children:[
        {
          path: '',
          loadChildren: () => import('./home/home.module').then((m) => m.HomeModule),
        },
        {
          path: '',
          loadChildren: () => import('./users/users.module').then((m) => m.UsersModule),
        },
      ]},
    ]),
    ClockModule
  ]
})
export class AdminWorkspaceModule { }
