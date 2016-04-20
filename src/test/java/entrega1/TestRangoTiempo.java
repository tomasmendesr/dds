package entrega1;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

//ESTE TEST ES PARA VER COMO SE MANEJA EL TEMA DEL TIEMPO

public class TestRangoTiempo {

	private DateTime dt = new DateTime();
	/*public enum Semana {
		1,2,3,4,5,6,7;
	}*/
	
	List<Integer> semana = Arrays.asList(1,2,3,4,5);
	List<Double> horas = Arrays.asList(18.0,19.0,20.0,21.0,22.0,23.0,24.0,0.0);
	
	
	@Test
	public void diaSem(){
		Assert.assertEquals(3,dt.getDayOfWeek());
	}
	
	@Test
	public void hora(){
		Assert.assertEquals(13.0,dt.getHourOfDay(),0);
	}
	
	@Test
	public void mes(){
		Assert.assertEquals(4, dt.getMonthOfYear());
	}
	
	@Test
	public void minutos(){
		Assert.assertEquals(18.0, dt.getMinuteOfHour(),0);
	}	//ver bien que onda el tema de los minutos
	
	@Test
	public void diaValido(){
		Assert.assertTrue(semana.contains(dt.getDayOfWeek()));
	}
	
	@Test
	public void horaValida(){
		Assert.assertTrue(horas.contains(dt.getHourOfDay()));
	}
	
	/*@Test
	public void horasMinutos(){
		Tiempo unTiempo = new Tiempo(1,13.0,52.0);
		Assert.assertEquals(13.52, unTiempo.getHorasMinutos(),0);
	}*/
	
}
