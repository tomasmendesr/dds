package AdaptersExt;

import java.util.List;

import POIsExt.RangoDeAtencion;

public class ServicioDTO {
	
	//Consutructor
	
	public ServicioDTO(String nombreDelServicio, List<RangoDeAtencion> listaDeRangosDeServicio){
		this.setNombreDelServicio(nombreDelServicio);
		this.setListaDeRangosDeServicioDTO(listaDeRangosDeServicio);
	}
	
	//Atributos
	
	private String 						nombreDelServicio; 				//EJ "Atencion ciudadana"
	private List<RangoDeAtencion>		listaDeRangosDeServicioDTO;
	
	//Geters y seters
	
	public String getNombreDelServicio() {
		return nombreDelServicio;
	}
	public void setNombreDelServicio(String nombreDelServicio) {
		this.nombreDelServicio = nombreDelServicio;
	}
	public List<RangoDeAtencion> getListaDeRangosDeServicioDTO() {
		return listaDeRangosDeServicioDTO;
	}
	public void setListaDeRangosDeServicioDTO(List<RangoDeAtencion> listaDeRangosDeServicioDTO) {
		this.listaDeRangosDeServicioDTO = listaDeRangosDeServicioDTO;
	}		
	
	
}
