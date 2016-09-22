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

import Master.ResultadoBusqueda;
import Master.Terminal;

@Entity
@Table(name="Funcionalidad")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="subclass", discriminatorType = DiscriminatorType.STRING)
public abstract class FuncionalidadExtraTerminal {
	
	//Atributos
	@Id
	@GeneratedValue
	@Column(name ="funcionalidad_id")
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
