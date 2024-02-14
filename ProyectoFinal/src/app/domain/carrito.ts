import { DetalleFactura } from "./DetalleFactura";
import { Cliente } from "./cliente";

export class Carrito {
    codigo?: number
    cliente?: Cliente
    detalles?: DetalleFactura[]
}