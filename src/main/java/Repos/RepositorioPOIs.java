package Repos;

import Model.POI;
import Model.Terminal;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

public class RepositorioPOIs implements WithGlobalEntityManager {

	//ATRIBUTOS
	private static RepositorioPOIs repositorioPOIs;
	private List<POI> coleccionDePOIS;

	//CONSTRUCTOR
	private RepositorioPOIs() {
		coleccionDePOIS = new ArrayList<POI>();
	}

	//Singleton del Repo
	public static RepositorioPOIs getInstance() {
		if (repositorioPOIs == null) repositorioPOIs = new RepositorioPOIs();
		return repositorioPOIs;
	}

	public static void resetPOIs() {
		repositorioPOIs = null;
	}


	//Busqueda por texto libre

	public List<POI> buscarPorTextoLibre(String unTag) {
		return this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}

	//ABM POIs

	public void agregarPOI(POI unPOI) { coleccionDePOIS.add(unPOI); }

	public void quitarPOI(POI unPOI) {
		if(coleccionDePOIS.contains(unPOI)) coleccionDePOIS.remove(unPOI);
	}

	//Agregar y quitar lista de POIs (para locales comerciales)
	public void quitarListaDePOIs(List<POI> unaListaDePOIs){
		unaListaDePOIs.forEach(unPOI -> this.quitarPOI(unPOI));
	}

	public void agregarListaDePOIs(List<POI> unaListaDePOIs){
		unaListaDePOIs.forEach(unPOI -> this.agregarPOI(unPOI));
	}

	//Devolver Locales comerciales que cumplen requisitos para ser modificados
	public List<POI> devolverLocalesComercialesQueCumplenRequisitos(String nombre,  String[] unasPalabras) {
		List<POI> localesQueCumplenRequisitos = new ArrayList<POI>();
		localesQueCumplenRequisitos = coleccionDePOIS.stream().filter(unPOI -> (unPOI.tieneNombreYPalabrasEspecificadas(nombre, unasPalabras).equals(Boolean.TRUE))).collect(Collectors.toList());
		return localesQueCumplenRequisitos;
	}

	public Boolean contienePOISegunID(int id){
		return this.getColeccionDePOIS().stream().anyMatch(poi -> poi.getID() == id);
	}

	public List<POI> listar() {
		return entityManager()//
				.createQuery("from POI", POI.class) //
				.getResultList();
	}
	
	public List<POI> buscarPorPalabraClave(String tag){
		return entityManager() 
				.createQuery("from POI where tag like :tag",POI.class) 
				.setParameter("tag", "%" + tag + "%")
				.getResultList();
	}

	public POI buscar(long id) {
		return entityManager().find(POI.class, id);
	}
	
	public void eliminarPOI(long id) {
		entityManager().remove(this.buscar(id));
	}
	
	public List<POI> buscarPOIsPorTipo(String tipo){
		return entityManager() 
				.createQuery("from POI where tipoDePOI like :tipo",POI.class) 
				.setParameter("tipo", "%" + tipo + "%")
				.getResultList();
	}

	public void agregar(POI poi) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(poi);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

	public List<POI> buscarPorNombre(String nombre) {
		return entityManager() //
				.createQuery("from POI where nombre like :nombre",POI.class) //
				.setParameter("nombre", "%" + nombre + "%") //
				.getResultList();
	}
	
	public List<POI> buscarPOIsCercaDe(Terminal terminal) {
		return this.listar().stream()
				.filter(poi -> poi.estaCercaDe(terminal.getUbicacion()))
				.collect(Collectors.toList());
	}
	
	public List<POI> buscarPorTexto(String frase){
		String[] palabrasBuscadas = frase.split(" ");
		List<POI> poisEncontrados = new ArrayList<>();
		List<POI> pois = this.listar();
		pois.forEach(poi -> {
			for(int i = 0; i < palabrasBuscadas.length ; i++){
				if(poi.getTags().contains(palabrasBuscadas[i])) 
					if(!poisEncontrados.contains(poi)) // para que no se repitan
						poisEncontrados.add(poi);
			}
		});
		return poisEncontrados;
	}
	
	//GETTERS Y SETTERS
	public void setColeccionDePOIS(List<POI> unaColeccion) {
		coleccionDePOIS = unaColeccion;
	}

	public List<POI> getColeccionDePOIS() {
		return coleccionDePOIS;
	}


}