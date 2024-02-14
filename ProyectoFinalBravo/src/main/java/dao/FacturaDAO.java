package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Cliente;
import model.DetalleFactura;
import model.Factura;
@Stateless
public class FacturaDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Factura factura) {
		em.persist(factura);
	}
	
	public void update(Factura factura) {
		em.merge(factura);
	}
	
	public void remove(int codigo) {
		Factura factura = em.find(Factura.class, codigo);
		em.refresh(factura);
	}
	
	public Factura read(int codigo) {
		Factura factura = em.find(Factura.class, codigo);
		return factura;
	}
	
	public List<Factura> getAll(){
		String jpql = "SELECT c FROM Factura c";
		Query q = em.createQuery(jpql, Factura.class);
		return q.getResultList();
	}
	
	public List<DetalleFactura> getDetalles(){
		String jpql = "SELECT c FROM DetalleFactura c";
		Query q = em.createQuery(jpql,DetalleFactura.class);
		return q.getResultList();
	}
	
	public Cliente getCliente(String dni) {
		String jpql = "SELECT c FROM Factura c WHERE dni="+dni;
		Query q = em.createQuery(jpql, Factura.class);
		q.setParameter("dni", dni);
		try {
			return (Cliente) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Cliente no encontrado en factura con dni");
			return null;
		}
	}
	
}
