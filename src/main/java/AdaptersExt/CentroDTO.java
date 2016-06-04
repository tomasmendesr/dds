package Adapters;

import java.util.List;

public class CentroDTO {

	//Consutructor
	
	
	
	//Atributos
	
	private Integer 					numeroDeComuna;			//EJ "3"
	private String					zonasQueIncluye;		//EJ "Balvanera, San Cristobal"
	private String					nombreDelDirector;		
	private String					direccionCGP;			//EJ "Junin 521" 
	private List<ServicioDTO>		listaDeServiciosDTO;	//Array de servicios que contiene
	
	//Geters y seters
	
	public Integer getNumeroDeComuna() {
		return numeroDeComuna;
	}
	public void setNumeroDeComuna(Integer numeroDeComuna) {
		this.numeroDeComuna = numeroDeComuna;
	}
	public String getZonasQueIncluye() {
		return zonasQueIncluye;
	}
	public void setZonasQueIncluye(String zonasQueIncluye) {
		this.zonasQueIncluye = zonasQueIncluye;
	}
	public String getNombreDelDirector() {
		return nombreDelDirector;
	}
	public void setNombreDelDirector(String nombreDelDirector) {
		this.nombreDelDirector = nombreDelDirector;
	}
	public String getDireccionCGP() {
		return direccionCGP;
	}
	public void setDireccionCGP(String direccionCGP) {
		this.direccionCGP = direccionCGP;
	}
	public List<ServicioDTO> getListaDeServiciosDTO() {
		return listaDeServiciosDTO;
	}
	public void setListaDeServiciosDTO(List<ServicioDTO> listaDeServiciosDTO) {
		this.listaDeServiciosDTO = listaDeServiciosDTO;
	}
}
