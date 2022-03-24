import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Cliente } from '../clientes/cliente';
import { Coche } from '../coches/coche';
import { AuthService } from '../usuarios/auth.service';
import { Alquiler } from './alquiler';

@Injectable({
  providedIn: 'root'
})
export class AlquilerService {

  urlEndPoint:string ="http://localhost:8087/api/alquileres";

  urlEndPointPost:string ="http://localhost:8087/api/alquiler/guardar/alquiler";

  constructor( private http:HttpClient,private authService:AuthService) { }

  httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  agregarAuthorizationHeader():any{
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer '+token);
    }

    return this.httpHeaders;
  }


  getAlquileres():Observable<Alquiler[]>{
    return this.http.get(this.urlEndPoint,{ headers: this.agregarAuthorizationHeader() }).pipe(
      map( (response) => response as Alquiler[] )
    );
  }

  //metodo de post para insertar clientes
  create(alquiler:Alquiler):Observable<Alquiler>{
    return this.http.post<Alquiler>(this.urlEndPoint,alquiler, { headers: this.agregarAuthorizationHeader() });
  }

  //buscar cliente por id
  getAlquiler(id:number):Observable<Alquiler>{
    return this.http.get<Alquiler>(`${this.urlEndPoint}/${id}`,{ headers: this.agregarAuthorizationHeader() })
  }

  //actualizar cliente
  update(alquiler:Alquiler):Observable<Alquiler>{
    return this.http.put<Alquiler>(`${this.urlEndPoint}/${alquiler.id}`, alquiler,{ headers: this.agregarAuthorizationHeader() })
  }

  //eliminar cliente
  delete(id:number):Observable<Alquiler>{
    return this.http.delete<Alquiler>(`${this.urlEndPoint}/${id}`,{ headers: this.agregarAuthorizationHeader() })
  }

  // mostrar coches
  getCoches():Observable<Coche[]>{
    return this.http.get<Coche[]>(`${this.urlEndPoint}/coches`,{ headers: this.agregarAuthorizationHeader() });
  }

  //mostrar clientes
  getClientes():Observable<Cliente[]>{
    return this.http.get<Cliente[]>(`${this.urlEndPoint}/clientes`,{ headers: this.agregarAuthorizationHeader() });
  }


}
