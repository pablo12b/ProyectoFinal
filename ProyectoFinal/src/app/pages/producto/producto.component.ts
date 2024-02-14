import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../services/productos.service';
import { Producto } from 'src/app/domain/producto';
import { AuthService } from '../services/auth.service';
import { CarritoService } from '../services/carrito.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit{
  productos: any[]=[];
  productosSeleccionados: Producto[] = [];
  productosSeleccionado: any;
  paginaActual: number = 1;
  productosPorPagina: number = 20; // 5 columnas x 4 filas

  constructor(private productosService: ProductosService, private authService: AuthService, private carritosService: CarritoService){}

  ngOnInit(): void {
    this.productosService.getProductos().subscribe(
      (productos) => {
        // Hacer una copia del array de productos para no mutar el original
        this.productos = [...productos];
      },
      (error) => {
        console.error('Error al obtener productos', error);
      }
    );
  }

  personaAutenticada = this.authService.getUsuarioAutenticado().correo
  codigoAutenticada = this.authService.getUsuarioAutenticado().codigo

  ordenar(tipo: string) {
    let productosOrdenados = [...this.productos];
    if (tipo === 'precioAsc') {
      productosOrdenados.sort((a, b) => (a.precio ?? 0) - (b.precio ?? 0));
    } else if (tipo === 'precioDesc') {
      productosOrdenados.sort((a, b) => (b.precio ?? 0) - (a.precio ?? 0));
    } else if (tipo === 'alfabetico') {
      productosOrdenados.sort((a, b) => (a.nombre ?? "").localeCompare(b.nombre ?? ""));
    }
    this.productos = productosOrdenados;
  }

  totalPaginas(): number {
    return Math.ceil(this.productos.length / this.productosPorPagina);
  }

  siguientePagina(): void {
    if (this.paginaActual < this.totalPaginas()) {
      this.paginaActual++;
    }
  }

  anteriorPagina(): void {
    if (this.paginaActual > 1) {
      this.paginaActual--;
    }
  }

  irAPagina(num: number): void {
    this.paginaActual = num;
  }
  
  
  // Función para crear un array de números de páginas para el *ngFor del paginador
  obtenerNumerosDePagina(): number[] {
    return Array(this.totalPaginas()).fill(0).map((x, i) => i + 1);
  }
  
  agregarAlCarrito(pro: Producto): void {
    console.log("codigo:"+this.codigoAutenticada);
    if (this.codigoAutenticada !== null) {
      this.carritosService.agregarProductoACarrito(pro, this.codigoAutenticada).subscribe(
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
    } else {
      console.error('this.codigoAutenticada es null, no se puede llamar a agregarProductoACarrito');
      // Puedes manejar este caso de error de acuerdo a tus necesidades
    }
    
  }

}
