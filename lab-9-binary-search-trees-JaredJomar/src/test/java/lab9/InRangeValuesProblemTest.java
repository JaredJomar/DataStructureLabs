package lab9;
import static org.junit.Assert.assertEquals;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import lab9.interfaces.Entry;
import lab9.interfaces.List;
import lab9.problems.InRangeValuesProblem;
import lab9.tree.BTEntry;
import lab9.tree.BTNode;

public class InRangeValuesProblemTest {
	
	BTNode<Integer, Integer> T1;
	BTNode<Integer, Integer> T2;
	
	InRangeValuesProblem<Integer, Integer> p;

	
	public static void print(List<Entry<Integer, Integer>> L, PrintStream P) {
		P.print("[ ");
			
		for (Entry<Integer, Integer> o: L) {
			P.print(o.getValue() + " ");
		}
		
		P.println("]");
		
	}
	@Before
	public void setUp() throws Exception {
		
		p = new InRangeValuesProblem<>();
		 
		
		/*
		 * 			50
		 * 		   /  \
		 * 		  10  56
		 * 		 /  \   \
		 * 		2   23  70
		 *	   /		/
		 *	  0		   60
		 *				 \
		 *				 61
		 */	
		T1 = new BTNode<>(50, 50);

		BTNode<Integer, Integer> ten = new BTNode<>(10, 10); 
		BTNode<Integer, Integer> fifty6 = new BTNode<>(56, 56);

		BTNode<Integer, Integer> two = new BTNode<>(2, 2); 
		BTNode<Integer, Integer> twenty3 = new BTNode<>(23, 23); 
		BTNode<Integer, Integer> seventy = new BTNode<>(70, 70); 

		BTNode<Integer, Integer> sixty1 = new BTNode<>(61, 61); 
		BTNode<Integer, Integer> zero = new BTNode<>(0,0);
		

		
		T1.setLeftChild(ten);
		T1.setRightChild(fifty6);
		ten.setParent(T1);
		fifty6.setParent(T1);
		
		ten.setLeftChild(two);
		ten.setRightChild(twenty3);
		two.setParent(ten);
		twenty3.setParent(ten);
		
		fifty6.setRightChild(seventy);
		seventy.setParent(fifty6);
		
		two.setLeftChild(zero);
		zero.setParent(two);
		
		seventy.setLeftChild(sixty1);
		sixty1.setParent(seventy);
		
		T2 = new BTNode<>(20, 20); // BST with only one node (20) 

	}

	@Test
	public void test1() {
		List<Entry<Integer, Integer>> L = p.inRangeValues(T1, 20, 51);
		assertEquals("Fails inRangeValues(20, 51) on T1. Expected value: [50 23]", true, 
				L.get(0).getValue() == 50 && L.get(1).getValue() == 23);

	}

	@Test
	public void test2() {
		List<Entry<Integer, Integer>> L = p.inRangeValues(T2, 0, 25);
		assertEquals("Fails inRangeValues(0, 25) on T2. Expected value: [20]", true, L.get(0).getValue() == 20);

	}

	@Test
	public void test3() {
		List<Entry<Integer, Integer>> L = p.inRangeValues(T1, 100, 200);
		assertEquals("Fails inRangeValues(100, 200) on T1. Expected value: [ ]", true, L.isEmpty());
	}

	@Test
	public void test4() {
		List<Entry<Integer, Integer>> L = p.inRangeValues(T1, 0, 40);

		assertEquals("Fails inRangeValues(0, 40) on T1. Expected value: [ 10 2 0 23 ]", true, 
				L.get(0).getValue() == 10 && L.get(1).getValue() == 2 && L.get(2).getValue() == 0
						&& L.get(3).getValue() == 23);

	}

	@Test
	public void test5() {

		List<Entry<Integer, Integer>> L = p.inRangeValues(T1, 8, 68);

		assertEquals("Fails inRangeValues(8, 68) on T1. Expected value: [50 10 23 56 61]", true, 
				L.get(0).getValue() == 50 && L.get(1).getValue() == 10 && L.get(2).getValue() == 23 &&
				L.get(3).getValue() == 56 && L.get(4).getValue() == 61);


	}

}
