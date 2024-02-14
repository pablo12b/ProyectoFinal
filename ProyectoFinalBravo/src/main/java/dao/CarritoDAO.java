package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.Carrito;
import model.Cliente;
import model.Producto;

@Stateless
public class CarritoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carrito carrito) {
		em.persist(carrito);
	}
	
	public void update(Carrito carrito) {
		em.merge(carrito);
	}
	
	public void remove(int codigo) {
		Carrito carrito = em.find(Carrito.class, codigo);
		em.remove(carrito);
	}
	
	public Carrito read(int codigo) {
		Carrito carrito = em.find(Carrito.class, codigo);
		return carrito;
	}
	
	public List<Carrito> getAll(){
		String jpql = "SELECT c FROM Carrito c";
		Query q = em.createQuery(jpql, Carrito.class);
		return q.getResultList();
	}
	
	public List<Carrito> obtenerCarritoPersona(int codigo){
		String jpql = "SELECT c FROM Carrito c WHERE c.cliente.codigo = :codigo";
	    TypedQuery<Carrito> query = em.createQuery(jpql, Carrito.class);
	    query.setParameter("codigo", codigo);
	    try {
	        return query.getResultList();
	    } catch (NoResultException e) {
	    	System.out.println("es nulo");
	        return null; // Manejar el caso en que no se encuentre ningún carrito
	    }
	}
	
	public Carrito obtenerCarritoPorCodigoPersona(int codigo) {
	    String jpql = "SELECT c FROM Carrito c WHERE c.cliente.codigo = :codigo";
	    TypedQuery<Carrito> query = em.createQuery(jpql, Carrito.class);
	    query.setParameter("codigo", codigo);
	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	    	System.out.println("es nulo");
	        return null; // Manejar el caso en que no se encuentre ningún carrito
	    } catch (NonUniqueResultException e) {
	        // Manejar el caso en que hay múltiples carritos asociados a la persona
	        // Dependiendo de la lógica de tu aplicación, podrías devolver el primero, lanzar una excepción, etc.
	        return query.getResultList().get(0);
	    }
	}
	
	public Cliente obtenerCliente(int codigo) {
	    String jpql = "SELECT c FROM Cliente c WHERE c.codigo = :codigo";
	    TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
	    query.setParameter("codigo", codigo);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        System.out.println("NO ESTA ESE CORREO");
	        return query.getSingleResult();
	    }
	}

}