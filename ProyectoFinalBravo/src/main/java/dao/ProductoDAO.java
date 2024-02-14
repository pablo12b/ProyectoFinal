package dao;

import java.util.Collections;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Producto;

@Stateless
public class ProductoDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Producto producto) {
		em.persist(producto);
	}
	
	public void update(Producto producto) {
		em.merge(producto);
	}
	
	public void remove(int codigo) {
		Producto cliente = em.find(Producto.class, codigo);
		em.remove(cliente);
	}
	
	public Producto read(int codigo) {
		Producto cliente = em.find(Producto.class, codigo);
		return cliente;
	}
	
	public List<Producto> getAll(){
		String jpql = "SELECT p FROM Producto p";
		Query q = em.createQuery(jpql, Producto.class);
		return q.getResultList();
	}
	
	public List<Producto> getProductoCodigo(List<Integer> codigos) {
	    if (codigos == null || codigos.isEmpty()) {
	        return Collections.emptyList(); // Retorna una lista vacía si no hay códigos
	    }
	    String jpql = "SELECT p FROM Producto p WHERE p.codigo IN :codigos";
	    Query query = em.createQuery(jpql, Producto.class);
	    query.setParameter("codigos", codigos);
	    return query.getResultList();
	}
}
