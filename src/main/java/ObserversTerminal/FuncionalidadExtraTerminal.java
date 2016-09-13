package ObserversTerminal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Master.ResultadoBusqueda;
import Master.Terminal;

@Entity
@Table(name="FUNCIONALIDAD")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="subclass", discriminatorType = DiscriminatorType.STRING)
public abstract class FuncionalidadExtraTerminal {
	
	@Id
	@GeneratedValue
	private Integer id;

	public abstract void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda);
	
	
}
