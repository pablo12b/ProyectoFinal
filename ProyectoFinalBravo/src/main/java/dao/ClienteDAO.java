package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Carrito;
import model.Cliente;

@Stateless 
public class ClienteDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Cliente cliente) {
		em.persist(cliente);
	}
	
	public void update(Cliente cliente) {
		em.merge(cliente);
	}
	
	public void remove(int codigo) {
		Cliente cliente = em.find(Cliente.class, codigo);
		em.remove(cliente);
	}
	
	public Cliente read(int codigo) {
		Cliente cliente = em.find(Cliente.class, codigo);
		return cliente;
	}
	
	public List<Cliente> getAll(){
		String jpql = "SELECT c FROM Cliente c";
		Query q = em.createQuery(jpql, Cliente.class);
		return q.getResultList();
	}
	
	public Cliente verificarUsuario(String usuario) {
	    String jpql = "SELECT c FROM Cliente c WHERE c.usuario = :usuario";
	    Query q = em.createQuery(jpql, Cliente.class).setParameter("usuario", usuario);
	    try {
	        Cliente cliente = (Cliente) q.getSingleResult();
	        // Si el cliente se encuentra, devuelve true
	        return cliente;
	    } catch (NoResultException e) {
	        // Si no hay resultado, devuelve false
	        return null;
	    }
	}

	
	public Cliente verificarContras(String contras) {
		String jpql = "SELECT c FROM Cliente c WHERE c.contras = :contras";
	    Query q = em.createQuery(jpql, Cliente.class).setParameter("contras", contras);
	    try {
	        Cliente cliente = (Cliente) q.getSingleResult();
	        // Si el cliente se encuentra, devuelve true
	        return cliente;
	    } catch (NoResultException e) {
	        // Si no hay resultado, devuelve false
	        return null;
	    }
	}
	public Cliente obtenerPorCodigo(int codigo) {
		String jpql = "SELECT p FROM Cliente p WHERE p.codigo = :codigo";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("codigo", codigo);
		List<Cliente> resultados = q.getResultList();
		return resultados.isEmpty() ? null : resultados.get(0);
	}
	
	public Cliente obtenerPorCorreo(String  dni) {
		String jpql = "SELECT p FROM Cliente p WHERE p.dni = :dni";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("dni", dni);
		
		List<Cliente> resultados = q.getResultList();
		return resultados.isEmpty() ? null : resultados.get(0);
	}
	public Cliente obtenerPorUsuario(String  usuario) {
		String jpql = "SELECT c FROM Cliente c WHERE c.usuario = :usuario";
	    Query q = em.createQuery(jpql, Cliente.class).setParameter("usuario", usuario);
		List<Cliente> resultados = q.getResultList();
		return resultados.isEmpty() ? null : resultados.get(0);
	}
}
