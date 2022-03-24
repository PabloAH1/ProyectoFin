import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  imagenSrc!:string;
  clientes!: Cliente[];

  constructor(private servicio:ClienteService, public authService:AuthService) { }

  ngOnInit(): void {

    this.imagenSrc = 'assets/avatar.jpg';

    this.servicio.getClientes().subscribe(
      resp => this.clientes =resp
    );
  }

  delete( cliente:Cliente):void{
    swal({
      title:'Está seguro?',
      text:`Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}`,
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
        this.servicio.delete(cliente.id).subscribe(
          resp =>{
            this.clientes = this.clientes.filter(cli => cli !== cliente)
            swal('Cliente eliminado', `Cliente ${cliente.nombre} eliminado con éxito`,'success');
          }
        )
      }
    });

  }

}
