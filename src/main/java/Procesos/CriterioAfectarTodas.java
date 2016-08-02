package Procesos;

import java.util.List;

import Master.Terminal;

public class CriterioAfectarTodas extends TipoDeCriterio { // Afecta a todas las terminales
	
	//Metodos
	public List<Terminal> terminalesAModificar(){
		return todasLasTerminales;
	}


}
