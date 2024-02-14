import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/app/enviroment/environment';
import { Carrito } from 'src/app/domain/carrito';
import { Observable } from 'rxjs';
import { Producto } from 'src/app/domain/producto';
import { Cliente } from 'src/app/domain/cliente';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  constructor(private http: HttpClient) { }

  agregarProductoACarrito(producto: Producto, codigo: number) {
    let url = `${environment.WS_PATH}/carritos/agregar-producto/${codigo}`;
    return this.http.post<any>(url, producto);
  }

  getCarrito(codigo: number){
    let url = `${environment.WS_PATH}/carritos/list/${codigo}`
    return this.http.get<any>(url)
  }

  deleteDetalle(codigo: number){
    let url = `${environment.WS_PATH}/carritos/delete/${codigo}`
    return this.http.delete<any>(url)
  }

}
