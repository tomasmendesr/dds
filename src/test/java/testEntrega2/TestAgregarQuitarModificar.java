package testEntrega2;



import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Master.Identity;
import Master.RepositorioPOIs;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;


public class TestAgregarQuitarModificar {
	
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private ParadaDeColectivo paradaDel114;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Polygon	zonaComuna8;
	
	@Before
	public void init(){
	
		Identity.setIdentity(0);
		
		// Comuna 8
		comuna8 = new Comuna(8);
		zonaComuna8 = new Polygon();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
				
				
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
		paradaDel47.setNombre("Parada del 47");
		
		
		
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
		
		// Parada del 114 -- No se la dir
		paradaDel114 = new ParadaDeColectivo(new Point(1,1));
		
		//Agrego Pois al repo
		RepositorioPOIs.getInstance().agregarPOI(paradaDel47);
		RepositorioPOIs.getInstance().agregarPOI(kioskoDeDiarios);
		RepositorioPOIs.getInstance().agregarPOI(cgp);
		RepositorioPOIs.getInstance().agregarPOI(banco);
		RepositorioPOIs.getInstance().agregarPOI(libreriaEscolar);
		
		
	}
		
	@Test
	public void TestRepositorioPOIsAgregaParadaDel114(){

		RepositorioPOIs.getInstance().agregarPOI(paradaDel114); // Ahora en la coleccion hay 6 POIs
			Assert.assertTrue(RepositorioPOIs.getInstance().getColeccionDePOIS().contains(paradaDel114)); 
	}
	
	@Test
	public void elRepoContieneLos5POIsDelBefore(){
		Assert.assertEquals(5, RepositorioPOIs.getInstance().getColeccionDePOIS().size());
	}
	
	@Test 
	public void TestRepositorioPOIsQuitaParadaDel47(){
		RepositorioPOIs.getInstance().quitarPOI(paradaDel47);
		Assert.assertEquals(4, RepositorioPOIs.getInstance().getColeccionDePOIS().size()); 
	}
	
	@After
	public void tearDown(){
		RepositorioPOIs.resetPOIs();
	}
	
}
