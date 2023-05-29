import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';


const routes: Routes = [
    { path: '', loadChildren: './homepage/homepage.module#HomepageModule'},
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'usuarios', loadChildren: './usuarios/usuarios.module#UsuariosModule'}, 
    { path: 'eventos', loadChildren: './eventos/eventos.module#EventosModule' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
