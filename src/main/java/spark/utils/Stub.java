package spark.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.GestorConsultas;
import Model.POI;
import Model.ResultadoBusqueda;
import Model.Terminal;
import ObserversTerminal.AccionesTerminal;
import ObserversTerminal.AlmacenarBusqueda;
import ObserversTerminal.NotificarAdministrador;
import ObserversTerminal.ReportePorFecha;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;
import POIsExt.Servicio;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;

public class Stub {

	public static void persistirModelo(){
		Comuna comuna8 = new Comuna(new Long(8));
    	PolygonAdapter zonaComuna8 = new PolygonAdapter(new Long(1));
    	zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
    	zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
    	zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
    	zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
    	zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
    	comuna8.setZona(zonaComuna8);
    	
    	Comuna comuna5 = new Comuna(new Long(5));
    	PolygonAdapter zonaComuna5 = new PolygonAdapter(new Long(2));
    	zonaComuna5.agregarPoint(new Point(-35.6744,-57.5025));
    	zonaComuna5.agregarPoint(new Point(-35.6578,-57.4787));
    	zonaComuna5.agregarPoint(new Point(-35.6648,-57.4697));
    	zonaComuna5.agregarPoint(new Point(-35.6621,-57.4240));
    	zonaComuna5.agregarPoint(new Point(-35.7048,-57.4612));
    	comuna5.setZona(zonaComuna5);
    	
    	ParadaDeColectivo paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
        paradaDel47.setDireccion("Corvalan 3691");
        paradaDel47.setNombre("Parada del 47");
        paradaDel47.addTag("47");
        paradaDel47.addTag("parada");
        paradaDel47.setTipoDePOI("Parada de Colectivo");
    	
    	Rubro rubroLibreriaEscolar = new Rubro(500.0);
		LocalComercial libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
		libreriaEscolar.addTag("libreria");
		libreriaEscolar.addTag("escolar");
		libreriaEscolar.setComuna(comuna8);
		libreriaEscolar.setDireccion("Peru 201");
		libreriaEscolar.setNombre("Libreria");
		libreriaEscolar.setTipoDePOI("Local Comercial");
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
		libreriaEscolar.setListaDeRangosDeAtencion(listaDeRangosDeAtencionLibreria);
		
    	
		
		// CGP -- Av Escalada 3100
		CGP cgp = new CGP(new Point(-34.6672, -58.4669)); //Servicios [tramiteDeDni,licenciaDeConducir]	
		cgp.setComuna(comuna8);
		cgp.setNombre("cgp lugano");
		cgp.setTipoDePOI("CGP");
		cgp.addTag("cgp");
        cgp.addTag("servicios");
        cgp.setDireccion("Mozar 1");

		Banco banco = new Banco(new Point(-34.6719, -58.4695));
		banco.setComuna(comuna8);
		banco.setNombre("Banco la Nacion");
		banco.setTipoDePOI("Banco");
		banco.addTag("cgp");
        banco.addTag("servicios");

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
		cgp.setServicios(coleccionDeServiciosDeCGP);


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
		banco.setServicios(coleccionDeServiciosDeBanco);

		RepositorioPOIs.getInstance().agregar(paradaDel47);
		RepositorioPOIs.getInstance().agregar(libreriaEscolar);
	//	RepositorioPOIs.getInstance().agregar(banco);
		RepositorioPOIs.getInstance().agregar(cgp);
		
		ReportePorFecha observerReportePorFecha = new ReportePorFecha();
    	NotificarAdministrador observerNotificarAdmin = new NotificarAdministrador();
    	AlmacenarBusqueda observerAlmacenarBusquedas = new AlmacenarBusqueda();
    	
    	List<AccionesTerminal> accionesTerminal = new ArrayList<>();
    	List<AccionesTerminal> accionesTerminal2 = new ArrayList<>();
    	accionesTerminal.add(observerNotificarAdmin);
    	accionesTerminal.add(observerAlmacenarBusquedas);
    	accionesTerminal2.add(observerReportePorFecha);
    	
    	Terminal terminal = new Terminal("Terminal Abasto");
    	terminal.setComuna(comuna8);
    	terminal.setUbicacion(new Point(-34.6715, -58.4676)); // misma ubicacion que la parada
    	terminal.setObservers(accionesTerminal);
    	RepositorioTerminales.getInstance().agregar(terminal);
    	
    	Terminal terminal2 = new Terminal("campus");
    	terminal2.setComuna(comuna5);
    	terminal2.setUbicacion(new Point(-34.6715, -58.4676)); // = que la parada
    	terminal2.setObservers(accionesTerminal2);
    	RepositorioTerminales.getInstance().agregar(terminal2);

		GestorConsultas gestorConsultas = new GestorConsultas();
		List<POI> consulta1 = gestorConsultas.buscarPOIs("parada");
		List<POI> consulta2 = gestorConsultas.buscarPOIs("libreria");
		ResultadoBusqueda resultado1 = new ResultadoBusqueda("parada",consulta1,1.01,terminal);
		resultado1.setMomentoDeBusqueda(new Date());
		ResultadoBusqueda resultado2 = new ResultadoBusqueda("libreria",consulta2,4.0,terminal2);
		resultado2.setMomentoDeBusqueda(new Date());
		//RepositorioBusquedas.getInstance().addResultadoBusqueda(resultado1);
		//RepositorioBusquedas.getInstance().addResultadoBusqueda(resultado2);
		
	}
}
