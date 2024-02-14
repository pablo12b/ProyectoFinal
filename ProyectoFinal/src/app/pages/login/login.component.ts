import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; // Importa Router
import { AuthService } from '../services/auth.service';
import { ClienteService } from '../services/cliente.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService, private clienteService: ClienteService) { // Inyecta Router aquí
    this.loginForm = this.formBuilder.group({
      usuario: ['', Validators.required],
      contras: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const usuario = this.loginForm.value.usuario;
      const contras = this.loginForm.value.contras;
      // Realizar la llamada al servicio y suscribirse para manejar la respuesta
      this.clienteService.getVerificarClientes(usuario, contras).subscribe(
        response => {
          console.log('Inicio de sesión exitoso', response);
          this.authService.setUsuarioAutenticado(response)
          this.router.navigate(['/paginas/inicio']); // Redirige al usuario a la página de inicio
        },
        error => {
          console.error('Error al iniciar sesión', error);
          // Manejar aquí el error, por ejemplo, mostrando un mensaje al usuario
        }
      );
    }
  }
  
}
