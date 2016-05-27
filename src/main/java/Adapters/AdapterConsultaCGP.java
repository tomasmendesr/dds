package Adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import Master.POI;
import Master.RepositorioComunas;
import POIS.CGP;

public class AdapterConsultaCGP implements AdapterConsulta {

	//Constructor 
	
	public AdapterConsultaCGP(RepositorioComunas repositorioComunas){
		this.setRepositorioComunas(repositorioComunas);
	}
	
	//Atributos
	
	private RepositorioComunas repositorioComunas;	//Lo necesitamos para setearle la comuna al nuevo CGP

	public RepositorioComunas getRepositorioComunas() {
		return repositorioComunas;
	}

	public void setRepositorioComunas(RepositorioComunas repositorioComunas) {
		this.repositorioComunas = repositorioComunas;
	}

	//Metodos
	
	@Override
	public List<POI> realizarConsulta(String unaConsulta) {
		List<CentroDTO> centroDTOEncontrados = new ArrayList<CentroDTO>();
		List<POI> cgpsEncontrados = new ArrayList<POI>();
		centroDTOEncontrados.addAll(this.componenteExternoRealizarConsulta(unaConsulta));
		cgpsEncontrados = this.adaptarListaComponenteExternoAListaCGP(centroDTOEncontrados);
		return cgpsEncontrados;
		}
	
	public List<CentroDTO> componenteExternoRealizarConsulta(String unaConsulta){return null;};

	public List<POI> adaptarListaComponenteExternoAListaCGP(List<CentroDTO> listaDeCentroDTO){
		List<POI> cgpsEncontradosAux = new ArrayList<POI>();
		listaDeCentroDTO.forEach(centroDTO -> cgpsEncontradosAux.add(this.adaptarACGP(centroDTO)));
		return cgpsEncontradosAux;
	}
	
	public POI adaptarACGP(CentroDTO unCentroDTO){
		CGP nuevoCGP = new CGP(this.extraerPointDeUnCentro(unCentroDTO));	//Geolocalizo el CGP
		this.agregarNombreACGP(nuevoCGP,unCentroDTO);
		this.agregarDireccionACGP(nuevoCGP,unCentroDTO);
		this.agregarServiciosACGP(nuevoCGP,unCentroDTO);
		this.agregarComunaACGP(nuevoCGP,unCentroDTO);
		return nuevoCGP;
	}
	
	public Point extraerPointDeUnCentro(CentroDTO unCentroDTO){
		return null;
	}
	
	public void agregarNombreACGP(CGP unCGP, CentroDTO unCentroDTO){
		unCGP.setNombre(unCentroDTO.getNumeroDeComuna().toString());
	}
	public void agregarDireccionACGP(CGP unCGP, CentroDTO unCentroDTO){
		unCGP.setDireccion(unCentroDTO.getDireccionCGP());
	}
	public void agregarServiciosACGP(CGP unCGP, CentroDTO unCentroDTO){
		
	}
	
	public void agregarComunaACGP(CGP unCGP, CentroDTO unCentroDTO){
		unCGP.setComuna(this.getRepositorioComunas().getListaDeComunas().stream().
		filter(comuna -> comuna.getNumeroDeComuna() == unCentroDTO.getNumeroDeComuna())
		.collect(Collectors.toList()).get(0));
	}
}
