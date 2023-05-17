package lab7;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab7.problems.MostFrequentProblem;

public class MostFrequentProblemTest {

	MostFrequentProblem p;
	
	int[] arr1 = {1,100,200,1,100};
	int[] arr2 = {2,2,2,2,3};
	int[] arr3 = {2,1000,2,1000,2,3};
	int[] arr4 = {0, 2, 4, 0, 2};
	int[] arr5 = {0, 2, 0, 0, 3};
	
	@Before
	public void setUp() {
		p = new MostFrequentProblem(); 
	}
	
	@Test
	public void test1() {
		assertTrue("Failed to return 100 on {1,100,200,1,100} with key 1", p.mostFrequent(arr1, 1) == 100);
	}
	
	@Test
	public void test2() {
		assertTrue("Failed to return 2 on {2,2,2,2,3} with key 2", p.mostFrequent(arr2, 2) == 2);
	}
	
	@Test
	public void test3() {
		assertTrue("Failed to return 1000 on {2,1000,2,1000,2,3} with key 2", p.mostFrequent(arr3, 2) == 1000);
	}
	
	@Test
	public void test4() {
		assertTrue("Failed to return 2 on {0, 2, 4, 0, 2} with key 0", p.mostFrequent(arr4, 0) == 2);
	}
	
	@Test
	public void test5() {
		assertTrue("Failed to return 3 on {0, 2, 0, 0, 3} with key 0", p.mostFrequent(arr5, 0) == 3);
	}

}
