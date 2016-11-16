package Server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.GestorConsultas;
import Model.POI;
import Model.ResultadoBusqueda;
import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.Terminal;
import ObserversTerminal.AccionesTerminal;
import ObserversTerminal.AlmacenarBusqueda;
import ObserversTerminal.NotificarAdministrador;
import ObserversTerminal.ReportePorFecha;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	    public static void main(String[] args) {
	    	Spark.port(8700);
	    	
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
	    	RepositorioPOIs.getInstance().agregar(paradaDel47);
	    	
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
			
	    	RepositorioPOIs.getInstance().agregar(libreriaEscolar);
	    	
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
	    	
			DebugScreen.enableDebugScreen();
			Router.configure();
	    }
}