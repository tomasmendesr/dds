package testEntrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;



import org.junit.Assert;

public class TestDeCercania {
	
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private PolygonAdapter	zonaComuna8;
	private Point ubicacionCercana;
	private Point ubicacionLejana;
	
	@Before
	public void init(){
		// Comuna 8
		comuna8 = new Comuna(new Long(8));
		zonaComuna8 = new PolygonAdapter(new Long(1));
		zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
		zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
		zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
		zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
		zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
		
		// UbicacionCercana en el Mapa - Sayos 4937 
		ubicacionCercana = new Point(-34.6717, -58.4679);
		
		// UbicacionLejana en el mapa - Av. Juan B. Justo 4045
		ubicacionLejana = new Point(-34.6048, -58.4591);
		
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
		
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
		kioskoDeDiarios.setComuna(comuna8);
	}
	
	@Test
	public void testParada47CercanoAMenosDe100Metros(){
		Assert.assertTrue(paradaDel47.estaCercaDe(ubicacionCercana)); 
	}
	
	@Test
	public void testParada47Lejano(){
		Assert.assertFalse(paradaDel47.estaCercaDe(ubicacionLejana)); 
	}

	@Test
	public void testCGPDentroDeLaMismaComuna(){
			Assert.assertTrue(cgp.estaCercaDe(ubicacionCercana)); 
	}
	
	@Test
	public void testCGPLejano(){
			Assert.assertFalse(cgp.estaCercaDe(ubicacionLejana)); 
	}

	@Test 
	public void testBancoCercanoAMenosDe500Metros(){
		Assert.assertTrue(banco.estaCercaDe(ubicacionCercana)); 
	}
	
	@Test 
	public void testBancoLejano(){
		Assert.assertFalse(banco.estaCercaDe(ubicacionLejana));  
	}
	
	@Test 
	public void testLibreriaDentroDelRadio(){
		Assert.assertTrue(libreriaEscolar.estaCercaDe(ubicacionCercana));
	}
	
	@Test
	public void testLibreriaFueraDelRadio(){
		Assert.assertFalse(libreriaEscolar.estaCercaDe(ubicacionLejana));
	}
	
	@Test
	public void testKioskoDeDiariosDentroDelRadio(){
		Assert.assertTrue(kioskoDeDiarios.estaCercaDe(ubicacionCercana));
	}
	
	@Test
	public void testKioskoDeDiariosLejano(){
		Assert.assertFalse(kioskoDeDiarios.estaCercaDe(ubicacionLejana));
	}
	
}
