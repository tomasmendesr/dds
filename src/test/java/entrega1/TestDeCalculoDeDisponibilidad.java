package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

public class TestDeCalculoDeDisponibilidad {

	private Polygon 				comuna8;
	private ParadaDeColectivo 		paradaDel47;
	private CGP 					cgp;
	private Banco 					banco;
	private Dispositivo 			dispositivo;
	private LocalComercial 			libreriaEscolar;
	private LocalComercial 			kioskoDeDiarios;	
	
	
	
	
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
		//instancio y agrego rangos de horario
		List<Double> horasManana = Arrays.asList(8.0,9.0,10.0,11.0,12.0);
		List<Double> horasTarde = Arrays.asList(14.0,15.0,16.0,17.0,18.0,19.0,20.0);
		List<Integer>diasManana = Arrays.asList(1,2,3,4,5,6);
		List<Integer>diasTarde = Arrays.asList(1,2,3,4,5);
		RangoDeAtencion rangoManana = new RangoDeAtencion(horasManana,diasManana);
		RangoDeAtencion rangoTarde = new RangoDeAtencion(horasTarde,diasTarde);
		libreriaEscolar.addRangoDeAtencion(rangoManana);
		libreriaEscolar.addRangoDeAtencion(rangoTarde);
		
		// Kiosko de Diarios -- Albariño 3702
		Rubro rubroKioskoDeDiarios = new Rubro(200.0);
		kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), comuna8, rubroKioskoDeDiarios);
		

	}
	
	//TESTS DE PARADA DE COLECTIVO
	
	@Test
	public void paraDaDeColectivoSiempreDisponible(){
		Assert.assertTrue(dispositivo.estaDisponible(paradaDel47, null, null));
	}
	
	//TESTS DE CGP
	
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
	
	@Test
	public void CGPNingunServicioDisponible(){
		Assert.assertFalse(dispositivo.estaDisponible(cgp, null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	
	//TESTS DE BANCO
	//(NO HACE FALTA PROBAR CON OTROS SERVICIOS PORQUE TODOS TIENEN EL MISMO RANGO HORARIO)
	
	@Test
	public void BancoDisponibleServicioAtencionAlClienteDiaYHorarioValido(){
		Tiempo diaYHorarioValidos = new Tiempo(1,11.0);
		Assert.assertTrue(dispositivo.estaDisponible(banco,"Atencion al cliente", diaYHorarioValidos));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlClienteDiaValidoYHorarioNoValido(){
		Tiempo diaValidoYHorarioNoValido = new Tiempo(4,15.0);
		Assert.assertFalse(dispositivo.estaDisponible(banco,"Atencion al cliente", diaValidoYHorarioNoValido));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlClienteDiaNoValidoYHorarioValido(){
		Tiempo diaNoValidoYHorarioValido = new Tiempo(6,10.0);
		Assert.assertFalse(dispositivo.estaDisponible(banco,"Atencion al cliente", diaNoValidoYHorarioValido));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlCleinteDiaYHorarioNoValidos(){
		Tiempo diaYHorarioNoValidos = new Tiempo(7,18.0);
		Assert.assertFalse(dispositivo.estaDisponible(banco,"Atencion al cliente", diaYHorarioNoValidos));
	}
	
	@Test
	public void BancoAlgunServicioDisponible(){
		Assert.assertTrue(dispositivo.estaDisponible(banco,null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	@Test 
	public void BancoNingunServicioDisponible(){
		Assert.assertFalse(dispositivo.estaDisponible(banco, null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	//TESTS DE LOCAL COMERCIAL
	
	@Test
	public void LocalComercialDisponibleDiaYHoraValidosManana(){
		Tiempo diaYHoraValidosManana = new Tiempo(1,8.0);
		Assert.assertTrue(dispositivo.estaDisponible(libreriaEscolar, null, diaYHoraValidosManana));
	}
	
	@Test 
	public void LocalComercialDisponibleDiaYHoraValidosTarde(){
		Tiempo diaYHoraValidosTarde = new Tiempo(3,15.0);
		Assert.assertTrue(dispositivo.estaDisponible(libreriaEscolar, null, diaYHoraValidosTarde));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaNoValidoHoraValidaManana(){
		Tiempo diaNoValidoYHoraValidaManana = new Tiempo(7,9.0);
		Assert.assertFalse(dispositivo.estaDisponible(libreriaEscolar, null,diaNoValidoYHoraValidaManana));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaValidoHoraNoValidaTarde(){
		Tiempo diaValidoYHoraNoValidaTarde = new Tiempo(6,16.0);
		Assert.assertFalse(dispositivo.estaDisponible(libreriaEscolar,null,diaValidoYHoraNoValidaTarde));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaValidoHoraNoValida(){
		Tiempo diaValidoYHoraNoValida = new Tiempo(3,13.0);
		Assert.assertFalse(dispositivo.estaDisponible(libreriaEscolar, null, diaValidoYHoraNoValida));
	}
	
}

