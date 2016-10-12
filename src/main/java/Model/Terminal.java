package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ObserversTerminal.AccionesTerminal;
import POIsExt.Comuna;
import Repos.RepositorioPOIs;

import javax.persistence.*;

@Entity @Table(name = "terminales")
public class Terminal {

	//ATRIBUTOS
	@Id	@GeneratedValue	@Column(name="terminal_id")
	private Long id;
	
	@Transient
	private RepositorioPOIs	repositorioPOIs;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="observers_x_terminal", 
		joinColumns = {@JoinColumn(name="terminal_id", referencedColumnName="terminal_id") },
		inverseJoinColumns= { @JoinColumn(name="accion_id", referencedColumnName="accion_id") } )
	private List<AccionesTerminal> observers; 
    
    @Column(name="Snombre")
	private String nombre;
    
    @Transient
	private List<ResultadoBusqueda>	busquedas;
    
    @ManyToOne	@JoinColumn(name="comuna_numero")
	private Comuna comuna;
    
	//CONSTRUCTOR

    public Terminal() { }

	public Terminal(String nombre, RepositorioPOIs unRepositorioDePOIs){
		this.setRepositorioPOIs(unRepositorioDePOIs);
		this.setNombre(nombre);
		observers = new ArrayList<AccionesTerminal>();
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
		busquedas.add(unResultado);  //modificar la entrega de los observersd para que haga los reportes en base a mongo
		//RepoBusquedas.getInstance().guardarBusqueda(unResultado); hacer bien esto porque sino te rompe tests
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
	public void quitarAccion(AccionesTerminal observer){ // El observer es el encargado de realizar la accion
		observers.remove(observer); 
	}
	public void addObserver(AccionesTerminal observer){
		observers.add(observer);
	}


	//GETTERS Y SETTERS
	public Long getId() {  return id;  }

	public void setId(Long id) {  this.id = id;  }

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}

	public List<AccionesTerminal> getObservers() {
		return observers;
	}

	public void setObservers(List<AccionesTerminal> observers) {
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