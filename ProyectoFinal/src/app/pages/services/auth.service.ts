import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Cliente } from 'src/app/domain/cliente';
import { environment } from 'src/app/enviroment/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioAutenticado : any
    constructor(private http: HttpClient) {}

  setUsuarioAutenticado(usuario: any) {
    this.usuarioAutenticado = usuario;
    localStorage.setItem(this.usuarioAutenticado, usuario);
  }

  // Método para obtener el usuario autenticado
  getUsuarioAutenticado() {
    return this.usuarioAutenticado;
  }

  // Método para verificar si el usuario está autenticado
  estaAutenticado(): boolean {
    return !!this.usuarioAutenticado;
  }

  cerrarSesion(){
    return this.usuarioAutenticado =null
  }
}

