import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './rutas/app-routing.module';
import { AppComponent } from './app.component';

import { FooterModule } from './footer/footer.module';
import { HeaderModule } from './header/header.module';
import { UsuariosModule } from './usuarios/usuarios.module';
import {HttpClientModule} from '@angular/common/http';
import { AuthService } from './usuarios/auth.service';
import { CochesModule } from './coches/coches.module';
import { CocheService } from './coches/coche.service';
import { ClienteService } from './clientes/cliente.service';
import { ClientesModule } from './clientes/clientes.module';
import { AlquileresModule } from './alquileres/alquileres.module';


@NgModule({
  declarations: [
    AppComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CochesModule,
    FooterModule,
    HeaderModule,
    UsuariosModule,
    HttpClientModule,
    ClientesModule,
    AlquileresModule
  ],
  providers: [
    CocheService,
    AuthService,
    ClienteService,
    AlquileresModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
