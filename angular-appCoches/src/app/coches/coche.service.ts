import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { AuthService } from '../usuarios/auth.service';
import { Coche } from './coche';


@Injectable({
  providedIn: 'root'
})
export class CocheService {

  urlEndPoint:string ="http://localhost:8087/api/coches";

  urlEndPointPost:string ="http://localhost:8087/api/coche/guardar/coche";

  constructor( private http:HttpClient,private authService:AuthService) { }

  httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  agregarAuthorizationHeader():any{
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer '+token);
    }

    return this.httpHeaders;
  }

  getCoches():Observable<Coche[]>{
    return this.http.get(this.urlEndPoint).pipe(
      map( (response) => response as Coche[] )
    );
  }

  //metodo de post para insertar clientes
  create(coche:Coche):Observable<Coche>{
    return this.http.post<Coche>(this.urlEndPoint,coche, { headers: this.agregarAuthorizationHeader() });
  }

  //buscar cliente por id
  getCoche(id:number):Observable<Coche>{
    return this.http.get<Coche>(`${this.urlEndPoint}/${id}`,{ headers: this.agregarAuthorizationHeader() })
  }

  //actualizar cliente
  update(coche:Coche):Observable<Coche>{
    return this.http.put<Coche>(`${this.urlEndPoint}/${coche.id}`, coche,{ headers: this.agregarAuthorizationHeader() })
  }

  //eliminar cliente
  delete(id:number):Observable<Coche>{
    return this.http.delete<Coche>(`${this.urlEndPoint}/${id}`,{ headers: this.agregarAuthorizationHeader() })
  }



  //subir imagen
  subirImagen(archivo: File, id:any):Observable<HttpEvent<any>>{
    let formData = new FormData();

    formData.append("archivo",archivo);
    formData.append("id",id);

    let httpHeaders = new HttpHeaders();

    let token= this.authService.token;

    if(token != null){
      httpHeaders = httpHeaders.append('Authorization','Bearer '+token);
    }

    const req = new HttpRequest('POST',`${this.urlEndPoint}/upload`,formData,{headers:httpHeaders});

    return this.http.request(req).pipe(
      resp =>resp
    );
  }









}
