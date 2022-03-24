import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientesComponent } from './clientes.component';
import { FormCliComponent } from './form-cli/form-cli.component';
import { DetailCliComponent } from './detail-cli/detail-cli.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from '../rutas/app-routing.module';



@NgModule({
  declarations: [
    ClientesComponent,
    FormCliComponent,
    DetailCliComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule
  ],
  exports:[
      ClientesComponent,
      FormCliComponent,
      DetailCliComponent
  ]
})
export class ClientesModule { }
