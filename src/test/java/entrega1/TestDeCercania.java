package entrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Assert;

public class TestDeCercania {
	
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
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
		
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676), comuna8);
		
		// CGP -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669), comuna8);	
		
		// Banco -- Av Riestra 5002
		banco = new Banco(new Point(-34.6719, -58.4695), comuna8);
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), comuna8, rubroLibreriaEscolar);
		
		// Kiosko de Diarios -- Albariño 3702
		Rubro rubroKioskoDeDiarios = new Rubro(200.0);
		kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), comuna8, rubroKioskoDeDiarios);
	}
	
	@Test
	public void testParada47CercanoAMenosDe100Metros(){
		Assert.assertTrue(dispositivoCercano.estaCercaDe(paradaDel47)); 
	}
	
	@Test
	public void testParada47Lejano(){
		Assert.assertFalse(dispositivoLejano.estaCercaDe(paradaDel47)); 
	}

	@Test
	public void testCGPDentroDeLaMismaComuna(){
			Assert.assertTrue(dispositivoCercano.estaCercaDe(cgp)); 
	}
	@Test
	public void testCGPLejano(){
			Assert.assertFalse(dispositivoLejano.estaCercaDe(cgp)); 
	}

	@Test 
	public void testBancoCercanoAMenosDe500Metros(){
		Assert.assertTrue(dispositivoCercano.estaCercaDe(banco)); 
	}
	
	@Test 
	public void testBancoLejano(){
		Assert.assertFalse(dispositivoLejano.estaCercaDe(banco));  
	}
	
	@Test 
	public void testLibreriaDentroDelRadio(){
		Assert.assertTrue(dispositivoCercano.estaCercaDe(libreriaEscolar));
	}
	
	@Test
	public void testLibreriaFueraDelRadio(){
		Assert.assertFalse(dispositivoLejano.estaCercaDe(libreriaEscolar));
	}
	
	@Test
	public void testKioskoDeDiariosDentroDelRadio(){
		Assert.assertTrue(dispositivoCercano.estaCercaDe(kioskoDeDiarios));
	}
	
	@Test
	public void testKioskoDeDiariosLejano(){
		Assert.assertFalse(dispositivoLejano.estaCercaDe(kioskoDeDiarios));
	}
	
}
