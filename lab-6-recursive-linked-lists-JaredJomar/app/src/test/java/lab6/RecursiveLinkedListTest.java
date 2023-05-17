package lab6;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab6.RecLinkedList.List;
import lab6.RecLinkedList.RecursiveLinkedList;

public class RecursiveLinkedListTest {
	
	/**
	 * NOTE: THESE TESTERS WILL ONLY GRADE EXERCISE 2
	 * IN TERMS OF FUNCTIONALITY, TAs WILL MANUALLY
	 * GRADE THE CODE TO SEE IF IT WAS IMPLEMENTED
	 * RECUSRIVELY OR NOT, AS WELL AS CHECK EXERCISES
	 * 1 & 3 BY HAND. 
	 */
	private List<String> todo;
	private List<String> addMethod;
	private List<String> removeMethod;
	private List<String> setMethod;

	
	@Before
	public void setup() {
		todo = new RecursiveLinkedList<>();
		addMethod = new RecursiveLinkedList<>();
		removeMethod = new RecursiveLinkedList<>();
		setMethod = new RecursiveLinkedList<>();
	}
	
	@Test
	public void testTODOMethods() {
		todo.add("CIIC4020");
		todo.add("CIIC4010");
		todo.add("CIIC4010");
		todo.add("CIIC4030");
		todo.add("CIIC4050");
		todo.add("CIIC4060");
		
		assertTrue("Failed to return 1 as first index of CIIC4020", todo.firstIndex("CIIC4010") == 1);
		assertTrue("Failed to return 2 as last index of CIIC4010", todo.lastIndex("CIIC4010") == 2);
		assertTrue("Failed to return true on contains(CIIC4010)", todo.contains("CIIC4010"));
		
		int removed = todo.removeAll("CIIC4010");
		assertTrue("Failed to remove all copies of CIIC4010", !todo.contains("CIIC4010") && removed == 2);
		

		assertTrue("Failed to return CIIC4060 as last element", todo.last().equals("CIIC4060"));
		assertTrue("Failed to return CIIC4020 as first element", todo.first().equals("CIIC4020"));
		
		todo.clear();
		assertTrue("Failed to clear list correctly", todo.isEmpty());
		
	}
	
	@Test
	public void testRecAdd() {
		
		String[] expected = {"CIIC4020", "CIIC4030", "CIIC4050", "CIIC4060"};
		
		addMethod.add("CIIC4020");
		addMethod.add("CIIC4030");
		addMethod.add("CIIC4050");
		addMethod.add("CIIC4060");
		
		Object[] got = addMethod.toArray();
		
		assertArrayEquals("Failed to add CIIC4020, CIIC4030, CIIC4050, CIIC4060 correctly", expected, got);
	
	}
	
	@Test
	public void testRemove() {
		String[] expected1 = {"CIIC4020", "CIIC4030", "CIIC4060"};
		String[] expected2 = {"CIIC4030", "CIIC4060"};
		String[] expected3 = {"CIIC4030"};
		Object[] got;

		
		removeMethod.add("CIIC4020");
		removeMethod.add("CIIC4030");
		removeMethod.add("CIIC4050");
		removeMethod.add("CIIC4060");
		
		removeMethod.remove(2);
		got = removeMethod.toArray();
		assertArrayEquals("Failed to remove element at index 2 correctly", expected1, got);

		removeMethod.remove(0);
		got = removeMethod.toArray();
		assertArrayEquals("Failed to remove element at start of list correctly", expected2, got);

		removeMethod.remove(removeMethod.size() - 1);
		got = removeMethod.toArray();
		assertArrayEquals("Failed to remove element at end of list correctly", expected3, got);
		
		
	}
	
	@Test
	public void testRecSet() {
		
		String[] expected1 = {"CIIC4020", "CIIC4025", "CIIC4050", "CIIC4060"};
		String[] expected2 = {"CIIC4010", "CIIC4025", "CIIC4050", "CIIC4060"};
		String[] expected3 = {"CIIC4010", "CIIC4025", "CIIC4050", "CIIC4070"};
		Object[] got;
		
		setMethod.add("CIIC4020");
		setMethod.add("CIIC4030");
		setMethod.add("CIIC4050");
		setMethod.add("CIIC4060");
		
		setMethod.set(1, "CIIC4025");
		got = setMethod.toArray();
		assertArrayEquals("Failed to change element at index 1 from CIIC4030 to CIIC4025 correctly", expected1, got);

		setMethod.set(0, "CIIC4010");
		got = setMethod.toArray();
		assertArrayEquals("Failed to change element at start of list from CIIC4020 to CIIC4010 correctly", expected2, got);

		setMethod.set(setMethod.size() - 1, "CIIC4070");
		got = setMethod.toArray();
		assertArrayEquals("Failed to change element at  end of list from CIIC4060 to CIIC4070 correctly", expected3, got);
		
		
		
	}

}