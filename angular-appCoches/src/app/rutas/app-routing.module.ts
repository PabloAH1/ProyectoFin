import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlquileresComponent } from '../alquileres/alquileres.component';
import { DetailAlqComponent } from '../alquileres/detail-alq/detail-alq.component';
import { FormAlqComponent } from '../alquileres/form-alq/form-alq.component';
import { ClientesComponent } from '../clientes/clientes.component';
import { DetailCliComponent } from '../clientes/detail-cli/detail-cli.component';
import { FormCliComponent } from '../clientes/form-cli/form-cli.component';
import { CochesComponent } from '../coches/coches.component';

import { DetalleComponent } from '../coches/detalle/detalle.component';
import { FormComponent } from '../coches/form/form.component';
import { LoginComponent } from '../usuarios/login/login.component';

const routes: Routes = [

  { path:'', redirectTo:'/coches', pathMatch:'full' },
  { path:'clientes/editar/:id', component: FormCliComponent},
  { path:'clientes/ver/:id', component: DetailCliComponent },
  { path:'clientes/crear', component: FormCliComponent },
  { path:'clientes', component: ClientesComponent },
  { path:'coches/editar/:id', component: FormComponent },
  { path:'coches/ver/:id', component: DetalleComponent },
  { path:'coches/crear', component: FormComponent },
  { path:'coches', component: CochesComponent },
  { path:'alquileres/editar/:id', component: FormAlqComponent },
  { path:'alquileres/ver/:id', component: DetailAlqComponent },
  { path:'alquileres/crear', component: FormAlqComponent },
  { path:'alquileres', component: AlquileresComponent },
  { path:'login', component: LoginComponent },
  { path:'**', redirectTo:'' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
