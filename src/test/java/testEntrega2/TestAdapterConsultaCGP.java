package testEntrega2;



import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;


import Adapters.AdapterConsultaCGP;
import AdaptersExt.CentroDTO;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Model.POI;
import POIs.CGP;

import POIsExt.Comuna;


public class TestAdapterConsultaCGP {

	private Comuna comuna8;
	private CGP cgp;
	private AdapterConsultaCGP adapterConsultaCGP;
	private ComponenteExternoConsultaCGPStub componenteExternoConsultaCGP;
	
	
	@Before
	public void init(){
					
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.setComuna(comuna8);
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
		
		// Instancio ComponenteExternoConsultaCGP
		
		componenteExternoConsultaCGP = new ComponenteExternoConsultaCGPStub();
		
		// AdapterCGP
		
		adapterConsultaCGP = new AdapterConsultaCGP(componenteExternoConsultaCGP);
		
	}
	
	//TEST COMPONENTE STUB
	
	@Test
	public void componenteExternoHaceLaConsultaDeberiaTener1ListaCon1POI(){
		List<CentroDTO> consultaComponente = componenteExternoConsultaCGP.realizarConsultaCGP("cgp");
		Assert.assertEquals(1, consultaComponente.size());
	}
	
	@Test
	public void componenteExternoHaceLaConsultaYSuUnicoElementoEsUnCGPDeDireccionAvEscalada3100(){
		List<CentroDTO> consultaComponente = componenteExternoConsultaCGP.realizarConsultaCGP("cgp");
		Assert.assertEquals("Av Escalada 3100", consultaComponente.get(0).getDireccionCGP());
	}
	
	// TEST ADAPTER
	
	@Test
	public void adapterRealizaLaConsultaYDevuevleUnaListaCon1POI(){
		List<POI> listaAdaptada = adapterConsultaCGP.realizarConsulta("cgp");
		Assert.assertEquals(1, listaAdaptada.size());
	}
	
	@Test
	public void adapterRealizaLaConsultaYSuUnicoElementoEsUnPOILlamadoCGPDeAvenidaEscalada(){
		List<POI> listaAdaptada = adapterConsultaCGP.realizarConsulta("cgp");
		Assert.assertEquals("CGP de Av Escalada 3100", listaAdaptada.get(0).getNombre());
	}
	
	
	
}
