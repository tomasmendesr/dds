package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class TestDeCalculoDeDisponibilidad {

	private Comuna	 				comuna8;
	private ParadaDeColectivo 		paradaDel47;
	private CGP 					cgp;
	private Banco 					banco;
	private Dispositivo 			dispositivo;
	private LocalComercial 			libreriaEscolar;
	private Fecha					fechaDeHoy;
	private Polygon					zonaComuna8;
	
	
	@Before
	public void init(){
	
		// Instancio la fecha de hoy
		
		fechaDeHoy = Fecha.actual();
		
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
		double	horarioDeAperturaManana 	= 8.0;
		double	horarioDeCierreManana		= 13.30;
		int		diaDeInicioDeAtencionManana = 1;
		int		diaDeFinDeAtencionManana	= 6;
		
		double	horarioDeAperturaTarde	 	= 14.30;
		double	horarioDeCierreTarde		= 20.0;
		int		diaDeInicioDeAtencionTarde  = 1;
		int		diaDeFinDeAtencionTarde		= 5;
		
		RangoDeAtencion rangoManana =
				new RangoDeAtencion(horarioDeAperturaManana,horarioDeCierreManana,diaDeInicioDeAtencionManana,diaDeFinDeAtencionManana);
		RangoDeAtencion rangoTarde =
				new RangoDeAtencion(horarioDeAperturaTarde,horarioDeCierreTarde,diaDeInicioDeAtencionTarde,diaDeFinDeAtencionTarde);
		
		libreriaEscolar.addRangoDeAtencion(rangoManana);
		libreriaEscolar.addRangoDeAtencion(rangoTarde);
		
		

	}
	
	//TESTS DE PARADA DE COLECTIVO
	
	@Test
	public void paraDaDeColectivoSiempreDisponible(){
		Assert.assertTrue(dispositivo.estaDisponible(paradaDel47, null, null));
	}
	
	//TESTS DE CGP
	
	@Test 
	public void CGPDisponibleServicioTramiteDeDNIDiaYHoraValida(){
		Tiempo diaYHoraValida = new Tiempo(fechaDeHoy,2,14.0,23.0);
		Assert.assertTrue(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaYHoraValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaValidoHoraNoValida(){
		Tiempo diaValidoHoraNoValida = new Tiempo(fechaDeHoy,1,22.0,42.0);
		Assert.assertFalse(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaValidoHoraNoValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaNoValidoHoraValida(){
		Tiempo diaNoValidoHoraValida = new Tiempo(fechaDeHoy,7,14.0,14.0);
		Assert.assertFalse(dispositivo.estaDisponible(cgp, "Tramite de DNI", diaNoValidoHoraValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaYHoraNoValidos(){
		Tiempo diaYHoraNoValidos = new Tiempo(fechaDeHoy,7,22.0,17.0);
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
		Tiempo diaYHorarioValidos = new Tiempo(fechaDeHoy,1,11.0,19.0);
		Assert.assertTrue(dispositivo.estaDisponible(banco,"Atencion al cliente", diaYHorarioValidos));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlClienteDiaValidoYHorarioNoValido(){
		Tiempo diaValidoYHorarioNoValido = new Tiempo(fechaDeHoy,4,15.0,22.0);
		Assert.assertFalse(dispositivo.estaDisponible(banco,"Atencion al cliente", diaValidoYHorarioNoValido));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlClienteDiaNoValidoYHorarioValido(){
		Tiempo diaNoValidoYHorarioValido = new Tiempo(fechaDeHoy,6,10.0,30.0);
		Assert.assertFalse(dispositivo.estaDisponible(banco,"Atencion al cliente", diaNoValidoYHorarioValido));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlCleinteDiaYHorarioNoValidos(){
		Tiempo diaYHorarioNoValidos = new Tiempo(fechaDeHoy,7,18.0,59.0);
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
		Tiempo diaYHoraValidosManana = new Tiempo(fechaDeHoy,1,8.0,42.0);
		Assert.assertTrue(dispositivo.estaDisponible(libreriaEscolar, null, diaYHoraValidosManana));
	}
	
	@Test 
	public void LocalComercialDisponibleDiaYHoraValidosTarde(){
		Tiempo diaYHoraValidosTarde = new Tiempo(fechaDeHoy,3,15.0,37.0);
		Assert.assertTrue(dispositivo.estaDisponible(libreriaEscolar, null, diaYHoraValidosTarde));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaNoValidoHoraValidaManana(){
		Tiempo diaNoValidoYHoraValidaManana = new Tiempo(fechaDeHoy,7,9.0,2.0);
		Assert.assertFalse(dispositivo.estaDisponible(libreriaEscolar, null,diaNoValidoYHoraValidaManana));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaValidoHoraNoValidaTarde(){
		Tiempo diaValidoYHoraNoValidaTarde = new Tiempo(fechaDeHoy,6,16.0,10.0);
		Assert.assertFalse(dispositivo.estaDisponible(libreriaEscolar,null,diaValidoYHoraNoValidaTarde));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaValidoHoraNoValida(){
		Tiempo diaValidoYHoraNoValida = new Tiempo(fechaDeHoy,3,13.0,45.0);
		Assert.assertFalse(dispositivo.estaDisponible(libreriaEscolar, null, diaValidoYHoraNoValida));
	}
	
}

