package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="facturas")
public class Factura{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="codigo")
	private int codigo;
	@Column(name="numero")
	private String numero;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id") // Nombre de la columna en la tabla Factura que referencia a Cliente
    private Cliente cliente;
	@Column(name="total")
	private double total;
	@Column(name="fecha")
	private Date fechaEmision;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<DetalleFactura> detalles;
	private Random random = new Random();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}
	
	public void addDetalle(DetalleFactura detalle) {
		if(detalles == null) {
			detalles = new ArrayList<DetalleFactura>();
			
		}
		detalles.add(detalle);
	}

	@Override
	public String toString() {
		return "Factura [codigo=" + codigo + ", numero=" + numero + ", cliente=" + cliente + ", total=" + total
				+ ", fechaEmision=" + fechaEmision + ", detalles=" + detalles + "]";
	}
	public String generarNumeroFactura() {
        // Generar cada parte del número de factura de manera aleatoria
        int parte1 = random.nextInt(10000); // Número entre 0 y 9999
        int parte2 = random.nextInt(1000);  // Número entre 0 y 999
        int parte3 = random.nextInt(100000); // Número entre 0 y 99999

        // Formatear el número de factura
        return String.format("%04d-%03d-%05d", parte1, parte2, parte3);
    }
	
}
