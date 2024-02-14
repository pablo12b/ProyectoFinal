package services;

import java.util.List;

import business.GestionDetalle;
import business.GestionFacturas;
import jakarta.inject.Inject;
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
import model.Cliente;
import model.DetalleFactura;
import model.Factura;
import model.Producto;

@Path("detalles")
public class DetalleFacturaServices {
	@Inject
	private GestionDetalle gestionDetalle;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(DetalleFactura detalle) {
		try {
			gestionDetalle.guardarDetalle(detalle);
			return Response.ok(detalle).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(em).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(DetalleFactura detalle) {
		try {
			gestionDetalle.actualizarDetalle(detalle);
			return Response.ok(detalle).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(em).build();
		}
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id")int codigo) {
		try {
			gestionDetalle.borrarDetalle(codigo);
			return "OK";
		} catch (Exception e) {
			// TODO: handle exception
			return "ERROR";
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getDetalles(){
		List <DetalleFactura> lista = gestionDetalle.getDetalles();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran detalles");
		return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listcar/{codigo}")
	public Response agregarProductoACarrito(@PathParam("codigo") int codigo) {
		List <DetalleFactura> lista = gestionDetalle.obtenerCliente(codigo);
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran detalles");
		return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	}
	
	@POST
	@Path("factura")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearFacturas(int codigo) {
		try {
			Factura factura = gestionDetalle.crearFacturas(codigo);
			return Response.ok(codigo).build();
		} catch (Exception e) {
	        ErrorMessage eS = new ErrorMessage(63, "Error al agregar el producto al carrito: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(eS).build();
	    }
	}
}
