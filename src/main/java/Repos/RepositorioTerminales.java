package Repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Model.Terminal;


public class RepositorioTerminales implements WithGlobalEntityManager {

	//Atributos
	private static RepositorioTerminales repositorioTerminales;
	
	private List<Terminal> terminales;
	
	//Constructor
		private RepositorioTerminales() {
			setTerminales(new ArrayList<Terminal>());
		}
			
	//Singleton 
	public static RepositorioTerminales getInstance() {
		if (repositorioTerminales == null) repositorioTerminales = new RepositorioTerminales();
			return repositorioTerminales;
		}
	
	public static void resetTerminales() {
		repositorioTerminales = null;
	}
	
	public List<Terminal> listar() {
		return entityManager()
				.createQuery("from Terminal", Terminal.class) 
				.getResultList();
	}
	
	public Terminal buscar(long id) {
		return entityManager().find(Terminal.class, id);
	}
	
	// Metodos
	public void addTerminal(Terminal terminal){
		terminales.add(terminal);
	}
	
	
	
	public void agregar(Terminal terminal){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(terminal);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<Terminal> buscarPorNombre(String nombre) {
		return entityManager() //
				.createQuery("from Terminal t where t.nombre like :nombre",Terminal.class) 
				.setParameter("nombre", "%" + nombre + "%") //
				.getResultList();
	}
	
	//Getters y setters
		public List<Terminal> getTerminales() {
			return terminales;
		}

		public void setTerminales(List<Terminal> terminales) {
			this.terminales = terminales;
		}

	
	
	
}
