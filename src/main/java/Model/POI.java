package Model;

import org.uqbar.geodds.Point;

import POIsExt.Comuna;
import POIsExt.RangoDeAtencion;
import javax.persistence.*;
import java.util.ArrayList;
import Converter.*;

import java.util.List;

import java.lang.String;


@Entity
@Table(name="POI")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoPOI", discriminatorType = DiscriminatorType.INTEGER)
// 1: ParadaaDeColectivo - 2: CGP - 3: Banco - 4: LocalComercial
public abstract class POI {

	//ATRIBUTOS
	@Id
	@GeneratedValue
	@Column(name="POI_ID")
	private long				id;
	
	@Column(name="UBICACION")
	@Convert(converter = PointConverter.class)
	private Point 					ubicacion;
	
	@Column(name="NOMBRE")
	private String 					nombre;
	
	@Column(name="DIRECCION")
	private String 					direccion;
	
	@ElementCollection
	@CollectionTable(name="TAG", joinColumns=@JoinColumn(name="POI_ID"))
	@Column(name="TAG")
	@Convert(converter = ListToStringConveter.class)
	protected List<String> 			tags; //Array de String que contienen todos los tags de busqueda libre - PALABRAS CLAVE
	
	@OneToMany
	private List<RangoDeAtencion>	listaDeRangosDeAtencion;
	
	@Column(name="COMUNA")
	@ManyToOne
	@JoinColumn(name = "COMUNA_NUMERO", foreignKey = @ForeignKey(name = "COMUNA_NUMERO_FK"))
	private Comuna					comuna;

	
	//CONSTRUCTOR

	public POI() { }

	public POI(Point unaUbicacion) {
		this.setUbicacion(unaUbicacion);
		this.instanciarNuevaColeccionDeTags(); //Para inicializar el ArrayList de Tags
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
	
	
	// Busqueda por texto libre
	public boolean detectarTagBuscado(String unTag){
		return tags.contains(unTag);
	}

	//Calculo de cercania
	
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getUbicacion().distance(unaUbicacion) * 1000 < this.cercaniaRequerida();
	}
	
	public double cercaniaRequerida(){ // Defino la cercania requerida standar
		return 500.0;
	}
	
	public boolean estaCercaDeUnPOI(POI unPOI){
		return this.estaAMenosDeXMetrosDeOtroPOI(unPOI, 500); //para pasar a metros
	}
	
	public void quitarTagAModificar(String tagAModificar){
		tags.removeIf(tag -> tag == tagAModificar);
	}
	
	// Es valido
	public boolean esValido(){
		return (this.estaGeolocalizado() && this.tieneNombre()); 
	}

	// Identificar si cumple los requisitos: si tiene el nombre especificado en el texto(para modificar locales comerciales)
	public Boolean tieneNombreYPalabrasEspecificadas(String unNombre,  String[] unasPalabras){
		if(nombre.equals(unNombre)){ return this.tienePalabrasEspecificadas(unasPalabras); }
		else return Boolean.FALSE;
	}

	public Boolean tienePalabrasEspecificadas(String[] unasPalabras) { return Boolean.FALSE; }

	public void actualizarPalabrasClaves(String[] unasPalabras){ }

	
	//GETTERS Y SETTERS

		void setUbicacion(Point unaUbicacion) {
			ubicacion = unaUbicacion;
			
		}
		
		public List<RangoDeAtencion> getListaDeRangosDeAtencion() {
			return listaDeRangosDeAtencion;
		}

		public void setListaDeRangosDeAtencion(List<RangoDeAtencion> unaListaDeRangosDeAtencion) {
			listaDeRangosDeAtencion =  unaListaDeRangosDeAtencion;
		}
		
		public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
			this.getListaDeRangosDeAtencion().add(unRangoDeAtencion);
		}
		

		public Point getUbicacion(){
			return ubicacion;
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

		public void setDireccion(String unaDireccion) {
			direccion = unaDireccion;
		}
		
		public void instanciarNuevaColeccionDeTags(){
			tags = new ArrayList<String>();
		}
		
		public List<String> getTags(){
			return tags;
		}
		
		public void addTag(String tag){//Agrega un tag al ArrayList
			tags.add(tag);
		}
		
		public void removeTag(String tag){
			tags.remove(tag);
		}
		
		public void setComuna(Comuna unaComuna){
			comuna = unaComuna;
		}
		
		public Comuna getComuna(){
			return this.comuna;
		}
		
		public long getID() {
			return id;
		}

		public void setID(long id) {
			this.id = id;
		}


}
