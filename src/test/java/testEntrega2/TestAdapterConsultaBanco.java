package testEntrega2;


import java.util.List;


import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import Adapters.AdapterConsultaBanco;
import Adapters.PolygonAdapter;
import ComponentesExternos.ComponenteExternoConsultaBancoStub;
import Model.POI;
import POIsExt.Comuna;




public class TestAdapterConsultaBanco {
	
	private Comuna 	comuna8;
	private PolygonAdapter	zonaComuna8;
	private AdapterConsultaBanco adapterConsultaBanco;
	private ComponenteExternoConsultaBancoStub componenteExternoConsultaBancoStub;
	
	
	@Before
	public void init(){
		
		// Comuna 8
		comuna8 = new Comuna(8);
		zonaComuna8 = new PolygonAdapter();
		zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
		zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
		zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
		zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
		zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
		
		// Componente externo stub
		componenteExternoConsultaBancoStub = new ComponenteExternoConsultaBancoStub();
		
		// Adapter consulta banco
		adapterConsultaBanco = new AdapterConsultaBanco(componenteExternoConsultaBancoStub);
		
	}
	
	//TESTS DE COMPONENTE EXTERNO STUB
	
	@Test @SuppressWarnings("unchecked")
	public void componenteExternoHaceLaConsultaDeberiaTenerUnaListaCon1POI(){
		List<JSONObject> consultaBancoJson = componenteExternoConsultaBancoStub.realizarConsultaBanco("banco");
		Assert.assertEquals(1,consultaBancoJson.size());
	}
	
	@Test @SuppressWarnings("unchecked")
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
