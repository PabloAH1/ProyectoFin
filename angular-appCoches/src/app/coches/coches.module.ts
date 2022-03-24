import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormComponent } from './form/form.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from '../rutas/app-routing.module';
import { DetalleComponent } from './detalle/detalle.component';
import { CochesComponent } from './coches.component';



@NgModule({
  declarations: [
    CochesComponent,
    FormComponent,
    DetalleComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule
  ],
  exports:[
    CochesComponent,
    FormComponent,
    DetalleComponent

  ]
})
export class CochesModule { }
