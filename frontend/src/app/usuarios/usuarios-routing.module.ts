import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuariosCreateComponent } from './components/usuarios-create/usuarios-create.component';
import { UsuariosReadComponent } from './components/usuarios-read/usuarios-read.component';



const routes: Routes = [
  {path: '', component: UsuariosReadComponent},
  {path: 'criar', component: UsuariosCreateComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UsuariosRoutingModule{

}