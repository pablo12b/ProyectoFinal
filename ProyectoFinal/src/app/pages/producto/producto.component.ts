import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../services/productos.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit{
  productos: any

  constructor(private productosService: ProductosService){}

  ngOnInit(): void {
    this.productos = this.productosService.getProductos()
  }
}
