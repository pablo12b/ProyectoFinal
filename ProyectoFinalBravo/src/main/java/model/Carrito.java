package model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="carrito")
public class Carrito{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_codigo")
    private Cliente cliente;
	@OneToMany(mappedBy = "carrito", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Transient
	List<DetalleFactura> detalles;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<DetalleFactura> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void vaciarCarrito() {
        detalles.clear();
    }
}
