package ComponentesExternos;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import AdaptersExt.CentroDTO;

public class ComponenteExternoConsultaBancoStub implements ComponenteExternoConsulta{

	@SuppressWarnings("unchecked")
	public JSONArray realizarConsultaBanco(String unaConsulta) {
		JSONArray jsonArrayDeBancos = new JSONArray();
		JSONObject banco1 = new JSONObject();
		JSONArray jsonArrayDeServicios = new JSONArray();
		banco1.put("banco", "Banco de la Plaza");
		banco1.put("x",new Double(-35.9338322));
		banco1.put("y", new Double(72.348353));
		banco1.put("sucursal", "Avellaneda");
		banco1.put("Gerente", "Edgardo Bauza");
		jsonArrayDeServicios.add("cobro de cheques");
		jsonArrayDeServicios.add("depositos");
		jsonArrayDeServicios.add("extracciones");
		banco1.put("servicios", jsonArrayDeServicios);
		jsonArrayDeBancos.add(banco1);
		return jsonArrayDeBancos;		
	}

	public List<CentroDTO> realizarConsultaCGP(String unaConsulta) {return null;}

}
