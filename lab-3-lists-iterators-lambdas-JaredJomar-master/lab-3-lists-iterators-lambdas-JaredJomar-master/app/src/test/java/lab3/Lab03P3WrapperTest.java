package lab3;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Lab03P3WrapperTest {
	
	Lab03P3Wrapper.List<Integer> list;



	Integer[] test1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // auto boxing....
	Lab03P3Wrapper.List<Integer> test1Iterable;
	
	String[] test2 = {"CIIC3011", "CIIC4010", "CIIC3075", "CIIC4020", "INSO4101", "CIIC3081", "CIIC4082"};
	Lab03P3Wrapper.List<String> test2Iterable;
	
	Character[] test3 = {'C','I','I','C','4','0','2','0'};
	Lab03P3Wrapper.List<Character> test3Iterable;
	
	
	
	@Before
	public void setUp() throws Exception {	
		test1Iterable = new Lab03P3Wrapper.ArrayList<Integer>(test1.length);
		for (Integer num : test1) {
			test1Iterable.add(num);
			
		}
		
		test2Iterable = new Lab03P3Wrapper.ArrayList<String>(test2.length); 
		for (String s : test2) {
			test2Iterable.add(s);
			
		}
		
		test3Iterable = new Lab03P3Wrapper.ArrayList<Character>(test3.length);
		for (Character c : test3) {
			test3Iterable.add(c);
		}
		
	}

	@Test
	public void test1() {
		Integer[] expectedOrder = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		boolean tester = testIterator(test1Iterable, expectedOrder);
		assertTrue("Failed to iterate backard on array {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}", tester);
	}
	
	
	@Test
	public void test2() {
		String[] expectedOrder = {"CIIC4082","CIIC3081","INSO4101","CIIC4020","CIIC3075","CIIC4010","CIIC3011"};
		boolean tester = testIterator(test2Iterable, expectedOrder);
		assertTrue("Failed to iterate forward on array {CIIC4082,CIIC3081,INSO4101,CIIC4020,CIIC3075,CIIC4010,CIIC3011}", tester);
	}
	
	@Test
	public void test3() {
		Character[] expectedOrder = {'0','2','0','4','C','I','I','C'};
		boolean tester = testIterator(test3Iterable, expectedOrder);
		assertTrue("Failed to iterate forward on array {0,2,0,4,C,I,I,C};", tester);
	}
	
	private static <E extends Comparable<E>> boolean testIterator(Iterable<E> c, E[] expected) { 
		
		if(expected.length > 0 && !c.iterator().hasNext()) return false;
		int i = 0;
		for(E elm : c) {
			if (elm.compareTo(expected[i]) != 0) return false;
			i++;
		}
		return true;
	}

}
