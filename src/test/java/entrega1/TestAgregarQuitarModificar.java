package entrega1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

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
	private UsuarioAdministrador admin;
	
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
		mapa.addPOI(paradaDel47);
		mapa.addPOI(cgp);
		mapa.addPOI(banco);
		mapa.addPOI(libreriaEscolar);
		mapa.addPOI(kioskoDeDiarios);
		
		// Administrador
		admin = new UsuarioAdministrador(mapa);
				
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676), comuna8);
		
		// Parada del 114 -- Mozart 2392
		paradaDel114 = new ParadaDeColectivo(new Point(-34.6598, -58.4683), comuna8);
		
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
		kioskoDeDiarios.setNombre("Kiosko de Carlitos");
		kioskoDeDiarios.setDireccion("Albariño 3702");
	}
	
	@Test
	public void TestAdminAgregaParadaDel114(){
			admin.agregarPOI(paradaDel114); // Ahora en la coleccion hay 6 POIs
			Assert.assertEquals(6, mapa.getColeccionDePOIS().size()); 
		}
	
	@Test 
	public void TestAdminQuitaParadaDel47(){
		admin.quitarPOI(paradaDel47);
		Assert.assertEquals(5, mapa.getColeccionDePOIS().size());
	}
	
	@Test 
	public void TestModificarNombreDelKioskoDeDiarios(){
		admin.modificarNombre(kioskoDeDiarios, "Kiosko de Juan");
		Assert.assertEquals("Kiosko de Juan", kioskoDeDiarios.getNombre());
	}
	
	@Test
	public void TestModificarDireccionKioskoDeDiarios(){
		admin.modificarDireccion(kioskoDeDiarios, "Av Escalada 1561");
		Assert.assertEquals("Escalada 1561", kioskoDeDiarios.getDireccion());
	}
	
}
