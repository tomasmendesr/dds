package Model;

import java.util.List;

import Repos.RepositorioPOIs;

public class GestorConsultas {
	private RepositorioPOIs repositorioPOIs;
	
	public GestorConsultas(){
		setRepositorioPOIs(RepositorioPOIs.getInstance());
	}

	//Consultar Busqueda POIs con TiempoMax
	public List<POI> consultarPOIsXTiempoEstimado(String unaConsulta, double tiempoMax, Terminal unaTerminal){
		double tInicio = System.currentTimeMillis(), tFin, duracion;
		List<POI> poisEncontrados = repositorioPOIs.consultarPOIs(unaConsulta);
		tFin = System.currentTimeMillis();
		duracion = (tFin - tInicio);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,duracion,unaTerminal);
		resultadoBusqueda.setTiempoEstimadoBusqueda(tiempoMax);
		unaTerminal.getObservers().forEach(observer -> observer.realizarAccion(unaTerminal, resultadoBusqueda));
		return poisEncontrados;
	}
	
	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}
}
