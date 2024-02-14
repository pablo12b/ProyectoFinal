import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { CarritoService } from '../services/carrito.service';
import { DetalleFacturaService } from '../services/detalle-factura.service';
import * as jsPDF from 'jspdf';
import { DetalleFactura } from 'src/app/domain/DetalleFactura';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {
  carritos: any// Un array para almacenar los productos
  detalles:any

  constructor(private carritosService: CarritoService, private detallesServices: DetalleFacturaService, private authService: AuthService) { } // Inyecta tu servicio aquí

  personaAutenticada = this.authService.getUsuarioAutenticado().correo
  codigoAutenticada = this.authService.getUsuarioAutenticado().codigo

  ngOnInit(): void {
    this.carritos = this.carritosService.getCarrito(this.codigoAutenticada);
    this.detalles = this.detallesServices.getDetalleCarrito(this.codigoAutenticada);
  }

  realizarCompra(){
    // Asegúrate de que la imagen no se muestra en la impresión
    document.querySelectorAll('.no-print').forEach(el => {
      if (el instanceof HTMLElement) {
        el.style.display = 'none';
      }
    });

    // Encuentra el formulario que deseas imprimir
    const formElement = document.getElementById('frm');

    if (formElement) {
      // Clona el formulario que deseas imprimir
      const formToPrint = formElement.cloneNode(true);

      // Asegúrate de que el clon es un elemento HTML para poder manipularlo
      if (formToPrint instanceof HTMLElement) {
        // Elimina todos los elementos con la clase 'no-print' del clon
        formToPrint.querySelectorAll('.no-print').forEach(element => {
          element.remove();
        });

        // Crea una nueva instancia de jsPDF
        const doc = new jsPDF();

        // Renderiza el clon en el PDF, no el formulario original
        doc.fromHTML(formToPrint, 20, 5, {
          'width': 170  // Asegúrate de establecer un ancho si es necesario
        }, () => {
          // Guarda el PDF
          doc.save('Factura.pdf');
        });

        // Vuelve a mostrar los elementos que habían sido ocultados, si es necesario
        document.querySelectorAll('.no-print').forEach(el => {
          if (el instanceof HTMLElement) {
            el.style.display = '';
          }
        });
      }
      } else {
        console.error('El formulario con id "frm" no se encuentra en el DOM.');
      }
        this.detallesServices.crearFacturas(this.codigoAutenticada).subscribe(
          response => {
            if (typeof response === 'string') {
              // La respuesta es un mensaje de éxito como texto
              console.log(response);
              // Puedes mostrar este mensaje en tu interfaz de usuario si es necesario
            } else {
              // La respuesta es un objeto JSON válido, puedes manejarlo según corresponda
              console.log(response);
            }
          },
          error => {
            console.error(error);
            // Manejar el error según tus necesidades
          }
      );
  }

  eliminarDetalle(cod: number){
    this.carritosService.deleteDetalle(cod).subscribe(
      (result: string) => {
        console.log(result); // Muestra el mensaje de éxito en la consola
        // Aquí puedes realizar acciones adicionales según el mensaje de éxito
      },
      error => {
        console.error(error); // Muestra el error en la consola
        // Manejar el error según tus necesidades
      }
    );
  }

}