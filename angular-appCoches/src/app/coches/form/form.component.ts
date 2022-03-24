import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert2';
import { Coche } from '../coche';
import { CocheService } from '../coche.service';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styles: [
  ]
})
export class FormComponent implements OnInit {

  titulo:string ="Nuevo Coche";

  coche: Coche = new Coche();



  constructor( private cocheService:CocheService,
    private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {



      this.activatedRoute.paramMap.subscribe(
        params =>{
          let id = +params.get('id')!;
          if(id){
            this.cocheService.getCoche(id).subscribe(
              (resp) => this.coche = resp
            )
          }
        }
      );
  }




  create():void{
    console.log("formulario enviado");
    console.log(this.coche);
    this.cocheService.create(this.coche).subscribe(
      resp => {
        swal('Nuevo Coche',`${this.coche.marca} creado con éxito`,'success');
        this.router.navigate(['/coches']);
      },
      err=>{
        console.log('Codigo de error backend',err.status);
      }
    );
  }

  update():void{
    console.log(this.coche);
    this.cocheService.update(this.coche).subscribe(
      resp=>{
        this.router.navigate(['/coches']);
        swal('Coche Actualizado',`${this.coche.marca}`,'success');
      },
      err=>{
        console.error('Codigo del error desde el backend'+err.status);
        console.error(err.error.errros)
      }
    );
  }

}
