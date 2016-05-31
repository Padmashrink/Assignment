package test;
import org.junit.Test;

import com.dag.*;
import junit.framework.TestCase;

public class TestDAG extends TestCase{
	DirectedAcyclicGraph test1 = new DirectedAcyclicGraph();

	@Test
	public void testNow2(){
		assertTrue(true==test1.addEdge("D", "C"));
	}
	
	@Test
	public void testNow4(){
		 assertTrue(true==test1.addEdge("B", "D"));
	}
	
	@Test
	public void testNow5(){
		assertTrue(true==test1.addEdge("A", "B"));
		assertTrue(true==test1.addEdge("B", "C"));
		assertFalse(true==test1.addEdge("C", "A"));
	}
}
