import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/clientes/cliente';
import { Coche } from 'src/app/coches/coche';
import swal from 'sweetalert2';
import { Alquiler } from '../alquiler';
import { AlquilerService } from '../alquiler.service';

@Component({
  selector: 'app-form-alq',
  templateUrl: './form-alq.component.html',
  styleUrls: []
})
export class FormAlqComponent implements OnInit {

  titulo:string ="Nuevo Alquiler";

  alquiler: Alquiler = new Alquiler();

  coches!:Coche[];
  clientes!:Cliente[];


  constructor( private alquilerService:AlquilerService,
    private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {

      this.alquilerService.getCoches().subscribe(
        resp => this.coches = resp
      );
      this.alquilerService.getClientes().subscribe(
        resp => this.clientes = resp
      );


      this.activatedRoute.paramMap.subscribe(
        params =>{
          let id = +params.get('id')!;
          if(id){
            this.alquilerService.getAlquiler(id).subscribe(
              (resp) => this.alquiler = resp
            )
          }
        }
      );
  }

  compararCoche(o1:Coche,o2:Coche):boolean{
    if(o1 === undefined && o2 ===undefined){
      return true;
    }

    return o1 === null || o2===null ||
     o1===undefined ||
      o2===undefined ? false : o1.id===o2.id;

  }
  compararCliente(o1:Cliente,o2:Cliente):boolean{
    if(o1 === undefined && o2 ===undefined){
      return true;
    }

    return o1 === null || o2===null ||
     o1===undefined ||
      o2===undefined ? false : o1.id===o2.id;

  }


  create():void{
    console.log("formulario enviado");
    console.log(this.alquiler);
    this.alquilerService.create(this.alquiler).subscribe(
      resp => {
        swal('Nuevo Alquiler',`${this.alquiler.cliente.nombre} creado con éxito`,'success');
        this.router.navigate(['/alquileres']);
      },
      err=>{
        console.log('Codigo de error backend',err.status);
      }
    );
  }

  update():void{
    console.log(this.alquiler);
    this.alquilerService.update(this.alquiler).subscribe(
      resp=>{
        this.router.navigate(['/alquileres']);
        swal('Alquiler Actualizado',`${this.alquiler.cliente.nombre}`,'success');
      },
      err=>{
        console.error('Codigo del error desde el backend'+err.status);
        console.error(err.error.errros)
      }
    );
  }

}
