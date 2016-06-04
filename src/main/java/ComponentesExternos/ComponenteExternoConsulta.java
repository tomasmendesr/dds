package ComponentesExternos;

import java.util.List;

import AdaptersExt.CentroDTO;
import AdaptersExt.JSON;

public interface ComponenteExternoConsulta {

	List<CentroDTO> realizarConsultaCGP(String unaConsulta);
	JSON			realizarConsultaBanco(String unaConsulta);
}
