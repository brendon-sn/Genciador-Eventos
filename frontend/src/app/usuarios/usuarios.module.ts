import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuariosRoutingModule } from './usuarios-routing.module';
import { UsuariosReadComponent } from './components/usuarios-read/usuarios-read.component';
import { SharedModule } from '../shared/shared.module';
import { UsuariosCreateComponent } from './components/usuarios-create/usuarios-create.component';



@NgModule({
  declarations: [UsuariosReadComponent, UsuariosCreateComponent],
  imports: [
    CommonModule,
    UsuariosRoutingModule,
    SharedModule,
  ]
})
export class UsuariosModule { }
