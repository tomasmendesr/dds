package ProcesosExt;

import java.util.Date;

public class POIABajar {

	//CONSTRUCTOR

	public POIABajar(Date fecha, Integer idPOI){
		this.setFecha(fecha);
		this.setIdPOI(idPOI);
	}

	//ATRIBUTOS

	private Date fecha;
	private Integer idPOI;

	//METODOS

	public Integer getIdPOI() {
		return idPOI;
	}
	public void setIdPOI(Integer idPOI) {
		this.idPOI = idPOI;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
