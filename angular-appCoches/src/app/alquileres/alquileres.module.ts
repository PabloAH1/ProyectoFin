import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlquileresComponent } from './alquileres.component';
import { DetailAlqComponent } from './detail-alq/detail-alq.component';
import { FormAlqComponent } from './form-alq/form-alq.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from '../rutas/app-routing.module';



@NgModule({
  declarations: [
    AlquileresComponent,
    FormAlqComponent,
    DetailAlqComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule
  ],
  exports:[
    AlquileresComponent,
    FormAlqComponent,
    DetailAlqComponent
  ]

})
export class AlquileresModule { }
