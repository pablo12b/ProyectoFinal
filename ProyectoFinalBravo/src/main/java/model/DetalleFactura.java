package model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="detalles_factura")
public class DetalleFactura{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	private int cantidad;
	@JoinColumn(name = "precio")
	private double precio;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_producto") // Nombre de la columna en la tabla DetalleFactura que referencia a Producto
    private Producto producto;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="codigo_factura")
	@Transient
	private Factura factura;
	@ManyToOne
	@JoinColumn(name = "carrito_id")
    private Carrito carrito;
	private boolean procesado;
	
	public boolean getProcesado() {
		return procesado;
	}
	public void setProcesado(boolean procesado) {
		this.procesado = procesado;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
	    this.cantidad = cantidad;
	    this.precio = this.producto.getPrecio() * cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	@Override
	public String toString() {
		return "DetalleFactura [codigo=" + codigo + ", cantidad=" + cantidad + ", precio=" + precio + ", producto="
				+ producto + "]";
	}
	
}
