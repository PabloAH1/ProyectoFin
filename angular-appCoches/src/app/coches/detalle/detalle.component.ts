import { HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Coche } from '../coche';
import { CocheService } from '../coche.service';



@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styles: [
  ]
})
export class DetalleComponent implements OnInit {

  coche!:Coche;
  fotoSeleccionada!:File;
  progreso!:number;


  constructor( private cocheService:CocheService,
     private activedRoute:ActivatedRoute) { }

  ngOnInit(): void {

    this.activedRoute.paramMap.subscribe(
      params => {
        let id:number = +params.get('id')!;

        if(id){
          this.cocheService.getCoche(id)
          .subscribe( resp => this.coche = resp);
        }


      }
    );

  }

  seleccionarImagen(event:any){
    this.fotoSeleccionada = event.target.files[0];
    console.log(this.fotoSeleccionada);
  }

  subirImagen():void{
    if(!this.fotoSeleccionada){
      swal('Error','Debe selecionar una imagen','error');

    }else{
      this.cocheService.subirImagen(this.fotoSeleccionada,this.coche.id)
      .subscribe(event =>{

        if(event.type === HttpEventType.UploadProgress){
          this.progreso = Math.round((event.loaded/event.total!)*100);
        }else if(event.type === HttpEventType.Response){
          let response:any = event.body;
          this.coche = response.cliente as Coche;

          swal('La imagen se ha subido correctamente!',response.mensaje,'success');
        }

      });
    }
  }



}
