package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Adapters.AdapterConsultaBanco;
import Adapters.AdapterConsultaCGP;
import AdaptersExt.JSON;
import ComponentesExternos.ComponenteExternoConsultaBanco;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Master.POI;
import Master.RepositorioPOIs;
import POIS.Banco;
import POIS.CGP;
import POIS.Comuna;
import POIS.RangoDeAtencion;
import POIS.Servicio;


public class TestAdapterConsultaBanco {
	
	private Comuna 	comuna8;
	private Banco	banco;
	private Polygon	zonaComuna8;
	private RepositorioPOIs repoPOIs;
	private AdapterConsultaBanco adapterConsultaBanco;
	private ComponenteExternoConsultaBanco componenteExternoConsultaBanco;
	
	
	@Before
	public void init(){
		
		adapterConsultaBanco = Mockito.mock(AdapterConsultaBanco.class);
		componenteExternoConsultaBanco = Mockito.mock(ComponenteExternoConsultaBanco.class);
		
		// Comuna 8
		comuna8 = new Comuna(8);
		zonaComuna8 = new Polygon();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
					
		// Instancio banco
		
		banco = new Banco(new Point(-34.6719, -58.4695));
		banco.setNombre("Banco Rio");
		
		//Instacio rango de atencion de banco
		double	horaDeAperturaBanco				= 10.0;
		double	horaDeCierreBanco				= 15.0;
		int		diaDeInicioDeAtencionBanco		= 1;
		int		diaDeFinDeAtencionBanco			= 5;
		RangoDeAtencion rangoDeAtencionDeBanco = 
		new RangoDeAtencion(horaDeAperturaBanco,horaDeCierreBanco,diaDeInicioDeAtencionBanco,diaDeFinDeAtencionBanco);
				
		//Instancio servicios de banco
		Servicio atencionAlCliente = new Servicio("Atencion al cliente", rangoDeAtencionDeBanco);
		Servicio atencionTarjetasDeCredito = new Servicio("Atencion a tarjetas de credito", rangoDeAtencionDeBanco);
			
		//Instancio y seteo coleccion de servicios de banco
		ArrayList<Servicio> coleccionDeServiciosDeBanco = new ArrayList<Servicio>();
		coleccionDeServiciosDeBanco.add(atencionTarjetasDeCredito);
		coleccionDeServiciosDeBanco.add(atencionAlCliente);
		banco.setColeccionServicios(coleccionDeServiciosDeBanco);
	
		//Agrega POIs al mapa
		repoPOIs = new RepositorioPOIs();
		repoPOIs.agregarPOI(banco);
	
	}
	
	@Test
	public void testAdapterBanco(){
		String unaConsulta = "Banco Rio, Atencion al cliente";
		JSON consultaSinAdaptar = new JSON();
		List<POI> consultaAdaptada = new ArrayList<POI>();
		consultaAdaptada.add(banco);
		
		Mockito.when(componenteExternoConsultaBanco.realizarConsulta(unaConsulta)).thenReturn(consultaSinAdaptar);
		Assert.assertEquals(consultaSinAdaptar, componenteExternoConsultaBanco.realizarConsulta(unaConsulta));
		Mockito.when(adapterConsultaBanco.adaptarConsulta(consultaSinAdaptar)).thenReturn(consultaAdaptada);
		Assert.assertEquals(1, adapterConsultaBanco.adaptarConsulta(consultaSinAdaptar).size());
		Mockito.when(adapterConsultaBanco.realizarConsulta(unaConsulta)).thenCallRealMethod();
		Assert.assertEquals(1, adapterConsultaBanco.realizarConsulta(unaConsulta).size());
	}
	
}
