import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Alquiler } from './alquiler';
import { AlquilerService } from './alquiler.service';

@Component({
  selector: 'app-alquileres',
  templateUrl: './alquileres.component.html',
  styleUrls: ['./alquileres.component.css']
})
export class AlquileresComponent implements OnInit {

  alquileres!: Alquiler[];

  constructor(private servicio:AlquilerService, public authService:AuthService) { }

  imagenSrc!:string;
  ngOnInit(): void {

    this.imagenSrc = 'assets/avatar.jpg';

    this.servicio.getAlquileres().subscribe(
      resp => this.alquileres =resp
    );
  }

  delete(alquiler:Alquiler):void{
    swal({
      title:'Está seguro?',
      text:`Seguro que desea eliminar el alquiler ${alquiler.cliente.nombre} ${alquiler.cliente.apellido}`,
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
        this.servicio.delete(alquiler.id).subscribe(
          resp =>{
            this.alquileres = this.alquileres.filter(cli => cli !== alquiler)
            swal('Alquiler eliminado', `Alquiler ${alquiler.id} eliminado con éxito`,'success');
          }
        )
      }
    });

  }

}
