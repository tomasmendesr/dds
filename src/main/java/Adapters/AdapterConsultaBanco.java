package Adapters;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ComponentesExternos.ComponenteExternoConsulta;
import Model.POI;
import POIs.Banco;
import POIsExt.RangoDeAtencion;
import POIsExt.Servicio;
import Repos.RepositorioPOIsExternos;

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
		RepositorioPOIsExternos.getInstance().almacenaListaPois(consultaAdaptada);
		return consultaAdaptada;
	}
	
	@SuppressWarnings("unchecked")
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
		banco.setServicios(listaDeServiciosBanco);
		banco.setNombre(nombreBanco);
		return banco;
	}

	@SuppressWarnings("unchecked")
	private List<Servicio> getListaDeServiciosDeJSON(JSONObject jsonBanco){
		List<Servicio> listaDeServicios = new ArrayList<Servicio>();
		List<String> arrayDeServicios = (List<String>) jsonBanco.get("servicios");
		List<RangoDeAtencion> listaDeRangoDeAtencionBancario = this.instanciarRangoDeAtencionBancarioStub(); 
		arrayDeServicios.forEach(stringServicio ->
		listaDeServicios.add(new Servicio(stringServicio,listaDeRangoDeAtencionBancario)));
		return listaDeServicios;
	}
	
	private List<RangoDeAtencion> instanciarRangoDeAtencionBancarioStub(){
		List<RangoDeAtencion> rangoDeAtencionBancario = new ArrayList<RangoDeAtencion>();
		int horaDeAperturaBancaria = 10;
		int minutosDeAperturaBancaria = 0;
		int horaDeCierreBancario = 15;
		int minutosDeCierreBancario = 0;
		int lunes = 1;
		int martes = 2;
		int miercoles = 3;
		int jueves = 4;
		int viernes = 5;
		RangoDeAtencion rangoLunes = new RangoDeAtencion(lunes,horaDeAperturaBancaria,
				minutosDeAperturaBancaria,horaDeCierreBancario,minutosDeCierreBancario);
		RangoDeAtencion rangoMartes = new RangoDeAtencion(martes,horaDeAperturaBancaria,
				minutosDeAperturaBancaria,horaDeCierreBancario,minutosDeCierreBancario);
		RangoDeAtencion rangoMiercoles = new RangoDeAtencion(miercoles,horaDeAperturaBancaria,
				minutosDeAperturaBancaria,horaDeCierreBancario,minutosDeCierreBancario);
		RangoDeAtencion rangoJueves = new RangoDeAtencion(jueves,horaDeAperturaBancaria,
				minutosDeAperturaBancaria,horaDeCierreBancario,minutosDeCierreBancario);
		RangoDeAtencion rangoViernes = new RangoDeAtencion(viernes,horaDeAperturaBancaria,
				minutosDeAperturaBancaria,horaDeCierreBancario,minutosDeCierreBancario);
		rangoDeAtencionBancario.add(0, rangoLunes);
		rangoDeAtencionBancario.add(1, rangoMartes);
		rangoDeAtencionBancario.add(2, rangoMiercoles);
		rangoDeAtencionBancario.add(3, rangoJueves);
		rangoDeAtencionBancario.add(4, rangoViernes);
		return rangoDeAtencionBancario;
	}
	
}
