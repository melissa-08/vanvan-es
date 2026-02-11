import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./pages/login/login').then(m => m.Login) },
  {path: 'motoristas',loadComponent: () => import('./pages/motoristas/motoristas').then(m => m.Motoristas)},
];
