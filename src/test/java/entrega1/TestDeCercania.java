package entrega1;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Assert;

public class TestDeCercania {
	
	@Test
	public void testDeCercaniaDel47SiEstoyAMediaCuadra(){
	// Parada del 47
		//Instacio su ubicacion
			Point pointDel47 = new Point(-34.6706, -58.4688); // Corvalan 6536
		//Instancio la comuna a la que pertenece
			Polygon comuna8 = new Polygon();	// Comuna 8
			comuna8.add(new Point(-34.6744,-58.5025));
			comuna8.add(new Point(-34.6578,-58.4787));
			comuna8.add(new Point(-34.6648,-58.4697));
			comuna8.add(new Point(-34.6621,-58.4240));
			comuna8.add(new Point(-34.7048,-58.4612));
		ParadaDeColectivo paradaDel47 = new ParadaDeColectivo(pointDel47, comuna8);
		Assert.assertTrue(paradaDel47.estaCerca(new Point(-34.6701,-58.4694))); // Punto a menos de 100 metros
		// Assert.assertTrue(paradaDel47.estaCerca(new Point(-35.1254, 14.5671))); // Punto lejano
	}
	
	@Test
	public void testDeSiElCGPEstaDentroDeLaComunaDondeEstoyTieneQueDarTrue(){
		// CGP
			//Instancio su ubicacion
				Point pointDelCGP = new Point(-34.6706, -58.4688); // Corvalan 6536
			//Instancio la comuna a la que pertenece
				Polygon comuna8 = new Polygon();	// Comuna 8
				comuna8.add(new Point(-34.6744,-58.5025));
				comuna8.add(new Point(-34.6578,-58.4787));
				comuna8.add(new Point(-34.6648,-58.4697));
				comuna8.add(new Point(-34.6621,-58.4240));
				comuna8.add(new Point(-34.7048,-58.4612));
			CGP cGP = new CGP(pointDelCGP, comuna8);
			Assert.assertTrue(cGP.estaCerca(new Point(-34.6775,-58.4641))); // Punto dentro de la comuna
			// Assert.assertTrue(cGP.estaCerca(new Point(-3.4214,0.0134))); // Punto fuera de la comuna
	}
	
}
