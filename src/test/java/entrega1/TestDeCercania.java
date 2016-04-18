package entrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Assert;

public class TestDeCercania {
	
	private Polygon comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cGP;
	
	@Before
	public void init(){
		// Instancio la comuna 8
		comuna8 = new Polygon();
		comuna8.add(new Point(-34.6744,-58.5025));
		comuna8.add(new Point(-34.6578,-58.4787));
		comuna8.add(new Point(-34.6648,-58.4697));
		comuna8.add(new Point(-34.6621,-58.4240));
		comuna8.add(new Point(-34.7048,-58.4612));
		
		// Instancio la parada del 47
		Point pointDel47 = new Point(-34.6706, -58.4688); // Corvalan 6536
		paradaDel47 = new ParadaDeColectivo(pointDel47, comuna8);
		
		// Instancio un CGP
		Point pointDelCGP = new Point(-34.6706, -58.4688); // Corvalan 6536
		cGP = new CGP(pointDelCGP, comuna8);	
	}
	
	@Test
	public void testDeCercaniaDel47SiEstoyCerca(){
		Assert.assertTrue(paradaDel47.estaCerca(new Point(-34.6701,-58.4694))); // Punto a menos de 100 metros
	}
	
	@Test
	public void testDeCercaniaDel47SiEstoyLejos(){
		Assert.assertTrue(!paradaDel47.estaCerca(new Point(-35.1254, 14.5671))); // Punto lejano
	}

	@Test
	public void testDeSiElCGPEstaDentroDeLaComunaDondeEstoy(){
			Assert.assertTrue(cGP.estaCerca(new Point(-34.6775,-58.4641))); // Punto dentro de la comuna
	}
	@Test
	public void testDeSiElCGPEstaFueraDeLaComunaDondeEstoy(){
			Assert.assertTrue(!cGP.estaCerca(new Point(-3.4214,0.0134))); // Punto fuera de la comuna
	}
	
}
