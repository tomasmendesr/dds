package entrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Assert;
import java.util.ArrayList;

public class TestDeBusquedaLibre {

	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private Mapa dispositivo;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private ArrayList<POI> poisEncontrados;
	private Polygon	zonaComuna8;
	
	@Before
	public void init(){
		// Comuna 8
		comuna8 = new Comuna();
		zonaComuna8 = new Polygon();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
		
		// Parada del 47 -- Corvalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676), comuna8);
		paradaDel47.setNombre("47");
		paradaDel47.setDireccion("Corvalan 3691");
		
		// DispositivoCercana -- Sayos 4937
		dispositivo = new Mapa(new Point(-34.6718,-58.46805));
		
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669), comuna8);
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
		
		// Banco -- Av Riestra 5002
		banco = new Banco(new Point(-34.6719, -58.4695), comuna8);
		banco.addTag("deposito");
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), comuna8, rubroLibreriaEscolar);
		libreriaEscolar.addTag("libreria");
		
		// Kiosko de Diarios -- Albariño 3702
		Rubro rubroKioskoDeDiarios = new Rubro(200.0);
		kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), comuna8, rubroKioskoDeDiarios);
		kioskoDeDiarios.addTag("caramelos");
		kioskoDeDiarios.setNombre("Kiosko de Carlitos");
		
		dispositivo.addPOI(paradaDel47);
		dispositivo.addPOI(cgp);
		dispositivo.addPOI(banco);
		dispositivo.addPOI(libreriaEscolar);
		dispositivo.addPOI(kioskoDeDiarios);
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconocePOIsConTag47(){
		poisEncontrados = dispositivo.buscarPorTextoLibre("47");
		Assert.assertEquals(2, poisEncontrados.size()); // En la coleccion deben estar paradaDel47 y el cgp
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconocePOIsConTagAsesoramiento(){
		poisEncontrados = dispositivo.buscarPorTextoLibre("asesoramiento");
		Assert.assertEquals(1, poisEncontrados.size()); // En la coleccion debe estar unicamente el cgp
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconocePOIsConTagCarlitos(){
		poisEncontrados = dispositivo.buscarPorTextoLibre("Carlitos");
		Assert.assertEquals(1, poisEncontrados.size()); // En la coleccion debe estar unicamente el kioskoDeDiarios
	}
	
	@Test
	public void testBusquedaDeTextoLibreDevuelveDireccionDelPOIEncontrado(){
		poisEncontrados = dispositivo.buscarPorTextoLibre("asesoramiento");
		String direccionPOI = poisEncontrados.get(0).getDireccion();
		Assert.assertEquals("Av Escalada 3100", direccionPOI);
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreGuardaEfectivamentePOIs(){
		poisEncontrados = dispositivo.buscarPorTextoLibre("asesoramiento");
		String nombrePOI = poisEncontrados.get(0).getNombre();
		Assert.assertEquals("Asesoria", nombrePOI);
	}
	
}