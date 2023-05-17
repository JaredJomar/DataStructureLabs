package lab5;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab5.util.dataStructures.LinkedQueue;
import lab5.util.interfaces.Queue;

import lab5.util.EmptyQueueException;

public class LinkedQueueTest {

	private Queue<String> Q;
	
	@Before
	public void setup() {
		Q = new LinkedQueue<>();
	}
	

	@Test(expected = EmptyQueueException.class)
	public void testEmptyQueueException() {
		Q.dequeue();
	}
	
	@Test(expected = EmptyQueueException.class)
	public void testEmptyQueueException2() {
		Q.front();
	}
		
	@Test
	public void testEnqueueAndDequeue() {
		String[] expected = {"Ron", "Jim", "Ned", "Bob"};
		Q.enqueue("Bob");
		Q.enqueue("Ned");
		Q.enqueue("Jim");
		Q.enqueue("Ron");
		
		boolean check = true;
		int i = Q.size();
		while(i > 0) {
			String elm = Q.dequeue();
			if(!elm.equals(expected[i - 1])) {
				check = false;
				break;
			} else {
				Q.enqueue(elm);
				i--;
			}
		}
		
		assertTrue("Failed to enqueue Bob, Ned, Jim, Ron in that order", check && i == 0);
		
	}
	
	@Test
	public void testFront() {
		Q.enqueue("Bob");
		Q.enqueue("Ned");
		Q.enqueue("Jim");
		Q.enqueue("Ron");
		
		assertTrue("Failed to return Bob as front of queue", Q.front().equals("Bob"));
	}

}