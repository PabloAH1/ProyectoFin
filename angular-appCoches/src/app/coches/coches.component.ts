import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Coche } from './coche';
import { CocheService } from './coche.service';




@Component({
  selector: 'app-coches',
  templateUrl: './coches.component.html',
  styles: [
  ]
})
export class CochesComponent implements OnInit {

  imagenSrc!:string;
  coches!: Coche[];

  constructor(private servicio:CocheService, public authService:AuthService) { }

  ngOnInit(): void {

    this.imagenSrc = 'assets/avatar.jpg';

    this.servicio.getCoches().subscribe(
      resp => this.coches =resp
    );
  }

  delete( coche:Coche):void{
    swal({
      title:'Está seguro?',
      text:`Seguro que desea eliminar al coche ${coche.marca} ${coche.modelo}`,
      type:'warning',
      showCancelButton:true,
      confirmButtonColor:'#3085d6',
      cancelButtonColor:'#d33',
      confirmButtonText:'Si, eliminar!',
      cancelButtonText:'No, cancelar!',
      confirmButtonClass:'btn btn-success',
      cancelButtonClass:'btn btn-danger',
      buttonsStyling:false,
      reverseButtons:true
    }).then((result)=>{
      if(result.value){
        this.servicio.delete(coche.id).subscribe(
          resp =>{
            this.coches = this.coches.filter(cli => cli !== coche)
            swal('Coche eliminado', `Coche ${coche.marca} eliminado con éxito`,'success');
          }
        )
      }
    });

  }



}
