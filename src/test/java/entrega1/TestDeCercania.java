package entrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Assert;

public class TestDeCercania {
	
	private Polygon comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private Maquina maquinaCercana;
	private Maquina maquinaLejana;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	
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
		
		// MaquinaCercana -- Sayos 4937
		// Esta a media cuadra de la parada del 47 y tambien esta a menos de 500 metros del Banco
		maquinaCercana = new Maquina(new Point(-34.6718,-58.46805));
		
		//MaquinaLejana -- Av Carabobo 27 
		// Se encuentra lejana a todos los puntos de interes instanciados
		maquinaLejana = new Maquina(new Point(-34.6273, -58.4564));
		
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
	public void testParada47CercanaAMenosDe100Metros(){
		Assert.assertTrue(maquinaCercana.estaCercaDe(paradaDel47)); 
	}
	
	@Test
	public void testParada47Lejana(){
		Assert.assertTrue(!maquinaLejana.estaCercaDe(paradaDel47)); 
	}

	@Test
	public void testCGPDentroDeLaMismaComuna(){
			Assert.assertTrue(maquinaCercana.estaCercaDe(cgp)); 
	}
	@Test
	public void testCGPLejano(){
			Assert.assertTrue(!maquinaLejana.estaCercaDe(cgp)); 
	}

	@Test 
	public void testBancoCercanoAMenosDe500Metros(){
		Assert.assertTrue(maquinaCercana.estaCercaDe(banco)); 
	}
	
	@Test 
	public void testBancoLejano(){
		Assert.assertTrue(!maquinaLejana.estaCercaDe(banco));  
	}
	
	@Test 
	public void testLibreriaDentroDelRadio(){
		Assert.assertTrue(maquinaCercana.estaCercaDe(libreriaEscolar));
	}
	
	@Test
	public void testLibreriaFueraDelRadio(){
		Assert.assertTrue(!maquinaLejana.estaCercaDe(libreriaEscolar));
	}
	
	@Test
	public void testKioskoDeDiariosDentroDelRadio(){
		Assert.assertTrue(maquinaCercana.estaCercaDe(kioskoDeDiarios));
	}
	
	@Test
	public void testKioskoDeDiariosLejano(){
		Assert.assertTrue(!maquinaLejana.estaCercaDe(kioskoDeDiarios));
	}
	
}
