package lab5;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab5.util.dataStructures.CircularDoublyLinkedQueue;
import lab5.util.interfaces.Deque;

public class CircularDoublyLinkedQueueTest {
	
	Deque<String> q;
	
	String[] names = {"Ned", "Joe", "Apu", "Jil"};
	String[] names1 = {"Joe", "Apu"};
	String[] names2 = {"Ned", "Ned", "Joe", "Apu", "Jil", "Jil"};


	@Before
	public void setUp() throws Exception {
		q = new CircularDoublyLinkedQueue<>();
	}

	@Test
	public void testAdds() {
		q.addFirst("Joe");
		q.addFirst("Ned");
		q.addLast("Apu");
		q.addLast("Jil");
		
		boolean c = check(names, q);
		
		assertTrue("Failed to add Ned & Joe to Front of Deque, as well as Apu & Jil to Rear of Deque", c);
		
	}
	
	@Test
	public void testRemoves() {
		q.addFirst("Joe");
		q.addFirst("Ned");
		q.addLast("Apu");
		q.addLast("Jil");
		
		q.removeFirst();
		q.removeLast();
		
		boolean c = check(names1, q);
		
		assertTrue("Failed to add Ned from Front of Deque, as well as Jil from Rear of Deque", c);
		
	}
	
	@Test
	public void testGets() {
		q.addFirst("Joe");
		q.addFirst("Ned");
		q.addLast("Apu");
		q.addLast("Jil");
		
		
		boolean c = q.getFirst().equals("Ned") && q.getLast().equals("Jil");
		
		assertTrue("Failed to get Ned from Front of Deque, as well as Jil from Rear of Deque", c);
		
	}
	
	@Test
	public void testRemoveOccurrences() {
		q.addFirst("Joe");
		q.addFirst("Ned");
		q.addFirst("Joe");
		q.addFirst("Ned");
		q.addLast("Apu");
		q.addLast("Jil");
		q.addLast("Apu");
		q.addLast("Jil");
		
		q.removeFirstOccurrence("Joe");
		q.removeLastOccurrence("Apu");
		
		boolean c = check(names2, q);
		
		assertTrue("Failed to remove first occurrence of Joe, as well as last occurrence of Apu", c);
		
	}

	private boolean check(String[] n, Deque<String> q) {
		if(n.length != q.size()) return false;
		
		String[] a = q.toArray();
		for (int i = 0; i < a.length; i++) {
			if(!a[i].equals(n[i])) return false;	
		}
		
		return true;
	}

}