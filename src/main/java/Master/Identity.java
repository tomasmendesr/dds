package Master;

public class Identity {

	//CUANDO SE HACE EL TEST SE VA A TENER QUE INICIALIZAR LA VARIABLE DE CLASE
	
	//ATRIBUTOS
	
	private static Integer num;
	
	//Geters y seters
	
	public static Integer getIdentityAndIncrement(){
		if(num == null) Identity.setIdentity(0);
		Integer aux = num;
		Identity.setIdentity(num++);
		return aux;
	}
		
	public static Integer getIdentity(){
		if(num == null) Identity.setIdentity(0);
		return num;
	}
	
	public static void setIdentity(Integer numAux){
		num = numAux;
	}
	
	
	
	
	
}
