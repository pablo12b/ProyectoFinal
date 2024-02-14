package business;

import java.util.List;

import dao.ClienteDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import model.Cliente;

@Stateless
public class GestionClientes {
	@Inject
	private ClienteDAO clienteDAO;
	
	public void guardarClientes(Cliente cliente) {
		Cliente cli = clienteDAO.read(cliente.getCodigo());
		if(cli != null) {
			clienteDAO.update(cliente);
		}else {
			clienteDAO.insert(cliente);
		}
	}
	
	public void actualizarCliente(Cliente cliente) throws Exception {
		Cliente cli = clienteDAO.read(cliente.getCodigo());
		if(cli != null) {
			clienteDAO.update(cliente);
		}else {
			throw new Exception("El cliente no existe");
		}
	}
	
	public void borrarCliente(int codigo) {
		clienteDAO.remove(codigo);
	}
	
	public List<Cliente> getClientes(){
		return clienteDAO.getAll();
	}
	
	public Cliente verificarUsuario(String usuario, String contras) {
		Cliente usua = clienteDAO.verificarUsuario(usuario);
		Cliente cont = clienteDAO.verificarContras(contras);
		if(usua == cont) {
			return usua;
		}else {
			return null;
		}
	}
	
	
	public Cliente obtenerPorUsuario(String  usuario) {
		return clienteDAO.obtenerPorUsuario(usuario);
	}
}
