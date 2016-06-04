package AdaptersExt;

public class RangoServicioDTO {
	
	//Constructor
	
	public RangoServicioDTO(int numeroDeDiaDeLaSemana,int horarioDesde,int minutosDesde, int horarioHasta, int minutosHasta){
		this.setNumeroDeDiaDeLaSemana(numeroDeDiaDeLaSemana);
		this.setHorarioDesde(horarioDesde);
		this.setMinutosDesde(minutosDesde);
		this.setHorarioHasta(horarioHasta);
		this.setMinutosHasta(minutosHasta);
	}
	
	//Atributos
	
	private int numeroDeDiaDeLaSemana; //EJ "1=Lunes, 2=Martes..."
	private int horarioDesde; //EJ 9
	private int minutosDesde; //EJ 0
	private int horarioHasta; //EJ 18
	private int minutosHasta; //EJ 0
	
	//Geters y seters
	
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
