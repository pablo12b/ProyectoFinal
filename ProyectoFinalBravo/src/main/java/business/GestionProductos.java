package business;

import java.util.List;

import dao.ProductoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import model.Producto;

@Stateless
public class GestionProductos {
	@Inject
	private ProductoDAO daoProducto;

	public void guardarProductos(Producto producto) {
		Producto pro = daoProducto.read(producto.getCodigo());
		if (pro != null){
			daoProducto.update(producto);
		}else {
			daoProducto.insert(producto);
		}
	}
	
	public void actualizarProducto(Producto producto) throws Exception {
		Producto pro = daoProducto.read(producto.getCodigo());
		if (pro != null){
			daoProducto.update(producto);
		}else {
			throw new Exception("Cliente no existe");
		}
	}
	
	public void borrarProducto(int codigo){
		daoProducto.remove(codigo);
	}
	
	public List<Producto> getProductos(){
		return daoProducto.getAll();
	}
	
	public List<Producto> getProductoCodigo(List<Integer> codigo){
		return daoProducto.getProductoCodigo(codigo);
	}
	
}