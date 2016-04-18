package entrega1;

public class LocalComercial {

	//ATRIBUTOS
	
	private Rubro rubro;
	
	//GETERS Y SETERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	//METODOS
	
	public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getRubro().getRadioDeCercania().isInside(unaMaquina.getUbicacion());
	}
}
