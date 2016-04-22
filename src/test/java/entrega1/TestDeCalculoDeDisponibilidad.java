package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import org.junit.Assert;

public class TestDeCalculoDeDisponibilidad {

	private Comuna	 				comuna8;
	private ParadaDeColectivo 		paradaDel47;
	private CGP 					cgp;
	private Banco 					banco;
	private LocalComercial 			libreriaEscolar;
	private Polygon					zonaComuna8;
	
	
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
		cgp = new CGP(new Point(-34.6672, -58.4669), comuna8); //Servicios [tramiteDeDni,licenciaDeConducir]	
		
		// Banco -- Av Riestra 5002
		banco = new Banco(new Point(-34.6719, -58.4695), comuna8);
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), comuna8, rubroLibreriaEscolar);
		//instancio y agrego rangos de horario de libreria
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
		LocalDateTime cualquierHoraCualquierFecha = LocalDateTime.now();
		Assert.assertTrue(paradaDel47.estaDisponible(null));
		Assert.assertTrue(paradaDel47.estaDisponible(cualquierHoraCualquierFecha));
	}
	
	//TESTS DE CGP
	
	@Test 
	public void CGPDisponibleServicioTramiteDeDNIDiaYHoraValida(){
		LocalDateTime diaYHoraValida = LocalDateTime.of(2016, 04, 18, 14, 30);
		Assert.assertTrue(cgp.estaDisponible("Tramite de DNI", diaYHoraValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaValidoHoraNoValida(){
		LocalDateTime diaValidoHoraNoValida = LocalDateTime.of(2016,04,18,22,42);
		Assert.assertFalse(cgp.estaDisponible("Tramite de DNI", diaValidoHoraNoValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaNoValidoHoraValida(){
		LocalDateTime diaNoValidoHoraValida = LocalDateTime.of(2016,04,24,14,14);
		Assert.assertFalse(cgp.estaDisponible("Tramite de DNI", diaNoValidoHoraValida));
	}
	
	@Test
	public void CGPNoDisponibleServicioTramiteDeDNIDiaYHoraNoValidos(){
		LocalDateTime diaYHoraNoValidos = LocalDateTime.of(2016,04,24,22,17);
		Assert.assertFalse(cgp.estaDisponible("Tramite de DNI", diaYHoraNoValidos));
	} 
	
	@Test
	public void CGPAlgunServicioDisponible(){
		Assert.assertTrue(cgp.estaDisponible(null,null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	@Test
	public void CGPNingunServicioDisponible(){
		Assert.assertFalse(cgp.estaDisponible(null,null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	//TESTS DE BANCO
	//(NO HACE FALTA PROBAR CON OTROS SERVICIOS PORQUE TODOS TIENEN EL MISMO RANGO HORARIO)
	
	@Test
	public void BancoDisponibleServicioAtencionAlClienteDiaYHorarioValido(){
		LocalDateTime diaYHorarioValidos = LocalDateTime.of(2016,04,18,11,19); //Lunes
		Assert.assertTrue(banco.estaDisponible("Atencion al cliente", diaYHorarioValidos));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlClienteDiaValidoYHorarioNoValido(){
		LocalDateTime diaValidoYHorarioNoValido = LocalDateTime.of(2016,04,21,15,22); //Jueves
		Assert.assertFalse(banco.estaDisponible("Atencion al cliente", diaValidoYHorarioNoValido));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlClienteDiaNoValidoYHorarioValido(){
		LocalDateTime diaNoValidoYHorarioValido = LocalDateTime.of(2016,04,23,10,30); //Sabado
		Assert.assertFalse(banco.estaDisponible("Atencion al cliente", diaNoValidoYHorarioValido));
	}
	
	@Test
	public void BancoNoDisponibleServicioAtencionAlCleinteDiaYHorarioNoValidos(){
		LocalDateTime diaYHorarioNoValidos = LocalDateTime.of(2016,04,24,18,59); //Domingo
		Assert.assertFalse(banco.estaDisponible("Atencion al cliente", diaYHorarioNoValidos));
	}
	
	@Test
	public void BancoAlgunServicioDisponible(){
		Assert.assertTrue(banco.estaDisponible(null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	@Test 
	public void BancoNingunServicioDisponible(){
		Assert.assertFalse(banco.estaDisponible(null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	//TESTS DE LOCAL COMERCIAL
	 
	@Test
	public void LocalComercialDisponibleDiaYHoraValidosManana(){
		LocalDateTime diaYHoraValidosManana = LocalDateTime.of(2016,04,18,8,42); //Lunes
		Assert.assertTrue(libreriaEscolar.estaDisponible(diaYHoraValidosManana));
	}
	
	@Test 
	public void LocalComercialDisponibleDiaYHoraValidosTarde(){
		LocalDateTime diaYHoraValidosTarde = LocalDateTime.of(2016,04,20,15,37); //Miercoles
		Assert.assertTrue(libreriaEscolar.estaDisponible(diaYHoraValidosTarde));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaNoValidoHoraValidaManana(){
		LocalDateTime diaNoValidoYHoraValidaManana = LocalDateTime.of(2016,04,24,9,2); //Domingo
		Assert.assertFalse(libreriaEscolar.estaDisponible(diaNoValidoYHoraValidaManana));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaValidoHoraNoValidaTarde(){
		LocalDateTime diaValidoYHoraNoValidaTarde = LocalDateTime.of(2016,04,23,16,10); //Sabado
		Assert.assertFalse(libreriaEscolar.estaDisponible(diaValidoYHoraNoValidaTarde));
	}
	
	@Test
	public void LocalComercialNoDisponibleDiaValidoHoraNoValida(){
		LocalDateTime diaValidoYHoraNoValida = LocalDateTime.of(2016,04,20,13,45); //Miercoles
		Assert.assertFalse(libreriaEscolar.estaDisponible(diaValidoYHoraNoValida));
	}
	
}

