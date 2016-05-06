package entrega1;

public class UsuarioAdministrador extends Usuario {

	//CONSTRUCTOR 
	
	public UsuarioAdministrador(Mapa unMapa) {
		super(unMapa);
	}

	//ATRIBUTOS
	
	
	//METODOS

	public void agregarPOI(POI unPOI) {
		this.getMapa().addPOI(unPOI);
	}

	public void quitarPOI(POI unPOI) {
		this.getMapa().removePOI(unPOI);
	}
	
	public void modificarPOI(POI unPOI) {}

}
