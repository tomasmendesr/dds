package testEntrega1;

import org.uqbar.geodds.Point;

import org.uqbar.geodds.Polygon;

import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;
import POIsExt.Servicio;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		comuna8 = new Comuna(8);
		zonaComuna8 = new Polygon();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);

		// Inicializo Banco -- Av Riestra 5002

		banco = new Banco(new Point(-34.6719, -58.4695));
		banco.setComuna(comuna8);
		
		// Parada del 47 -- Corvanalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));

		
		// CGP -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669)); //Servicios [tramiteDeDni,licenciaDeConducir]	
		cgp.setComuna(comuna8);
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
		libreriaEscolar.setComuna(comuna8);		
		RangoDeAtencion rangoLunesManana = new RangoDeAtencion(1,8,0,13,30);
		RangoDeAtencion rangoMartesManana = new RangoDeAtencion(2,8,0,13,30);
		RangoDeAtencion rangoMiercolesManana = new RangoDeAtencion(3,8,0,13,30);
		RangoDeAtencion rangoJuevesManana = new RangoDeAtencion(4,8,0,13,30);
		RangoDeAtencion rangoViernesManana = new RangoDeAtencion(5,8,0,13,30);
		RangoDeAtencion rangoSabadoManana = new RangoDeAtencion(6,8,0,13,30);
		RangoDeAtencion rangoLunesTarde = new RangoDeAtencion(1,14,30,20,0);
		RangoDeAtencion rangoMartesTarde = new RangoDeAtencion(2,14,30,20,0);
		RangoDeAtencion rangoMiercolesTarde = new RangoDeAtencion(3,14,30,20,0);
		RangoDeAtencion rangoJuevesTarde = new RangoDeAtencion(4,14,30,20,0);
		RangoDeAtencion rangoViernesTarde = new RangoDeAtencion(5,14,30,20,0);
		List<RangoDeAtencion> listaDeRangosDeAtencionLibreria = new ArrayList<RangoDeAtencion>();
		listaDeRangosDeAtencionLibreria.add(rangoLunesManana);
		listaDeRangosDeAtencionLibreria.add(rangoLunesTarde);
		listaDeRangosDeAtencionLibreria.add(rangoMartesManana);
		listaDeRangosDeAtencionLibreria.add(rangoMartesTarde);
		listaDeRangosDeAtencionLibreria.add(rangoMiercolesManana);
		listaDeRangosDeAtencionLibreria.add(rangoMiercolesTarde);
		listaDeRangosDeAtencionLibreria.add(rangoJuevesManana);
		listaDeRangosDeAtencionLibreria.add(rangoJuevesTarde);
		listaDeRangosDeAtencionLibreria.add(rangoViernesManana);
		listaDeRangosDeAtencionLibreria.add(rangoViernesTarde);
		listaDeRangosDeAtencionLibreria.add(rangoSabadoManana);
		libreriaEscolar.setColeccionDeRangosDeAtencion(listaDeRangosDeAtencionLibreria);
		
		
		//Instancio servicio de tramite de DNI
		RangoDeAtencion rangoDeAtencionDNILunes = new RangoDeAtencion(1,10,0,19,0);
		RangoDeAtencion rangoDeAtencionDNIMartes = new RangoDeAtencion(2,10,0,19,0);
		RangoDeAtencion rangoDeAtencionDNIMiercoles = new RangoDeAtencion(3,10,0,19,0);
		RangoDeAtencion rangoDeAtencionDNIJueves = new RangoDeAtencion(4,10,0,19,0);
		RangoDeAtencion rangoDeAtencionDNIViernes = new RangoDeAtencion(5,10,0,19,0);
		RangoDeAtencion rangoDeAtencionDNISabado = new RangoDeAtencion(6,10,0,19,0);
		List<RangoDeAtencion> listaDeRangosDeAtencionDNI = new ArrayList<RangoDeAtencion>();
		listaDeRangosDeAtencionDNI.add(rangoDeAtencionDNILunes); listaDeRangosDeAtencionDNI.add(rangoDeAtencionDNIMartes);
		listaDeRangosDeAtencionDNI.add(rangoDeAtencionDNIMiercoles); listaDeRangosDeAtencionDNI.add(rangoDeAtencionDNIJueves);
		listaDeRangosDeAtencionDNI.add(rangoDeAtencionDNIViernes); listaDeRangosDeAtencionDNI.add(rangoDeAtencionDNISabado);
		Servicio tramiteDeDNI = new Servicio("Tramite de DNI",listaDeRangosDeAtencionDNI);
					
		//Instancion servicio de licnencias de conducir
		RangoDeAtencion rangoDeAtencionLicenciaDeConducirLunes = new RangoDeAtencion(1,11,0,16,0);
		RangoDeAtencion rangoDeAtencionLicenciaDeConducirMartes = new RangoDeAtencion(2,11,0,16,0);
		RangoDeAtencion rangoDeAtencionLicenciaDeConducirMiercoles = new RangoDeAtencion(3,11,0,16,0);
		RangoDeAtencion rangoDeAtencionLicenciaDeConducirJueves = new RangoDeAtencion(4,11,0,16,0);
		RangoDeAtencion rangoDeAtencionLicenciaDeConducirViernes = new RangoDeAtencion(5,11,0,16,0);
		List<RangoDeAtencion> listaDeRangosDeAtencionLicenciaDeConducir = new ArrayList<RangoDeAtencion>();
		listaDeRangosDeAtencionLicenciaDeConducir.add(rangoDeAtencionLicenciaDeConducirLunes);
		listaDeRangosDeAtencionLicenciaDeConducir.add(rangoDeAtencionLicenciaDeConducirMartes);
		listaDeRangosDeAtencionLicenciaDeConducir.add(rangoDeAtencionLicenciaDeConducirMiercoles);
		listaDeRangosDeAtencionLicenciaDeConducir.add(rangoDeAtencionLicenciaDeConducirJueves);
		listaDeRangosDeAtencionLicenciaDeConducir.add(rangoDeAtencionLicenciaDeConducirViernes);
		Servicio licenciaConducir = new Servicio("Tramite de licencia de conducir",listaDeRangosDeAtencionLicenciaDeConducir);
						
		//Termino de setear la coleccion de servicios de CGP
		ArrayList<Servicio> coleccionDeServiciosDeCGP = new ArrayList<Servicio>();
		coleccionDeServiciosDeCGP.add(licenciaConducir);
		coleccionDeServiciosDeCGP.add(tramiteDeDNI);
		cgp.setListaDeServicios(coleccionDeServiciosDeCGP);


		//Instancio y seteo servicios de banco
		
		RangoDeAtencion rangoBancarioLunes = new RangoDeAtencion(1,10,0,15,0);
		RangoDeAtencion rangoBancarioMartes = new RangoDeAtencion(2,10,0,15,0);
		RangoDeAtencion rangoBancarioMiercoles = new RangoDeAtencion(3,10,0,15,0);
		RangoDeAtencion rangoBancarioJueves = new RangoDeAtencion(4,10,0,15,0);
		RangoDeAtencion rangoBancarioViernes = new RangoDeAtencion(5,10,0,15,0);
		List<RangoDeAtencion> rangoDeAtencionBancario = new ArrayList<RangoDeAtencion>();
		rangoDeAtencionBancario.add(0, rangoBancarioLunes);		rangoDeAtencionBancario.add(1, rangoBancarioMartes);
		rangoDeAtencionBancario.add(2, rangoBancarioMiercoles);	rangoDeAtencionBancario.add(3, rangoBancarioJueves);
		rangoDeAtencionBancario.add(4, rangoBancarioViernes);

		Servicio atencionAlCliente = new Servicio("Atencion al cliente", rangoDeAtencionBancario);
		Servicio atencionTarjetasDeCredito = new Servicio("Atencion a tarjetas de credito", rangoDeAtencionBancario);
		
		ArrayList<Servicio> coleccionDeServiciosDeBanco = new ArrayList<Servicio>();
		coleccionDeServiciosDeBanco.add(atencionTarjetasDeCredito);
		coleccionDeServiciosDeBanco.add(atencionAlCliente);
		banco.setListaDeServicios(coleccionDeServiciosDeBanco);
		
	}
	
	

	
	//TESTS DE PARADA DE COLECTIVO
	
	@Test
	public void paraDaDeColectivoSiempreDisponible(){
		LocalDateTime cualquierHoraCualquierFecha = LocalDateTime.now();
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
	
	/*@Test
	public void CGPAlgunServicioDisponible(){
		Assert.assertTrue(cgp.estaDisponible(null,null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	@Test
	public void CGPNingunServicioDisponible(){
		Assert.assertFalse(cgp.estaDisponible(null,null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA*/
	
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
	
	/*@Test
	public void BancoAlgunServicioDisponible(){
		Assert.assertTrue(banco.estaDisponible(null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA
	
	@Test 
	public void BancoNingunServicioDisponible(){
		Assert.assertFalse(banco.estaDisponible(null, null));
	}	//ESTE TEST DEPENDE DE LA HORA EN LA QUE SE LO CORRA*/
	
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

