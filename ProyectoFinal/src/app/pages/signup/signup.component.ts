import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClienteService } from '../services/cliente.service';
import { Router } from '@angular/router'; // Importa Router
import { Cliente } from 'src/app/domain/cliente';
import { AuthService } from '../services/auth.service';
import { CarritoService } from '../services/carrito.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signUpForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private clienteService: ClienteService, private router: Router, private authService: AuthService, private carritoService: CarritoService) {
    this.signUpForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      dni: ['', Validators.required],
      direccion: ['', Validators.required],
      usuario: ['', Validators.required],
      contras: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.signUpForm.valid) {
      const cliente: Cliente = this.signUpForm.value;
      this.clienteService.saveClientes(cliente).subscribe(
        response => {
          console.log('Registro exitoso', response);
          this.authService.setUsuarioAutenticado(response);
          // Aquí puedes manejar la respuesta del servidor, como redirigir al usuario o mostrar un mensaje de éxito
          this.router.navigate(['/paginas/login']);
        },
        error => {
          console.error('Error al registrar el cliente', error);
          // Aquí puedes manejar errores, como mostrar un mensaje al usuario
        }
      );
    }
  }
}
