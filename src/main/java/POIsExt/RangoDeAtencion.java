package POIsExt;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RangoDeAtencion {

	//Atributos
	@Id
	@GeneratedValue
	@Column(name="RANGO_DE_ATENCION_ID")
	private int id;
	@Column(name="NUMERO_DE_DIA_DE_LA_SEMANA")
	private int numeroDeDiaDeLaSemana; //EJ "1=Lunes, 2=Martes..."
	@Column(name="HORARIO_DESDE")
	private int horarioDesde; //EJ 9
	@Column(name="MINUTOS_DESDE")
	private int minutosDesde; //EJ 0
	@Column(name="HORARIO_HASTA")
	private int horarioHasta; //EJ 18
	@Column(name="MINUTOS_HASTA")
	private int minutosHasta; //EJ 0

	//Constructor
	public RangoDeAtencion() { }

	public RangoDeAtencion(int numeroDeDiaDeLaSemana,int horarioDesde,int minutosDesde, int horarioHasta, int minutosHasta){
		this.setNumeroDeDiaDeLaSemana(numeroDeDiaDeLaSemana);
		this.setHorarioDesde(horarioDesde);
		this.setMinutosDesde(minutosDesde);
		this.setHorarioHasta(horarioHasta);
		this.setMinutosHasta(minutosHasta);
	}
		
	//METODOS
	public boolean disponible(LocalDateTime unTiempo){
		return this.diaDisponible(unTiempo) && this.horarioDisponible(unTiempo);
	}
	
	public boolean horarioDisponible(LocalDateTime unTiempo){
		double	tiempoEnHorasMinutos = this.pasarAHorasMinutos(unTiempo);
		double  horarioDeApertura = this.getHorarioDeApertura();
		double  horarioDeCierre = this.getHorarioDeCierre();
		return	(horarioDeApertura <= tiempoEnHorasMinutos)
				&& (tiempoEnHorasMinutos <= horarioDeCierre);
	}
	
	public boolean diaDisponible(LocalDateTime unTiempo){
		int unDiaDeLaSemana = unTiempo.getDayOfWeek().getValue();
		return unDiaDeLaSemana == this.getNumeroDeDiaDeLaSemana();
	}
	
	public double pasarAHorasMinutos(LocalDateTime unTiempo){
		double horas = unTiempo.getHour();
		double minutos = unTiempo.getMinute();
		return horas + (minutos/100);
	}
	
	public double getHorarioDeApertura(){
		double horaDesde = this.getHorarioDesde();
		double minutosDesde = this.getMinutosDesde();
		return horaDesde + (minutosDesde/100);
	}
	
	public double getHorarioDeCierre(){
		double horaHasta = this.getHorarioHasta();
		double minutosHasta = this.getMinutosHasta();
		return horaHasta + (minutosHasta/100);
	}
	
	public List<RangoDeAtencion> rangoDeAtencionBancario(){
		RangoDeAtencion rangoBancarioLunes = new RangoDeAtencion(1,10,0,15,0);
		RangoDeAtencion rangoBancarioMartes = new RangoDeAtencion(2,10,0,15,0);
		RangoDeAtencion rangoBancarioMiercoles = new RangoDeAtencion(3,10,0,15,0);
		RangoDeAtencion rangoBancarioJueves = new RangoDeAtencion(4,10,0,15,0);
		RangoDeAtencion rangoBancarioViernes = new RangoDeAtencion(5,10,0,15,0);
		List<RangoDeAtencion> rangoDeAtencionBancario = new ArrayList<RangoDeAtencion>();
		rangoDeAtencionBancario.add(0, rangoBancarioLunes);		rangoDeAtencionBancario.add(1, rangoBancarioMartes);
		rangoDeAtencionBancario.add(2, rangoBancarioMiercoles);	rangoDeAtencionBancario.add(3, rangoBancarioJueves);
		rangoDeAtencionBancario.add(4, rangoBancarioViernes);
		return rangoDeAtencionBancario;
	}

	//Getters y setters
	public Integer getId() {  return id;  }
	public void setId(Integer id) {  this.id = id;  }
	public int getNumeroDeDiaDeLaSemana() {
		return numeroDeDiaDeLaSemana;
	}
	public void setNumeroDeDiaDeLaSemana(int numeroDeDiaDeLaSemana) {
		this.numeroDeDiaDeLaSemana = numeroDeDiaDeLaSemana;
	}
	public int getHorarioDesde() {
		return horarioDesde;
	}
	public void setHorarioDesde(int horarioDesde) {
		this.horarioDesde = horarioDesde;
	}
	public int getMinutosDesde() {
		return minutosDesde;
	}
	public void setMinutosDesde(int minutosDesde) {
		this.minutosDesde = minutosDesde;
	}
	public int getHorarioHasta() {
		return horarioHasta;
	}
	public void setHorarioHasta(int horarioHasta) {
		this.horarioHasta = horarioHasta;
	}
	public int getMinutosHasta() {
		return minutosHasta;
	}
	public void setMinutosHasta(int minutosHasta) {
		this.minutosHasta = minutosHasta;
	}
}
