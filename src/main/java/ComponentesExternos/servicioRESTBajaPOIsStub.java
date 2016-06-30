package ComponentesExternos;

import java.time.LocalDateTime;
import java.util.Date;

import org.json.simple.JSONObject;

public class servicioRESTBajaPOIsStub{

	public JSONObject getPOIs(){
		JSONObject poisABajar = new JSONObject();
		Integer poiID = 2;
		Date fecha = new Date();
		fecha.setDate(1);
		fecha.setHours(3);
		fecha.setMinutes(0);
		fecha.setMonth(7);
		fecha.setYear(116);		
		poisABajar.put(poiID,fecha);
		return poisABajar;		
	}
	
}
