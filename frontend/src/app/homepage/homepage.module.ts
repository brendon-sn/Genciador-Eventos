import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageRoutingModule } from './homepage-routing.module';
import { InicioComponent } from './inicio/inicio.component';


@NgModule({
  declarations: [InicioComponent],
  imports: [
    CommonModule,
    HomepageRoutingModule
  ]
})
export class HomepageModule { }
