package Adapters;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ComponentesExternos.ComponenteExternoConsulta;
import ComponentesExternos.ComponenteExternoConsultaBancoStub;
import Master.POI;
import POIS.Banco;
import POIS.RangoDeAtencion;
import POIS.Servicio;

public class AdapterConsultaBanco implements AdapterConsulta {

	//CONSTRUCTOR
	
	public AdapterConsultaBanco(ComponenteExternoConsulta componente){
		this.setComponenteExternoConsulta(componente);
	}
	
	//ATRIBUTOS
	
	private ComponenteExternoConsulta componenteExterno;
	
	private void setComponenteExternoConsulta(ComponenteExternoConsulta componente){
		componenteExterno = componente;
	}
	
	private ComponenteExternoConsulta getComponenteExterno(){
		return componenteExterno;
	}
	
	//METODOS
	
	
	public List<POI> realizarConsulta(String unaConsulta) {
		JSONArray consultaSinAdaptar;
		List<POI> consultaAdaptada;
		consultaSinAdaptar = this.getComponenteExterno().realizarConsultaBanco(unaConsulta);
		consultaAdaptada = this.adaptarConsulta(consultaSinAdaptar);
		return consultaAdaptada;
	}
	
	public List<POI> adaptarConsulta(JSONArray consultaSinAdaptar){
		List<POI> consultaAdaptada = new ArrayList<POI>();
		List<JSONObject> conusltaSinAdaptarJson = (List<JSONObject>) consultaSinAdaptar; //un JSONArray = List
		conusltaSinAdaptarJson.forEach(jsonBanco -> consultaAdaptada.add(this.toBanco(jsonBanco)));
		return consultaAdaptada;
	}
	
	public Banco toBanco(JSONObject jsonBanco){
		String nombreBanco = (String) jsonBanco.get("banco");
		Double posX = (Double) jsonBanco.get("x");
		Double posY = (Double) jsonBanco.get("y");
		Point posicionDelBanco = new Point(posX,posY);
		List<Servicio> listaDeServiciosBanco = this.getListaDeServiciosDeJSON(jsonBanco);
		Banco banco = new Banco(posicionDelBanco);
		banco.setListaDeServicios(listaDeServiciosBanco);
		banco.setNombre(nombreBanco);
		return banco;
	}

	private List<Servicio> getListaDeServiciosDeJSON(JSONObject jsonBanco){
		List<Servicio> listaDeServicios = new ArrayList<Servicio>();
		List<String> arrayDeServicios = (List<String>) jsonBanco.get("servicios");
		RangoDeAtencion rangoDeAtencionBancario = new RangoDeAtencion(9.0,15.0,1,5);
		arrayDeServicios.forEach(stringServicio ->
		listaDeServicios.add(new Servicio(stringServicio,rangoDeAtencionBancario)));
		return listaDeServicios;
	}
	
}
