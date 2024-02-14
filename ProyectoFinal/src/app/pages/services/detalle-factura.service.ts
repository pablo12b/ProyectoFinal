import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/app/enviroment/environment';
import { DetalleFactura } from 'src/app/domain/DetalleFactura';

@Injectable({
  providedIn: 'root'
})
export class DetalleFacturaService {

  constructor(private http: HttpClient) { }
  
  getDetalleCarrito(codigo: number){
    let url = `${environment.WS_PATH}/detalles/listcar/${codigo}`;
    return this.http.get<any>(url)
  }

  crearFacturas(codigo: number){
    let url = `${environment.WS_PATH}/detalles/factura/`;
    return this.http.post<any>(url, codigo);
  }
}
