import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Alquiler } from '../alquiler';
import { AlquilerService } from '../alquiler.service';

@Component({
  selector: 'app-detail-alq',
  templateUrl: './detail-alq.component.html',
  styleUrls: []
})
export class DetailAlqComponent implements OnInit {

  alquiler!:Alquiler;


  constructor( private alquilerService:AlquilerService,
     private activedRoute:ActivatedRoute) { }

  ngOnInit(): void {

    this.activedRoute.paramMap.subscribe(
      params => {
        let id:number = +params.get('id')!;

        if(id){
          this.alquilerService.getAlquiler(id)
          .subscribe( resp => this.alquiler = resp);
        }


      }
    );

  }







}
