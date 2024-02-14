package services;

import java.util.ArrayList;
import java.util.List;

import business.GestionCarrito;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Carrito;
import model.CarritoProducto;
import model.Cliente;
import model.DetalleFactura;
import model.Producto;

@Path("carritos")
public class CarritoServices {
	@Inject
	private GestionCarrito gestionCarritos;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list/{codigo}")
	public Response getClientes(@PathParam("codigo") int codigo){
		List <Carrito> lista = gestionCarritos.obtenerCarritoPersona(codigo);
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran carritos");
		return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("agregar-producto/{codigo}")
	public Response agregarProductoACarrito(Producto producto, @PathParam("codigo") int codigo) {
	    try {
	        Cliente cliente = gestionCarritos.obtenerCliente(codigo);
	        Carrito carrito = gestionCarritos.obtenerCarritoPorCodigoPersona(codigo);
	        if (carrito == null) {
	            // Si el carrito no existe, crea uno nuevo y asócialo al cliente.
	            carrito = new Carrito();
	            carrito.setCliente(cliente);
	            gestionCarritos.guardarCarrito(carrito);
	        }
	        // Agregar el producto al carrito
	        gestionCarritos.agregarProductoACarrito(producto, codigo);
	        return Response.ok(producto).build();
	    } catch (Exception e) {
	        ErrorMessage eS = new ErrorMessage(63, "Error al agregar el producto al carrito: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(eS).build();
	    }
	}
	
}
