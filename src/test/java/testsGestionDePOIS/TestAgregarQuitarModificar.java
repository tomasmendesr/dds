package testsGestionDePOIS;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import gestionDePOIS.Banco;
import gestionDePOIS.CGP;
import gestionDePOIS.Comuna;
import gestionDePOIS.LocalComercial;
import gestionDePOIS.Mapa;
import gestionDePOIS.ParadaDeColectivo;
import gestionDePOIS.Rubro;

public class TestAgregarQuitarModificar {
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private ParadaDeColectivo paradaDel114;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Polygon	zonaComuna8;
	private Mapa mapa;
	
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
		
		// Mapa
		mapa = new Mapa();
		mapa.agregarPOI(paradaDel47);
		mapa.agregarPOI(cgp);
		mapa.agregarPOI(banco);
		mapa.agregarPOI(libreriaEscolar);
		mapa.agregarPOI(kioskoDeDiarios);
		
				
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
		
		// Parada del 114 -- Mozart 2392
		paradaDel114 = new ParadaDeColectivo(new Point(-34.6598, -58.4683));
		
		// CGP -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));	
		cgp.setComuna(comuna8);
		
		// Banco -- Av Riestra 5002
		banco = new Banco(new Point(-34.6719, -58.4695));
		banco.setComuna(comuna8);
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
		libreriaEscolar.setComuna(comuna8);
		
		// Kiosko de Diarios -- Albariño 3702
		Rubro rubroKioskoDeDiarios = new Rubro(200.0);
		kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), rubroKioskoDeDiarios);
		kioskoDeDiarios.setNombre("Kiosko de Carlitos");
		kioskoDeDiarios.setDireccion("Albariño 3702");
		kioskoDeDiarios.setComuna(comuna8);
	}
	
	@Test
	public void TestMapaAgregaParadaDel114(){
			mapa.agregarPOI(paradaDel114); // Ahora en la coleccion hay 6 POIs
			Assert.assertEquals(6, mapa.getColeccionDePOIS().size()); 
		}
	
	@Test 
	public void TestMapaQuitaParadaDel47(){
		Assert.assertTrue(mapa.getColeccionDePOIS().contains(paradaDel47)); // esto pasa porque esta comparando por identidad y no esta comparando por equivalencia
		mapa.quitarPOI(paradaDel47);
		Assert.assertFalse(mapa.getColeccionDePOIS().contains(paradaDel47));
	}
	
/*	@Test 
	public void TestModificarNombreDelKioskoDeDiarios(){
		mapa.modificarNombre(kioskoDeDiarios, "Kiosko de Juan");
		Assert.assertEquals("Kiosko de Juan", kioskoDeDiarios.getNombre());
	}
	
	@Test
	public void TestModificarDireccionKioskoDeDiarios(){
		mapa.modificarDireccion(kioskoDeDiarios, "Av Escalada 1561");
		Assert.assertEquals("Av Escalada 1561", kioskoDeDiarios.getDireccion());
	}
	*/
}
