
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';

@Component({
  selector: 'app-detail-cli',
  templateUrl: './detail-cli.component.html',
  styleUrls: []
})
export class DetailCliComponent implements OnInit {

  cliente!:Cliente;

  constructor( private clienteService:ClienteService,
     private activedRoute:ActivatedRoute) { }

  ngOnInit(): void {

    this.activedRoute.paramMap.subscribe(
      params => {
        let id:number = +params.get('id')!;

        if(id){
          this.clienteService.getCliente(id)
          .subscribe( resp => this.cliente = resp);
        }

      }
    );

  }






}
