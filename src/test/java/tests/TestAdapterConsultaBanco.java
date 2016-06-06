package tests;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Adapters.AdapterConsultaBanco;
import Adapters.AdapterConsultaCGP;
import ComponentesExternos.ComponenteExternoConsultaBancoStub;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Master.POI;
import Master.RepositorioPOIs;
import POIs.Banco;
import POIs.CGP;
import POIs.Comuna;
import POIs.RangoDeAtencion;
import POIs.Servicio;


public class TestAdapterConsultaBanco {
	
	private Comuna 	comuna8;
	private Banco	banco;
	private Polygon	zonaComuna8;
	private RepositorioPOIs repoPOIs;
	private AdapterConsultaBanco adapterConsultaBanco;
	private ComponenteExternoConsultaBancoStub componenteExternoConsultaBancoStub;
	
	
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
		
		// Componente externo stub
		componenteExternoConsultaBancoStub = new ComponenteExternoConsultaBancoStub();
		
		// Adapter consulta banco
		adapterConsultaBanco = new AdapterConsultaBanco(componenteExternoConsultaBancoStub);
		
		
		/*
		// Instancio banco
		
		banco = new Banco(new Point(-34.6719, -58.4695));
		banco.setNombre("Banco Rio");
		
		//Instacio rango de atencion de banco
		double	horaDeAperturaBanco				= 10.0;
		double	horaDeCierreBanco				= 15.0;
		int		diaDeInicioDeAtencionBanco		= 1;
		int		diaDeFinDeAtencionBanco			= 5;
		RangoDeAtencion rangoDeAtencionDeBanco = 
		new RangoDeAtencion(horaDeAperturaBanco,horaDeCierreBanco,diaDeInicioDeAt11encionBanco,diaDeFinDeAtencionBanco);
				
		//Instancio servicios de banco
		Servicio atencionAlCliente = new Servicio("Atencion al cliente", rangoDeAtencionDeBanco);
		Servicio atencionTarjetasDeCredito = new Servicio("Atencion a tarjetas de credito", rangoDeAtencionDeBanco);
			
		//Instancio y seteo coleccion de servicios de banco
		ArrayList<Servicio> coleccionDeServiciosDeBanco = new ArrayList<Servicio>();
		coleccionDeServiciosDeBanco.add(atencionTarjetasDeCredito);
		coleccionDeServiciosDeBanco.add(atencionAlCliente);
		banco.setListaDeServicios(coleccionDeServiciosDeBanco);
	
		//Agrega POIs al mapa
		repoPOIs = new RepositorioPOIs();
		repoPOIs.agregarPOI(banco);
		*/
	}
	
	//TESTS DE COMPONENTE EXTERNO STUB
	
	@Test
	public void componenteExternoHaceLaConsultaDeberiaTenerUnaListaCon1POI(){
		List<JSONObject> consultaBancoJson = componenteExternoConsultaBancoStub.realizarConsultaBanco("banco");
		Assert.assertEquals(1,consultaBancoJson.size());
	}
	
	@Test 
	public void componenteExternoTiene1BancoLlamadoBancoDeLaPlaza(){
		List<JSONObject> consultaBancoJson = componenteExternoConsultaBancoStub.realizarConsultaBanco("banco");
		Assert.assertEquals("Banco de la Plaza", consultaBancoJson.get(0).get("banco"));
	}
	
	//TESTS DE ADAPTER
	
	@Test
	public void adapterRealizaLaConsultaYDevuevleUnaListaCon1POI(){
		List<POI> consultaBancoPOI = adapterConsultaBanco.realizarConsulta("banco");
		Assert.assertEquals(1,consultaBancoPOI.size());
	}
	
	@Test 
	public void adapterRealizaLaConsultaYSuUnicoElementoEsUnPOILlamadoBancoDeLaPlaza(){
		List<POI> consultaBancoPOI = adapterConsultaBanco.realizarConsulta("banco");
		Assert.assertEquals("Banco de la Plaza",consultaBancoPOI.get(0).getNombre());
	}
	
	@Test
	public void adapterRealizaLaConsultaYSuUnicoElementoEsUnPOIGeolocalizado(){
		List<POI> consultaBancoPOI = adapterConsultaBanco.realizarConsulta("banco");
		Assert.assertTrue(consultaBancoPOI.get(0).estaGeolocalizado());
	}
	
	@Test
	public void adapterRealizaLaConsultaYSuUnicoElementoEsUnPOIValido(){
		List<POI> consultaBancoPOI = adapterConsultaBanco.realizarConsulta("banco");
		Assert.assertTrue(consultaBancoPOI.get(0).esPOIValido());
	}
}
