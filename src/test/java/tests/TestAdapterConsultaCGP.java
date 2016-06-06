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
import AdaptersExt.CentroDTO;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Master.POI;
import Master.RepositorioPOIs;
import POIs.Banco;
import POIs.CGP;
<<<<<<< HEAD
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
=======
import POIs.Comuna;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIs.Rubro;
>>>>>>> c7682eb707c84e3aacfad3e1908058e96b114003

public class TestAdapterConsultaCGP {

	private Comuna comuna8;
	private CGP cgp;
	private Polygon	zonaComuna8;
	private RepositorioPOIs repoPOIs;
	private AdapterConsultaCGP adapterConsultaCGP;
	private ComponenteExternoConsultaCGPStub componenteExternoConsultaCGP;
	
	
	@Before
	public void init(){
		
		adapterConsultaCGP = Mockito.mock(AdapterConsultaCGP.class);
		componenteExternoConsultaCGP = Mockito.mock(ComponenteExternoConsultaCGPStub.class);
		
		// Comuna 8
		comuna8 = new Comuna(8);
		zonaComuna8 = new Polygon();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
					
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.setComuna(comuna8);
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
		
		//Agrega POIs al mapa
		repoPOIs = new RepositorioPOIs();
		repoPOIs.agregarPOI(cgp);
	}
	
	
	
}
