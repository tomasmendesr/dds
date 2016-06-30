package testEntrega4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Master.Identity;
import POIs.Banco;
import POIs.CGP;
import POIs.ParadaDeColectivo;

public class TestIdentity {

	@Before
	public void init(){
		Identity.initializeIdentity();
	}
	
	@Test
	public void identityEmpiezaEn0(){
		Assert.assertEquals(0,Identity.getIdentity(),0);
	}
	
	@Test
	public void identityVale1CuandoSeQuiereIncrementar(){
		Assert.assertEquals(0, Identity.getIdentityAndIncrement(),0);
		Assert.assertEquals(1, Identity.getIdentity(),0);
	}
	
	/*@Test
	public void poiTieneUnIDQueVale0(){
		Assert.assertEquals(1, new ParadaDeColectivo(null).getID() ,0);
	}
		
	@Test
	public void tresPOIsDistitntosTienenDistintoID(){
		Assert.assertEquals(2, new ParadaDeColectivo(null).getID(),0);
		Assert.assertEquals(3, new CGP(null).getID(),0);
		Assert.assertEquals(4, new Banco(null).getID(),0);
	}*/
}
