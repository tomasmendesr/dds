package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class ASD {

	@Test
	public void test(){
	List<Integer> lista = new ArrayList<Integer>();
	lista.add(1);
	lista.add(2);
	lista.add(3);
	Integer rdo = lista.stream().reduce(0,(a,b) -> a + b);
	Assert.assertEquals(5, rdo,0);
	
	}
}
