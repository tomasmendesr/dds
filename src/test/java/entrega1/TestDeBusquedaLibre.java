package entrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Assert;
import java.util.ArrayList;

public class TestDeBusquedaLibre {

	private Polygon comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private Dispositivo dispositivo;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private ArrayList<POI> lista;
	private int size;
	private String texto;
	
	@Before
	public void init(){
		// Comuna 8
		comuna8 = new Polygon();
		comuna8.add(new Point(-34.6744,-58.5025));
		comuna8.add(new Point(-34.6578,-58.4787));
		comuna8.add(new Point(-34.6648,-58.4697));
		comuna8.add(new Point(-34.6621,-58.4240));
		comuna8.add(new Point(-34.7048,-58.4612));
		
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676), comuna8);
		paradaDel47.addTag("47");
		
		// DispositivoCercana -- Sayos 4937
		dispositivo = new Dispositivo(new Point(-34.6718,-58.46805));
		
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669), comuna8);
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
		
		dispositivo.addPOI(paradaDel47);
		dispositivo.addPOI(cgp);
		dispositivo.addPOI(banco);
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconocePOIsConTag47(){
		lista = dispositivo.buscarPorTextoLibre("47");
		size = 2;
		Assert.assertEquals(size, lista.size());
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconocePOIsConTagAsesoramiento(){
		lista = dispositivo.buscarPorTextoLibre("asesoramiento");
		size = 1;
		Assert.assertEquals(size, lista.size());
		}
	
	@Test
	public void testLaBusquedaDeTextoLibreGuardaEfectivamentePOIs(){
		lista = dispositivo.buscarPorTextoLibre("asesoramiento");
		texto = lista.get(0).getNombre();
		Assert.assertEquals("Asesoria", texto);
	}
	
	/*@Test
	public void testLaBusquedaDeTextoLibreReconoceElTag47(){
		Assert.assertTrue(dispositivo.textoLibre(paradaDel47, "47")); 
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreNoReconoceElTag5(){
		Assert.assertFalse(dispositivo.textoLibre(paradaDel47, "5"));
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconoceElTagLibreria(){
		Assert.assertTrue(dispositivo.textoLibre(libreriaEscolar, "libreria"));
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreNoReconoceElTagPlaza(){
		Assert.assertFalse(dispositivo.textoLibre(libreriaEscolar, "plaza"));
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreReconoceElTagAsesoramiento(){
		Assert.assertTrue(dispositivo.textoLibre(cgp, "asesoramiento"));
	}
	
	@Test
	public void testLaBusquedaDeTextoLibreNoReconoceElTagAbogado(){
		Assert.assertFalse(dispositivo.textoLibre(cgp, "abogado"));
	}
	
	@Test
	public void testElBancoReconoceElTagDeposito(){
		Assert.assertTrue(dispositivo.textoLibre(banco, "deposito"));
	}
	
	@Test
	public void testElBancoNoReconoceElTagExtraccion(){
		Assert.assertFalse(dispositivo.textoLibre(banco, "extraccion"));
	}
	
	@Test
	public void testElKioskoReconoceElTagCaramelos(){
		Assert.assertTrue(dispositivo.textoLibre(kioskoDeDiarios, "caramelos"));
	}
	
	@Test
	public void testElKioskoNoReconoceElTagGolosina(){
		Assert.assertFalse(dispositivo.textoLibre(kioskoDeDiarios, "golosinas"));
	}*/
}