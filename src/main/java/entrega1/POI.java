package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class POI {

	//CONSTRUCTOR 
	
	public POI(Point unaUbicacion, Polygon unaComuna) {
		this.setUbicacion(unaUbicacion);
		this.setComuna(unaComuna);
		this.setTags(); //Para inicializar el Array 
	}	
	
	//ATRIBUTOS
	
	private Point 					ubicacion;
	private String 					nombre;
	private String 					direccion;
	private Polygon 				comuna;
	private ArrayList<String> 		tags; //Array de String que contienen todos los tags de busqueda libre
	private RangoDeAtencion		 	rangoDeTiempo; 
	private ArrayList<Servicio> 	servicios;

	
	//GETTERS Y SETTERS
	
	public Point getUbicacion(){
		return ubicacion;
	}
	
	public void setUbicacion(Point unaUbicacion){
		ubicacion = unaUbicacion;
	}
	
	public Polygon getComuna(){
		return comuna;
	}
	
	public void setComuna(Polygon unaComuna){
		comuna = unaComuna;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setTags(){ //Inicializa el ArrayList
		tags = new ArrayList<String>();
	}
	
	public void addTag(String tag){//Agrega un tag al ArrayList
		tags.add(tag);
	}
	
	public ArrayList<Servicio> getColeccionServicios(){
		return servicios;
	}
	
	public void setColeccionServicios(ArrayList<Servicio> coleccionDeServicios){
		servicios = coleccionDeServicios;
	}
	
	public void addServicio(Servicio unServicio){
		servicios.add(unServicio);
	}
	
	//METODOS
	
	public boolean estaAMenosDeXMetrosDeOtroPOI(POI otroPOI,double metros){
		return this.getUbicacion().distance(otroPOI.getUbicacion())*1000 < metros;	// Para pasarlo a metros
	}
	
	public boolean esPOIValido(){
		return this.estaGeolocalizado() && this.tieneNombre();
	}
	
	public boolean estaGeolocalizado(){
		return this.getUbicacion() != null;
	}
	
	public boolean tieneNombre(){
		return this.getNombre() != null;
	}
	
	public boolean detectarTagBuscado(String unTag){
		return tags.contains(unTag);
	}

	//Calculo de cercania

	public boolean estaCercaDeDispositivo(Dispositivo unDispositivo){
		return this.getUbicacion().distance(unDispositivo.getUbicacion())*1000 < this.cercaniaRequerida(); //para pasar a metros
	}
	
	public double cercaniaRequerida(){ // Defino la cercania requerida standar
		return 500.0;
	}
	
	//Calculo de disponibilidad
	
	public boolean estaDisponible(String unNombreDeServicio,Tiempo unTiempo){
		if(unNombreDeServicio == null){
			return this.algunServicioDisponible();
		} else {		
			return this.servicioDisponible(unNombreDeServicio,unTiempo);
		}
	}
	
	public boolean algunServicioDisponible(){
		Tiempo horaDelMomento = this.crearHoraDelMomento();	//Instancio la hora del momento
		return	this.getColeccionServicios().stream().
				anyMatch(servicio -> servicio.rangoDeAtencionValido(horaDelMomento));
	}
	
	public Tiempo crearHoraDelMomento(){
		int		diaSemana = new DateTime().getDayOfWeek();
		double 	hora = new DateTime().getHourOfDay();
		return new Tiempo(diaSemana,hora);
	}
	
	public boolean servicioDisponible(String unNombreDeServicio, Tiempo unTiempo){
		return this.getServicio(unNombreDeServicio).rangoDeAtencionValido(unTiempo);
	}
	
	public Servicio getServicio(String unNombreDeServicio){
		return this.getColeccionServicios().stream().
				filter(servicio -> servicio.getNombre() == unNombreDeServicio).
				collect(Collectors.toList()).get(0); //SE SUPONE QUE EL SERVICIO INGRESADO SIEMPRE ES VALIDO
	}

	
}
