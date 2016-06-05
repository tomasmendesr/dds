package ComponentesExternos;

import java.util.List;

import org.json.simple.JSONArray;

import AdaptersExt.CentroDTO;
import AdaptersExt.JSON;

public interface ComponenteExternoConsulta {

	List<CentroDTO> realizarConsultaCGP(String unaConsulta);
	JSONArray			realizarConsultaBanco(String unaConsulta);
}
