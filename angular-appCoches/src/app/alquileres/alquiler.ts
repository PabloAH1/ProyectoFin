import { Cliente } from "../clientes/cliente";
import { Coche } from "../coches/coche";

export class Alquiler{
  id!:number;
  fechaIni!:Date;
  fechaFin!:Date;
  coche!:Coche;
  cliente!:Cliente;
}
