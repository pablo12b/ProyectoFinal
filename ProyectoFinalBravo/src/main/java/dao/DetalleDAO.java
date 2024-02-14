package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Carrito;
import model.Cliente;
import model.DetalleFactura;

@Stateless
public class DetalleDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(DetalleFactura detalleFactura) {
		em.persist(detalleFactura);
	}
	
	public void update(DetalleFactura detalleFactura) {
		em.merge(detalleFactura);
	}
	
	public void remove(int codigo) {
		DetalleFactura detalleFactura = em.find(DetalleFactura.class, codigo);
		em.remove(detalleFactura);
	}
	
	public DetalleFactura read(int codigo) {
		DetalleFactura detalleFactura = em.find(DetalleFactura.class, codigo);
		return detalleFactura;
	}
	
	public List<DetalleFactura> getAll(){
		String jpql = "SELECT c FROM DetalleFactura c";
		Query q = em.createQuery(jpql, DetalleFactura.class);
		return q.getResultList();
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
	
	public Carrito obtenerCarritoPorCliente(int codigo) {
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
	
	public List<DetalleFactura> obtenerDetallePorCarrito(int codigo) {
		String jpql = "SELECT c FROM DetalleFactura c WHERE c.carrito.codigo = :codigo AND c.procesado = true";
	    TypedQuery<DetalleFactura> query = em.createQuery(jpql, DetalleFactura.class);
	    query.setParameter("codigo", codigo);
	    return query.getResultList();
	}
}
