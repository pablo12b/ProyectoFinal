package services;

import java.util.List;

import business.GestionClientes;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Cliente;

@Path("clientes")
public class ClienteServices {
	@Inject
	private GestionClientes gestionClientes;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Cliente cliente) {
		try {
			gestionClientes.guardarClientes(cliente);
			return Response.ok(cliente).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(em).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Cliente cliente) {
		try {
			gestionClientes.actualizarCliente(cliente);
			return Response.ok(cliente).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(em).build();
		}
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id")int codigo) {
		try {
			gestionClientes.borrarCliente(codigo);
			return "OK";
		} catch (Exception e) {
			// TODO: handle exception
			return "ERROR";
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List <Cliente> lista = gestionClientes.getClientes();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	}
	
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response verificarCliente(@QueryParam("usuario") String usuario,
	                                 @QueryParam("contras") String contras) {
	    try {
	        Cliente esValido = gestionClientes.verificarUsuario(usuario, contras);
	        if (esValido != null) {
                return Response.ok(esValido).build();
	        } else {
	            // Credenciales inválidas
	            ErrorMessage em = new ErrorMessage(4, "Usuario o contraseña incorrecta");
	            return Response.status(Response.Status.UNAUTHORIZED).entity(em).build();
	        }
	    } catch (Exception e) {
	        ErrorMessage em = new ErrorMessage(4, "No se pudo verificar el usuario");
	        return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	    }
	}
	
	@GET
	@Path("codigo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCodigoCliente(@QueryParam("usuario") String usuario) {
	    try {
            Cliente codigo = gestionClientes.obtenerPorUsuario(usuario);
            // Envía un objeto JSON con el mensaje de acceso concedido
            return Response.ok(codigo.getCodigo()).build();

	    } catch (Exception e) {
	        ErrorMessage em = new ErrorMessage(4, "No se pudo verificar el usuario");
	        return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	    }
	}
}
