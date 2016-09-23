package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ObserversTerminal.FuncionalidadExtraTerminal;
import POIsExt.Comuna;
import Repos.RepositorioPOIs;

import javax.persistence.*;

@Entity
public class Terminal {

	//ATRIBUTOS
	@Id	@GeneratedValue	@Column(name="TERMINAL_ID")
	private Long									id;
	
	@Transient
	private RepositorioPOIs			 				repositorioPOIs;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "funcionalidad_x_terminal", joinColumns = {
			@JoinColumn(name = "TERMIJNAL_ID", nullable = false) },
			inverseJoinColumns = { @JoinColumn(name = "FUNCIONALIDAD_ID",
					nullable = false) })
	private List<FuncionalidadExtraTerminal>		observers;
    
    @Column(name="NOMBRE")
	private String									nombreTerminal;
    
    //Mapear con NOSQL
	private List<ResultadoBusqueda>					busquedas;
    
    @Column(name="COMUNA")@ManyToOne
	@JoinColumn(name = "COMUNA_NUMERO", foreignKey = @ForeignKey(name = "COMUNA_NUMERO_FK"))
	private Comuna 									comuna;
		
	//CONSTRUCTOR
    public Terminal() { }

	public Terminal(String nombre, RepositorioPOIs unRepositorioDePOIs){
		this.setRepositorioPOIs(unRepositorioDePOIs);
		this.setNombreTerminal(nombre);
		observers = new ArrayList<FuncionalidadExtraTerminal>();
		busquedas = new ArrayList<ResultadoBusqueda>();
	}
	

	
	//METODOS
		
	//Consultar Busqueda POIs con TiempoMax
	public List<POI> consultarPOIsXTiempoEstimado(String unaConsulta, double tiempoMax){
		double tInicio = System.currentTimeMillis(), tFin, duracion;
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = repositorioPOIs.consultarPOIs(unaConsulta);
		tFin = System.currentTimeMillis();
		duracion = (tFin - tInicio);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,duracion,this);
		resultadoBusqueda.setTiempoEstimadoBusqueda(tiempoMax);
		observers.forEach(observer -> observer.realizarAccion(this, resultadoBusqueda));
		this.guardarBusqueda(resultadoBusqueda); // Para hacer el reporte por usuarios
		return poisEncontrados;
	}
	
	public void guardarBusqueda(ResultadoBusqueda unResultado){
		busquedas.add(unResultado);
	}
	
	public Integer obtenerResultadosTotales(){ // Obtengo la suma de la lista creada en resultadosTotales()
		return this.resultadosTotales().stream().reduce(0, (a,b) -> a + b);
	}
	
	public List<Integer> resultadosTotales(){ // Obtengo una lista con todas las cantidades de resultados de las busquedas
		return this.getResultadosBusqueda().stream()
		.map(resultado -> resultado.getCantidadDeResultados())
		.collect(Collectors.toList());
	}
	
	public Boolean recibirMail(){ // Creo este metodo para poder testearlo mientras tanto
		return true;
	}
	
	
	// Proceso 3: Agregar o quitar acciones
	public void quitarAccion(FuncionalidadExtraTerminal observer){ // El observer es el encargado de realizar la accion
		observers.remove(observer); 
	}
	public void addObserver(FuncionalidadExtraTerminal observer){
		observers.add(observer);
	}


	//GETTERS Y SETTERS
	public Long getId() {  return id;  }

	public void setId(Long id) {  this.id = id;  }

	public String getNombreTerminal(){
		return nombreTerminal;
	}

	public void setNombreTerminal(String nombre){
		nombreTerminal = nombre;
	}

	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}

	public List<FuncionalidadExtraTerminal> getObservers() {
		return observers;
	}

	public void setObservers(List<FuncionalidadExtraTerminal> observers) {
		this.observers = observers;
	}

	public List<ResultadoBusqueda> getResultadosBusqueda(){
		return busquedas;
	}

	public void setComuna(Comuna comuna){
		this.comuna = comuna;
	}

	public Comuna getComuna(){
		return comuna;
	}


}