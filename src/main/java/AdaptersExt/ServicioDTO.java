package Adapters;

import java.util.List;

public class ServicioDTO {
	
	//Consutructor
	
	public ServicioDTO(String nombreDelServicio, List<RangoServicioDTO> listaDeRangosDeServicio){
		this.setNombreDelServicio(nombreDelServicio);
		this.setListaDeRangosDeServicioDTO(listaDeRangosDeServicio);
	}
	
	//Atributos
	
	private String 						nombreDelServicio; 				//EJ "Atencion ciudadana"
	private List<RangoServicioDTO>		listaDeRangosDeServicioDTO;
	
	//Geters y seters
	
	public String getNombreDelServicio() {
		return nombreDelServicio;
	}
	public void setNombreDelServicio(String nombreDelServicio) {
		this.nombreDelServicio = nombreDelServicio;
	}
	public List<RangoServicioDTO> getListaDeRangosDeServicioDTO() {
		return listaDeRangosDeServicioDTO;
	}
	public void setListaDeRangosDeServicioDTO(List<RangoServicioDTO> listaDeRangosDeServicioDTO) {
		this.listaDeRangosDeServicioDTO = listaDeRangosDeServicioDTO;
	}		
	
	
}
