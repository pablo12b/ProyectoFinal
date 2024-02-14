package business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import model.Carrito;
import model.Cliente;
import model.DetalleFactura;
import model.Factura;
import model.Producto;
@Singleton
@Startup
public class GestionDatos {
	@Inject
	private ProductoDAO productoDAO;
	@Resource(lookup = "java:/ProyectoBravoDS")
    private DataSource dataSource;
	@PostConstruct
	public void init() {
		System.out.println("Iniciando.........");
		try (Connection connection = dataSource.getConnection()) {
            // Imprimir un mensaje si la conexión es exitosa
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            // Capturar y mostrar cualquier excepción que ocurra
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
		
		//SUDADERAS
		
		Producto pro = new Producto();
		pro.setCategoria("SUDADERA");
		pro.setNombre("SUDADERA DENIM CUELLO CREMALLERA");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/9794/444/800/2/w/750/9794444800_6_1_1.jpg?ts=1702294241544");
		pro.setPrecio(69.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("SUDADERA");
		pro.setNombre("SUDADERA BOXY FIT CAPUCHA");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/0039/442/922/2/w/750/0039442922_6_1_1.jpg?ts=1704970284609");
		pro.setPrecio(59.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("SUDADERA");
		pro.setNombre("SUDADERA LAVADA CREW NECK");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/0039/443/807/2/w/750/0039443807_6_1_1.jpg?ts=1704970282135");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("SUDADERA");
		pro.setNombre("SUDADERA CAPUCHA");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/0761/350/800/2/w/750/0761350800_6_1_1.jpg?ts=1688110917303");
		pro.setPrecio(47.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("SUDADERA");
		pro.setNombre("SUDADERA DENIM CAPUCHA");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/1538/406/800/2/w/750/1538406800_6_1_1.jpg?ts=1704357327378");
		pro.setPrecio(69.99);
		
		productoDAO.insert(pro);
		
		//CAMISA
		
		pro = new Producto();
		pro.setCategoria("CAMISA");
		pro.setNombre("CAMISA ESTRUCTURA EASY CARE");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/7545/517/800/2/w/750/7545517800_6_1_1.jpg?ts=1690528093013");
		pro.setPrecio(45.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("CAMISA");
		pro.setNombre("CAMISA DENIM");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/6987/350/800/2/w/750/6987350800_6_1_1.jpg?ts=1691075188255");
		pro.setPrecio(45.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("CAMISA");
		pro.setNombre("CAMISA ESTRUCTURA EASY CARE");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/7545/517/800/2/w/750/7545517800_6_1_1.jpg?ts=1690528093013");
		pro.setPrecio(45.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("CAMISA");
		pro.setNombre("CAMISA MEZCLA LYOCELL");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/4125/116/800/2/w/750/4125116800_6_1_1.jpg?ts=1700651904720");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("CAMISA");
		pro.setNombre("CAMISA ACTIVE");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/5445/404/800/2/w/750/5445404800_6_1_1.jpg?ts=1689590762962");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
		
		//PANTALONES
		
		pro = new Producto();
		pro.setCategoria("PANTALÓN");
		pro.setNombre("PANTALÓN CINTURA DOBLE CONTRASTE");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/5445/404/800/2/w/750/5445404800_6_1_1.jpg?ts=1689590762962");
		pro.setPrecio(69.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("PANTALÓN");
		pro.setNombre("PANTALÓN TRAJE SLIM FIT");
		pro.setUrl("https://static.zara.net/photos///2023/I/0/2/p/1564/711/800/2/w/750/1564711800_6_1_1.jpg?ts=1689590613643");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("PANTALÓN");
		pro.setNombre("PANTALÓN SLIM FIT CONFORT");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/6861/441/800/2/w/750/6861441800_6_1_1.jpg?ts=1704788303573");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("PANTALÓN");
		pro.setNombre("PANTALÓN CHINO ALGODÓN");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/1934/330/710/2/w/750/1934330710_6_1_1.jpg?ts=1705319852414");
		pro.setPrecio(45.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("PANTALÓN");
		pro.setNombre("PANTALÓN CINTURA JOGGER CONFORT");
		pro.setUrl("https://static.zara.net/photos///2024/V/0/2/p/5805/444/407/2/w/750/5805444407_6_1_1.jpg?ts=1704378014089");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
		
		//ZAPATOS
		
		pro = new Producto();
		pro.setCategoria("ZAPATOS");
		pro.setNombre("BOTA CORDONES PIEL COMBINADA");
		pro.setUrl("https://static.zara.net/photos///2024/V/1/2/p/2146/220/107/2/w/750/2146220107_2_3_1.jpg?ts=1702312538727");
		pro.setPrecio(99.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("ZAPATOS");
		pro.setNombre("DEPORTIVO BOTÍN");
		pro.setUrl("https://static.zara.net/photos///2024/V/1/2/p/2104/320/800/2/w/750/2104320800_6_2_1.jpg?ts=1705668983142");
		pro.setPrecio(89.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("ZAPATOS");
		pro.setNombre("BOTA VOLUMEN TRACK");
		pro.setUrl("https://static.zara.net/photos///2024/V/1/2/p/2008/320/800/2/w/750/2008320800_6_2_1.jpg?ts=1701246647683");
		pro.setPrecio(89.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("ZAPATOS");
		pro.setNombre("ZAPATILLA DEPORTIVA BANDELETA");
		pro.setUrl("https://static.zara.net/photos///2023/I/1/2/p/2230/220/800/2/w/750/2230220800_6_2_1.jpg?ts=1692877043226");
		pro.setPrecio(79.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("ZAPATOS");
		pro.setNombre("MOCASÍN APLIQUE METÁLICO");
		pro.setUrl("https://static.zara.net/photos///2024/V/1/2/p/2603/320/800/2/w/750/2603320800_6_2_1.jpg?ts=1702381524698");
		pro.setPrecio(89.99);
		
		productoDAO.insert(pro);
		
		pro = new Producto();
		pro.setCategoria("ZAPATOS");
		pro.setNombre("DEPORTIVO VOLUMEN");
		pro.setUrl("https://static.zara.net/photos///2024/V/1/1/p/2200/320/001/2/w/750/2200320001_6_2_1.jpg?ts=1702919685519");
		pro.setPrecio(49.99);
		
		productoDAO.insert(pro);
	}
}
