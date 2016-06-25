package Master;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FechaActual {

	
	public static String getFecha(){
		Calendar fecha = new GregorianCalendar();
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		
        return dia + "/" + (mes +1) + "/" + anio;
	}
	
	public static String getHora(){
		Calendar fecha = new GregorianCalendar();
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
        return hora + ":" + minuto + ":" + hora + ":" + segundo;
	}
}
