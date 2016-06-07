package Adapters;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import AdaptersExt.CentroDTO;
import AdaptersExt.ServicioDTO;
import ComponentesExternos.ComponenteExternoConsultaCGPStub;
import Master.POI;
import POIs.CGP;
import POIsExt.RangoDeAtencion;
import POIsExt.Servicio;

public class AdapterConsultaCGP implements AdapterConsulta {

	//CONSTRUCTOR
	
	public AdapterConsultaCGP(ComponenteExternoConsultaCGPStub componente){
		this.setComponenteExterno(componente);
	}
	
	//ATRIBUTOS
	
	private ComponenteExternoConsultaCGPStub	componenteExternoCGP;
	
	//GETERS Y SETERS
	
	public ComponenteExternoConsultaCGPStub getComponenteExterno(){
		return componenteExternoCGP;
	}
	
	public void setComponenteExterno(ComponenteExternoConsultaCGPStub componente){
		componenteExternoCGP = componente;
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
		CGP nuevoCGP = new CGP(new Point(unCentroDTO.getPosX(),unCentroDTO.getPosY()));
 		nuevoCGP.setNombre("CGP de ".concat(unCentroDTO.getDireccionCGP()));
 		List<Servicio> serviciosAdaptados = this.adaptarServiciosDTO(unCentroDTO.getListaDeServiciosDTO());
 		nuevoCGP.setListaDeServicios(serviciosAdaptados);
		return nuevoCGP;
	}
	
	// HACER REFACTOR DE LA FORMA EN LA QUE SE MANEJAN LOS SERVICIOS Y LOS RANGOS DE ATENCION
	// PAJA MAL PERO BUEN, ES MEJOR LA NUEVA FORMA
	
	private List<Servicio> adaptarServiciosDTO(List<ServicioDTO> listaDeServiciosDTO){
		List<Servicio> listaServiciosAdaptados = new ArrayList<Servicio>();
		listaDeServiciosDTO.forEach(servicioDTO -> listaServiciosAdaptados.add(this.toServicio(servicioDTO)));
		return listaServiciosAdaptados;
	}
	
	private Servicio toServicio(ServicioDTO unServicioDTO){
		List<RangoDeAtencion> listaDeRangosDeAtencion = unServicioDTO.getListaDeRangosDeServicioDTO();
		String nombreServicio = unServicioDTO.getNombreDelServicio();
		return new Servicio(nombreServicio,listaDeRangosDeAtencion); //La forma en la que manejamos los rangos de atencion es la misma 
	}
	

}
