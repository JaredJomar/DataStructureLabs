package lab4;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab4.problems.DoublyLinkedList;

public class DoublyLinkedListTest {
	
	DoublyLinkedList<String> l;
	String[] expected1 = {"Ned", "Joe", "Jill", "Apu"};
	String[] expected2 = {"Ned", "Ned", "Joe", "Joe", "Jill", "Apu", "Ned"};
	String[] expected3 = {"Ned", "Joe", "Jill", "Apu", "Ned"};
	String[] expected4 = {"Joe", "Jill", "Apu", "Ned"};
	String[] expected5 = {"Joe", "Jill", "Apu"};

	@Before
	public void setUp() throws Exception {
		l = new DoublyLinkedList<>();
	}

	@Test
	public void testAdd() {
		l.add("Ned");
		l.add("Joe");
		l.add("Jill");
		l.add("Apu");
		assertTrue("Failed to add Ned, Joe, Jill Apu to list correctly", equals(l, expected1));
	}

	@Test
	public void testAdd2() {
		l.add("Ned");
		l.add("Joe");
		l.add("Jill");
		l.add("Apu");
		
		l.add("Ned", 0);
		l.add("Ned", l.size());
		l.add("Joe", 2);
		assertTrue("Failed to add Ned to beginning and end of list, and Joe at position 2 correctly", equals(l, expected2));

	}
	
	@Test
	public void testRemove() {		
		l.add("Ned");
		l.add("Joe");
		l.add("Jill");
		l.add("Apu");
		
		l.add("Ned", 0);
		l.add("Ned", l.size());
		l.add("Joe", 2);
		
		
		l.remove("Joe");
		l.remove("Ned");
		
		assertTrue("Failed to remove extra copy of Joe and first copy of Ned correctly", equals(l, expected3));
		
	}
	
	@Test
	public void testRemove2() {
		
		l.add("Ned");
		l.add("Joe");
		l.add("Jill");
		l.add("Apu");
		
		l.add("Ned", 0);
		l.add("Ned", l.size());
		l.add("Joe", 2);
		
		
		l.remove("Joe");
		l.remove("Ned");
		
		l.remove(0);
		assertTrue("Failed to remove elm at position 1 correctly", equals(l, expected4));

	}
	
	@Test
	public void testRemoveAll() {
		l.add("Ned");
		l.add("Joe");
		l.add("Jill");
		l.add("Apu");
		
		l.add("Ned", 0);
		l.add("Ned", l.size());
		l.add("Joe", 2);
		
		
		l.remove("Joe");
		l.remove("Ned");
		
		l.remove(0);
		
		l.add("Ned", 0);
		l.add("Ned", 0);
		
		l.removeAll("Ned");
		assertTrue("Failed to remove 3 copies of Ned on {\"Ned\", \"Ned\", \"Joe\", \"Jill\", \"Apu\", \"Ned\"}", equals(l, expected5));
	}
	
	private boolean equals(DoublyLinkedList<String> l, String[] expected) {
		if(l.isEmpty()) return false;
		int i = 0;
		for (String elm : l) {
			if(!elm.equals(expected[i])) return false;
			i++;
		}
		return true;
	}
}
