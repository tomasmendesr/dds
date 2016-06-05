package ComponentesExternos;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import AdaptersExt.CentroDTO;
import AdaptersExt.JSON;
import AdaptersExt.RangoServicioDTO;
import AdaptersExt.ServicioDTO;
import POIS.CGP;
import POIS.Servicio;

public class ComponenteExternoConsultaCGPStub implements ComponenteExternoConsulta{

	public List<CentroDTO> realizarConsultaCGP(String unaConsulta){
		List<CentroDTO> listaStubResultante = new ArrayList<CentroDTO>();
		//Instancio centroDTO
		CentroDTO centroDTOStub = new CentroDTO();
		centroDTOStub.setDireccionCGP("Av Escalada 3100");
		centroDTOStub.setNumeroDeComuna(8);
		List<ServicioDTO> listaDeServiciosDTO = new ArrayList<ServicioDTO>();	

			//Instancio servicios dentro del centroDTO
			RangoServicioDTO rangoLunesServicioAsesoria = new RangoServicioDTO(1,9,0,18,0);
			RangoServicioDTO rangoMartesServicioAsesoria = new RangoServicioDTO(2,9,0,18,0);
			RangoServicioDTO rangoMiercolesServicioAsesoria = new RangoServicioDTO(3,9,0,18,0);
			List<RangoServicioDTO> rangoDeAtencionAsesoria = new ArrayList<RangoServicioDTO>();
			rangoDeAtencionAsesoria.add(rangoLunesServicioAsesoria);
			rangoDeAtencionAsesoria.add(rangoMartesServicioAsesoria);
			rangoDeAtencionAsesoria.add(rangoMiercolesServicioAsesoria);
			ServicioDTO asesoriaLegal = new ServicioDTO("Asesoria Legal",rangoDeAtencionAsesoria);
		
		listaDeServiciosDTO.add(asesoriaLegal);
		centroDTOStub.setListaDeServiciosDTO(listaDeServiciosDTO); //Seteo la lista de ServicioDTO
		listaStubResultante.add(centroDTOStub);						
		return listaStubResultante;
	}
	
	public JSONArray realizarConsultaBanco(String unaConsulta){return null;}
}
