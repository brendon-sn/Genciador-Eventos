import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventoCreateComponent } from './components/evento-create/evento-create.component';
import { EventoReadComponent } from './components/evento-read/evento-read.component';
import { MotivoCreateComponent } from './components/motivo-create/motivo-create.component';


const routes: Routes = [
  { path: '', redirectTo: 'listar' },
  { path: 'listar', component: EventoReadComponent },
  { path: 'criar', component: EventoCreateComponent },
  { path: 'motivo', component: MotivoCreateComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventosRoutingModule { }
