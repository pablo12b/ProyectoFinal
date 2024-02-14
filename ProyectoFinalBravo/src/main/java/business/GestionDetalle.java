package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DetalleDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import model.Carrito;
import model.Cliente;
import model.DetalleFactura;
import model.Factura;
import model.Producto;

@Stateless
public class GestionDetalle {
	@Inject
	private DetalleDAO detalleDAO;
	@Inject
	private GestionFacturas gFacturas;
	@Inject
	private GestionCarrito gCarrito;
	
	public void guardarDetalle(DetalleFactura detalle) {
		DetalleFactura det = detalleDAO.read(detalle.getCodigo());
		if(det != null) {
			detalleDAO.update(detalle);
		}else {
			detalleDAO.insert(detalle);
		}
	}
	
	public void actualizarDetalle(DetalleFactura detalle) throws Exception {
		DetalleFactura det = detalleDAO.read(detalle.getCodigo());
		if(det != null) {
			detalleDAO.update(detalle);
		}else {
			throw new Exception("El detalle no existe");
		}
	}
	
	public void borrarDetalle(int codigo) {
		detalleDAO.remove(codigo);
	}
	
	public List<DetalleFactura> getDetalles(){
		return detalleDAO.getAll();
	}
	
	public List<DetalleFactura> obtenerCliente(int codigo) {
		Cliente cliente = detalleDAO.obtenerCliente(codigo);
		Carrito carrito = detalleDAO.obtenerCarritoPorCliente(cliente.getCodigo());
		return detalleDAO.obtenerDetallePorCarrito(carrito.getCodigo());
	}
	
	public Factura crearFacturas(int codigo){
		Cliente cliente = detalleDAO.obtenerCliente(codigo);
		Carrito carrito = detalleDAO.obtenerCarritoPorCliente(cliente.getCodigo());
		List<DetalleFactura> detalles = detalleDAO.obtenerDetallePorCarrito(carrito.getCodigo());
		Factura factura = new Factura();
		factura.setCliente(cliente);
		factura.setDetalles(detalles);
		factura.setFechaEmision(new Date());
		factura.setNumero(factura.generarNumeroFactura());
		double total = 0;
		for(DetalleFactura detalle: detalles) {
			detalle.setFactura(factura);
			detalle.setProcesado(false);
			detalle.setCarrito(null);
			total = total + detalle.getPrecio();
			detalleDAO.update(detalle);
		}
		List<Factura> facturas = cliente.getFacturas();
        if (facturas == null) {
        	facturas = new ArrayList<Factura>();
        	cliente.setFacturas(facturas);
        }
        facturas.add(factura);
        cliente.setFacturas(facturas);
		gCarrito.removeCarrito(carrito.getCodigo());
		factura.setTotal(total);
		gFacturas.guardarFactura(factura);
		for(DetalleFactura detalle: detalles) {
	        detalleDAO.remove(detalle.getCodigo());
	    }
		return factura;
	}
	
}
