import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/app/enviroment/environment';
import { Producto } from 'src/app/domain/producto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  constructor(private http: HttpClient) { }

  getProductos(){
    let url = `${environment.WS_PATH}/productos/list/`;
    return this.http.get<Producto[]>(url);
  }

}
