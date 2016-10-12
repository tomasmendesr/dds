package ObserversTerminal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
@Table(name="acciones")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType = DiscriminatorType.INTEGER)
// 1: Almacenar - 2: Notificar - 3: ReporteParcial - 4: ReporteFecha - 5: ReporteTotales
public abstract class AccionesTerminal {
	
	//Atributos
	@Id
	@GeneratedValue
	@Column(name ="accion_id")
	private Long id;

	//Metodos
	public abstract void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda);
	
	//Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
}
