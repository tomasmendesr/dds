package Model;

import java.util.ArrayList;
import java.util.List;


import Adapters.AdapterConsulta;
import Repos.RepositorioPOIs;
import Repos.RepositorioPOIsExternos;

public class GestorConsultas  {
	private RepositorioPOIs repositorioPOIs;
	private List<AdapterConsulta> adapters;
	
	public GestorConsultas(){
		setRepositorioPOIs(RepositorioPOIs.getInstance());
		adapters = new ArrayList<AdapterConsulta>();
	}

	//Consultar Busqueda POIs con TiempoMax
	public List<POI> consultarPOIsXTiempoEstimado(String unaConsulta, double tiempoMax, Terminal unaTerminal){
		double tInicio = System.currentTimeMillis(), tFin, duracion;
		List<POI> poisEncontrados = this.buscarPOIs(unaConsulta);
		tFin = System.currentTimeMillis();
		duracion = (tFin - tInicio);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,duracion,unaTerminal);
		resultadoBusqueda.setTiempoEstimadoBusqueda(tiempoMax);
		unaTerminal.getObservers().forEach(observer -> observer.realizarAccion(unaTerminal, resultadoBusqueda));		
		return poisEncontrados;
	}
	
	public List<POI> buscarPOIs(String unaConsulta){
		List<POI> poisEncontrados = new ArrayList<POI>();
		if(!adapters.isEmpty()){
			List<POI> poisEncontradosEnServicioExterno = this.buscarEnServiciosExternos(unaConsulta);
			poisEncontrados.addAll(poisEncontradosEnServicioExterno);
		}
		List<POI> poisEncontradosEnServidorLocal = this.buscarPorTextoLibre(unaConsulta);
		poisEncontrados.addAll(poisEncontradosEnServidorLocal);
		return poisEncontrados;
	}
	
	public List<POI> buscarEnServiciosExternos(String unaConsulta){
		List<POI> poisEncontrados = new ArrayList<POI>();
		RepositorioPOIsExternos repositorioPOIsExternos = RepositorioPOIsExternos.getInstance();
		poisEncontrados.addAll(repositorioPOIsExternos.buscarPoisPorConsulta(unaConsulta));
		if(poisEncontrados.isEmpty()){
			adapters.forEach(adapter -> poisEncontrados.addAll(adapter.realizarConsulta(unaConsulta)));
		}
		return poisEncontrados;
	}
	
	public List<POI> buscarPorTextoLibre(String unaConsulta){
		return RepositorioPOIs.getInstance().buscarPorTextoLibre(unaConsulta);
	}
	
	
	//Getters y setters
	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}
	
	public void agregarAdapter(AdapterConsulta unAdapter){
		adapters.add(unAdapter);
	}
	
	public void quitarAdapter(AdapterConsulta unAdapter){
		adapters.remove(unAdapter);
	}
}
