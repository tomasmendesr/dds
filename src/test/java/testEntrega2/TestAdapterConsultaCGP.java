package testEntrega2;



import java.util.List;

import org.junit.After;
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
import Repos.RepositorioPOIs;
import Repos.RepositorioPOIsExternos;
import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import de.flapdoodle.embedmongo.runtime.Network;


public class TestAdapterConsultaCGP {

	private Comuna comuna8;
	private CGP cgp;
	private AdapterConsultaCGP adapterConsultaCGP;
	private ComponenteExternoConsultaCGPStub componenteExternoConsultaCGP;
	private RepositorioPOIsExternos repositorioPOIsExternos;
	static int PORT;
	static MongodProcess mongod;
	
	
	@Before
	public void init() throws Exception{
		
		//Abro conexion con Mongodb
		PORT = 27017;
		MongodConfig config = new MongodConfig(Version.V2_0, PORT, Network.localhostIsIPv6());
		MongodExecutable prepared = MongoDBRuntime.getDefaultInstance().prepare(config);
		mongod = prepared.start();
					
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.setComuna(comuna8);
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
		
		repositorioPOIsExternos = RepositorioPOIsExternos.getInstance();
		
		// Instancio ComponenteExternoConsultaCGP
		
		componenteExternoConsultaCGP = new ComponenteExternoConsultaCGPStub();
		
		// AdapterCGP
		
		adapterConsultaCGP = new AdapterConsultaCGP(componenteExternoConsultaCGP);
		
	}
	
	//TEST COMPONENTE STUB
	
	/*@Test
	public void componenteExternoHaceLaConsultaDeberiaTener1ListaCon1POI(){
		List<CentroDTO> consultaComponente = componenteExternoConsultaCGP.realizarConsultaCGP("cgp");
		Assert.assertEquals(1, consultaComponente.size());
	}
	
	@Test
	public void componenteExternoHaceLaConsultaYSuUnicoElementoEsUnCGPDeDireccionAvEscalada3100(){
		List<CentroDTO> consultaComponente = componenteExternoConsultaCGP.realizarConsultaCGP("cgp");
		Assert.assertEquals("Av Escalada 3100", consultaComponente.get(0).getDireccionCGP());
	}*/
	
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
	
    @After
    public void tearDown(){
        repositorioPOIsExternos.borrarTodosLosPois();
        RepositorioPOIs.resetPOIs();
        if (mongod != null) mongod.stop();
    }	
}
