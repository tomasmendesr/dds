package Master;

import java.util.concurrent.atomic.AtomicInteger;

public class Identity {

	//CUANDO SE HACE EL TEST SE VA A TENER QUE INICIALIZAR LA VARIABLE DE CLASE
	
	//ATRIBUTOS
	
	private static AtomicInteger num = new AtomicInteger(0);
	
	//Geters y seters
	
	public static Integer getIdentity(){
		return num.get();
	}
	
	public static Integer getIdentityAndIncrement(){
		return num.getAndIncrement();		
	}
	
	
	
	
	
}
