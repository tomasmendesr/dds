package Master;

import java.util.concurrent.atomic.AtomicInteger;

public class Identity {

	//CUANDO SE HACE EL TEST SE VA A TENER QUE INICIALIZAR LA VARIABLE DE CLASE
	
	//ATRIBUTOS
	
	private static AtomicInteger num = new AtomicInteger();
	
	//Geters y seters
	
	
	
	public static Integer getIdentity(){
		return num.get();
	}
	
	public static Integer getIdentityAndIncrement(){
		return num.getAndIncrement();		
	}
	
	public static void setIdentity(Integer numAux){
		num.set(numAux);
	}
	
	public static void initializeIdentity(){
		Identity.setIdentity(0);
	}
	
	
	
	
	
}
