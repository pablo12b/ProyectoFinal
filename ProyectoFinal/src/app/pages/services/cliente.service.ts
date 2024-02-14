import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/app/enviroment/environment';
import { Cliente } from 'src/app/domain/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  saveClientes(cliente: Cliente){
    let url = `${environment.WS_PATH}/clientes/`;
    return this.http.post<any>(url,cliente);
  }

  getVerificarClientes(usuario: string, contras: string){
    let url = `${environment.WS_PATH}/clientes/login?usuario=${encodeURIComponent(usuario)}&contras=${encodeURIComponent(contras)}`;
    return this.http.get<any>(url);
  }

  getCodigoCliente(usuario: string){
    let url = `${environment.WS_PATH}/clientes/codigo?usuario=${encodeURIComponent(usuario)}`;
    return this.http.get<number>(url);
  }
}
