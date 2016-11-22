package testEntrega6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Adapters.AdapterConsultaBanco;
import Adapters.AdapterConsultaCGP;
import Adapters.PolygonAdapter;
import ComponentesExternos.ComponenteExternoConsultaBancoStub;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Model.GestorConsultas;
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
import org.junit.Assert;

public class TestPersistirPOIsMongo {
	
	private Comuna comuna8;
	private PolygonAdapter zonaComuna8;
	private CGP cgp;
	private ComponenteExternoConsultaCGPStub componenteExternoConsultaCGP;
	private ComponenteExternoConsultaBancoStub componenteExternoConsultaBanco;
	private AdapterConsultaCGP adapterConsultaCGP;
	private AdapterConsultaBanco adapterConsultaBanco;
	private GestorConsultas gestorConsultas;
	static int PORT;
	static MongodProcess mongod;
	
	@Before
	public void init()throws Exception{
		
		//Abro conexion con Mongodb
		PORT = 27017;
		MongodConfig config = new MongodConfig(Version.V2_0, PORT, Network.localhostIsIPv6());
		MongodExecutable prepared = MongoDBRuntime.getDefaultInstance().prepare(config);
		mongod = prepared.start();
		
		comuna8 = new Comuna(new Long(8));
		zonaComuna8 = new PolygonAdapter(new Long(1));
		zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
		zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
		zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
		zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
		zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
		
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.setComuna(comuna8);
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
				
		// Instancio ComponenteExternoConsultaCGP
		
		componenteExternoConsultaCGP = new ComponenteExternoConsultaCGPStub();
		componenteExternoConsultaBanco = new ComponenteExternoConsultaBancoStub();
		
		// AdapterCGP
		
		adapterConsultaCGP = new AdapterConsultaCGP(componenteExternoConsultaCGP);
		adapterConsultaBanco = new AdapterConsultaBanco(componenteExternoConsultaBanco);
		
		gestorConsultas= new GestorConsultas();
		gestorConsultas.agregarAdapter(adapterConsultaBanco);
		gestorConsultas.agregarAdapter(adapterConsultaCGP);
	}
	
	@Test
	public void testSePersistePOI(){
		gestorConsultas.buscarPOIs("banco");
		Assert.assertEquals("Banco de la Plaza", RepositorioPOIsExternos.getInstance().buscarTodosLosBancos().get(0).getNombre());
	}
	
    @After
    public void tearDown(){
        RepositorioPOIsExternos.getInstance().borrarTodosLosPois();
        RepositorioPOIs.resetPOIs();
       if(mongod != null) mongod.stop();
    }

}
