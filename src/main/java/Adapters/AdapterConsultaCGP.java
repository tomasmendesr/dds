package Adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import AdaptersExt.CentroDTO;
import AdaptersExt.ServicioDTO;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Master.POI;
import Master.RepositorioComunas;
import POIS.CGP;
import POIS.Comuna;
import POIS.Servicio;

public class AdapterConsultaCGP implements AdapterConsulta {

	//CONSTRUCTOR
	
	public AdapterConsultaCGP(ComponenteExternoConsultaCGPStub componente, RepositorioComunas repoComunas){
		this.setComponenteExterno(componente);
		this.setRepositorioComunas(repoComunas);
	}
	
	//ATRIBUTOS
	
	private ComponenteExternoConsultaCGPStub	componenteExternoCGP;
	private RepositorioComunas					repositorioComunas;
	
	//GETERS Y SETERS
	
	public ComponenteExternoConsultaCGPStub getComponenteExterno(){
		return componenteExternoCGP;
	}
	
	public void setComponenteExterno(ComponenteExternoConsultaCGPStub componente){
		componenteExternoCGP = componente;
	}

	public RepositorioComunas getRepositorioComunas() {
		return repositorioComunas;
	}

	public void setRepositorioComunas(RepositorioComunas repositorioComunas) {
		this.repositorioComunas = repositorioComunas;
	}
	
	//METODOS

	public List<POI> realizarConsulta(String unaConsulta) {
		List<CentroDTO> consultaSinAdaptar = new ArrayList<CentroDTO>();
		List<POI> consultaAdaptada;
		consultaSinAdaptar = this.getComponenteExterno().realizarConsultaCGP(unaConsulta);
		consultaAdaptada = this.adaptarConsulta(consultaSinAdaptar);
		return consultaAdaptada;
	}
	
	public List<POI> adaptarConsulta(List<CentroDTO> consultaSinAdaptar){
		List<POI> consultaAdaptada = new ArrayList<POI>();
		consultaSinAdaptar.forEach(centroDTO -> consultaAdaptada.add(this.toCGP(centroDTO)));
		return consultaAdaptada;
	}
	
	public CGP toCGP(CentroDTO unCentroDTO){
		CGP nuevoCGP = new CGP(new Point(1,1));	//cuando tenga la api de google maps solucionar
 		nuevoCGP.setNombre("CGP de ".concat(unCentroDTO.getDireccionCGP()));
 		nuevoCGP.setComuna(this.setComunaDeCGP(unCentroDTO.getNumeroDeComuna()));
 		this.setListaDeServiciosA(nuevoCGP,unCentroDTO);
		return nuevoCGP;
	}
	
	private Comuna setComunaDeCGP(Integer unNumeroDeComuna){return null;}
	
	private void setListaDeServiciosA(CGP unCGP, CentroDTO unCentroDTO){
		List<Servicio> nuevaListaDeServicios = new ArrayList<Servicio>();
		unCentroDTO.getListaDeServiciosDTO().forEach(servicioDTO -> nuevaListaDeServicios.add(this.toServicioCGP(servicioDTO)));
		unCGP.setColeccionServicios(nuevaListaDeServicios);
	}
	
	private Servicio toServicioCGP(ServicioDTO unServicioDTO){return null;}
}
