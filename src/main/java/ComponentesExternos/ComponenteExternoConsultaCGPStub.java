package ComponentesExternos;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import AdaptersExt.CentroDTO;
import AdaptersExt.ServicioDTO;
import POIsExt.RangoDeAtencion;




public class ComponenteExternoConsultaCGPStub implements ComponenteExternoConsulta{

	public List<CentroDTO> realizarConsultaCGP(String unaConsulta){
		List<CentroDTO> listaStubResultante = new ArrayList<CentroDTO>();
		//Instancio centroDTO
		CentroDTO centroDTOStub = new CentroDTO();
		centroDTOStub.setDireccionCGP("Av Escalada 3100");
		centroDTOStub.setPosX(-34.6672);
		centroDTOStub.setPosY(-58.4669);
		centroDTOStub.setNumeroDeComuna(8);
		List<ServicioDTO> listaDeServiciosDTO = new ArrayList<ServicioDTO>();	

			//Instancio servicios dentro del centroDTO
			RangoDeAtencion rangoLunesServicioAsesoria = new RangoDeAtencion(1,9,0,18,0);
			RangoDeAtencion rangoMartesServicioAsesoria = new RangoDeAtencion(2,9,0,18,0);
			RangoDeAtencion rangoMiercolesServicioAsesoria = new RangoDeAtencion(3,9,0,18,0);
			List<RangoDeAtencion> rangoDeAtencionAsesoria = new ArrayList<RangoDeAtencion>();
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
