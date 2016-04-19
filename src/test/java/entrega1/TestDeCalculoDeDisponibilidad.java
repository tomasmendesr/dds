package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import java.util.ArrayList;

import org.junit.Assert;

public class TestDeCalculoDeDisponibilidad {

	private Polygon 				comuna8;
	private ParadaDeColectivo 		paradaDel47;
	private CGP 					cgp;
	private Banco 					banco;
	private Dispositivo 			dispositivo;
	private LocalComercial 			libreriaEscolar;
	private LocalComercial 			kioskoDeDiarios;	
		
	//Servicios de banco
	private Servicio				depositoEnCuentaCorriente;
	private Servicio				prestamoHipotecario;
	
	
	
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
				
		//dispositivo -- Av Carabobo 27 
		dispositivo = new Dispositivo(new Point(-34.6273, -58.4564));
		
		// CGP -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669), comuna8); //Servicios [tramiteDeDni,licenciaDeConducir]	
		
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
	public void paraDaDeColectivoSiempreDisponible(){
		Assert.assertTrue(dispositivo.estaDisponible(paradaDel47, null, null));
	}
	
	@Test 
	public void CGPDisponibleServicioTramiteDeDNIDiaYHoraValida(){
		Tiempo diaYHoraValida = new Tiempo(2,14.0);
		Assert.assertTrue(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaYHoraValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaValidoHoraNoValida(){
		Tiempo diaValidoHoraNoValida = new Tiempo(1,22.0);
		Assert.assertFalse(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaValidoHoraNoValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaNoValidoHoraValida(){
		Tiempo diaNoValidoHoraValida = new Tiempo(7,14.0);
		Assert.assertFalse(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaNoValidoHoraValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaYHoraNoValidos(){
		Tiempo diaYHoraNoValidos = new Tiempo(7,22.0);
		Assert.assertFalse(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaYHoraNoValidos));
	}
	
	@Test
	public void CGPAlgunServicioDisponible(){
		Assert.assertTrue(dispositivo.estaDisponible(cgp, null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	
}
