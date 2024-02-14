package business;

import java.util.ArrayList;
import java.util.List;

import dao.CarritoDAO;
import dao.ClienteDAO;
import dao.DetalleDAO;
import dao.ProductoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Carrito;
import model.Cliente;
import model.DetalleFactura;
import model.Producto;

@Stateless
public class GestionCarrito {
	
	@Inject
	private CarritoDAO carritoDAO;
	@Inject
	private GestionDetalle gDetalle;
	
	public void guardarCarrito(Carrito carrito) {
		Carrito car = carritoDAO.read(carrito.getCodigo());
		if(car != null) {
			carritoDAO.update(carrito);
		}else {
			carritoDAO.insert(carrito);
		}
		
	}
	
	public void removeCarrito(int codigo) {
		carritoDAO.remove(codigo);
	}
	
	public List<Carrito> getAll(){
		return carritoDAO.getAll();
	}
	
	public Cliente obtenerCliente(int codigo) {
		 return carritoDAO.obtenerCliente(codigo);
	}
	
	public Carrito obtenerCarritoPorCodigoPersona(int codigo) {
		return carritoDAO.obtenerCarritoPorCodigoPersona(codigo);
	}
	
	public List<Carrito> obtenerCarritoPersona(int codigo) {
		return carritoDAO.obtenerCarritoPersona(codigo);
	}
	
	public void agregarProductoACarrito(Producto producto, int codigo) {
	    try {
	        // Paso 1: Obtén el carrito asociado al cliente mediante su correo.
	        Carrito carrito = carritoDAO.obtenerCarritoPorCodigoPersona(codigo);
	        Cliente cliente = carritoDAO.obtenerCliente(codigo);
	        System.out.println(carrito.getCliente().getNombre() + "---------------OJO EN ESTA VERGA--------------");
	        if (carrito != null) {
	            // Paso 2: Crea un nuevo detalle.
	            DetalleFactura detalle = new DetalleFactura();
	            List<DetalleFactura> detallespro = gDetalle.getDetalles();
	            for(DetalleFactura detalles : detallespro) {
	            	if(producto.getCodigo() == detalles.getProducto().getCodigo() && detalles.getProcesado() == true && detalles.getCarrito().getCodigo() == carrito.getCodigo()) {
	            		detalle = detalles;
	            	}
	            }
	            // Paso 3: Asigna el producto al detalle.
	            detalle.setProducto(producto);
	            detalle.setProcesado(true);
	            detalle.setCarrito(carrito);
	            detalle.setCantidad(detalle.getCantidad() + 1);
	            System.out.println("precio:"+detalle.getPrecio());
	            gDetalle.guardarDetalle(detalle);
	            // Paso 4: Asigna el cliente al carrito (si aún no está asignado).
	            if (carrito.getCliente() == null) {
	                carrito.setCliente(cliente);
	            }
            	// Paso 5: Agrega el detalle al carrito.
	            List<DetalleFactura> detalles = carrito.getDetalles();
	            if (detalles == null) {
	                detalles = new ArrayList<DetalleFactura>();
	                carrito.setDetalles(detalles);
	            }
	            detalles.add(detalle);
	            carrito.setDetalles(detalles);
	            for (DetalleFactura detalle1 : detalles) {
	                System.out.println("Elemento: " + detalle1);
	            }
	            // Actualiza el carrito en la base de datos
	            carritoDAO.update(carrito);
	            System.out.println("Producto agregado al carrito exitosamente.");
	        } else {
	            System.out.println("No se encuentra el carrito");
	            // Puedes manejar la lógica de negocio aquí, lanzar excepciones, etc.
	        }
	    } catch (Exception e) {
	        System.out.println("Error interno del servidor: " + e.getMessage());
	        e.printStackTrace();
	        // Puedes manejar la excepción según tus necesidades
	    }
	}
	
	public Carrito obtenerCarritoPorPersona(int codigoPersona) {
		return carritoDAO.obtenerCarritoPorCodigoPersona(codigoPersona);
	}
}