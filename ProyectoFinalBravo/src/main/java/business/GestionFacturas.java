package business;

import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import model.Carrito;
import model.Cliente;
import model.DetalleFactura;
import model.Factura;
@Stateless
public class GestionFacturas {
	@Inject
	private FacturaDAO facturaDAO;
	public void guardarFactura(Factura factura) {
		Factura fac = facturaDAO.read(factura.getCodigo());
		if(fac != null) {
			facturaDAO.update(factura);
		}else {
			facturaDAO.insert(factura);
		}
	}
	
	public void actualizarFactura(Factura factura) throws Exception {
		Factura fac = facturaDAO.read(factura.getCodigo());
		if(fac != null) {
			facturaDAO.update(factura);
		}else {
			throw new Exception("La factura no existe");
		}
	}
	
	public void borrarFactura(int codigo) {
		facturaDAO.remove(codigo);
	}
	
	public List<Factura> getFacturas(){
		return facturaDAO.getAll();
	}
	
	public List<DetalleFactura> getDetalles(){
		return facturaDAO.getDetalles();
	}

}
