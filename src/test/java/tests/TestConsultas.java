package tests;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Adapters.AdapterConsultaBanco;
import Adapters.AdapterConsultaCGP;
import ComponentesExternos.ComponenteExternoConsulta;
import ComponentesExternos.ComponenteExternoConsultaBancoStub;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;



import Master.POI;
import Master.RepositorioPOIs;

public class TestConsultas {
	
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Polygon	zonaComuna8;
	private RepositorioPOIs repositorioPOIs;
	private AdapterConsultaBanco adapterConsultaBanco;
	private AdapterConsultaCGP adapterConsultaCGP;
	private ComponenteExternoConsulta componenteExternoConsultaBancoStub;
	private ComponenteExternoConsultaCGPStub componenteExternoConsultaCGPStub;
	
	
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
				
		// Parada del 47 -- Corvalan 3691
		paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
		paradaDel47.setDireccion("Corvalan 3691");
			
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.setComuna(comuna8);
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
		
		// Banco -- Av Riestra 5002
		banco = new Banco(new Point(-34.6719, -58.4695));
		banco.addTag("deposito");
		banco.setComuna(comuna8);
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
		libreriaEscolar.setComuna(comuna8);
		libreriaEscolar.addTag("libreria");
		
		// Kiosko de Diarios -- Albariño 3702
		Rubro rubroKioskoDeDiarios = new Rubro(200.0);
		kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), rubroKioskoDeDiarios);
		kioskoDeDiarios.setComuna(comuna8);
		kioskoDeDiarios.addTag("caramelos");
		kioskoDeDiarios.setNombre("Kiosko de Carlitos");
				
		//Agrega POIs al repositorioPOIs
		repositorioPOIs = new RepositorioPOIs();
		repositorioPOIs.agregarPOI(paradaDel47);
		repositorioPOIs.agregarPOI(cgp);
		repositorioPOIs.agregarPOI(banco);
		repositorioPOIs.agregarPOI(libreriaEscolar);
		repositorioPOIs.agregarPOI(kioskoDeDiarios);
		
		//Inicializo los componentes externos
		
		componenteExternoConsultaBancoStub = new ComponenteExternoConsultaBancoStub();
		componenteExternoConsultaCGPStub = new ComponenteExternoConsultaCGPStub();		
		
		//Inicializo los adapters
		
		adapterConsultaBanco = new AdapterConsultaBanco(componenteExternoConsultaBancoStub);
		adapterConsultaCGP = new AdapterConsultaCGP(componenteExternoConsultaCGPStub);
		
		//Agrega adapters al repositorioPOIs
		repositorioPOIs.agregarAdapter(adapterConsultaBanco);
		repositorioPOIs.agregarAdapter(adapterConsultaCGP);
		
	}
	
	@Test
	public void consultaEnTodosLosOrigenesDeDatos(){
		List<POI> listaResultante = this.repositorioPOIs.consultarPOIs("banco");
		Assert.assertEquals(2, listaResultante.size()); // tiene el banco y el cgp
	}
	
	
	
	
}
