package lab4;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab4.problems.RemoveAfterProblem;
import lab4.problems.RemoveAfterProblem.Node;

public class RemoveAfterTest {

	RemoveAfterProblem p;
	Node<String> head1, head2, head3, head4;
	String[] names1 = {"Ned", "Joe", "Jil", "Ned", "Kim", "Ned", "Ned", "Xi", "Ned", "Apu"};
	String[] names2 = {"Bob", "Jim", "Jil", "Bob", "Apu", "Bob", "Bob", "Bob"};
	String[] names3 = {"Ned", "Apu", "Jil", "Apu", "Apu", "Ned", "Ned", "Xi", "Ned", "Apu"};

	@Before
	public void setUp() {
		p = new RemoveAfterProblem();
		
		Node<String> tempNode;

		head1 = new Node<>("Al");
		tempNode = head1;
		for(int i = 0; i < names1.length; i++) {
			Node<String> newNode = new Node<>(names1[i]);
			tempNode.setNext(newNode);
			tempNode = tempNode.getNext();
		}

		head2 = new Node<>("Bob");
		tempNode = head2;
		for(int i = 0; i < names2.length; i++) {
			Node<String> newNode = new Node<>(names2[i]);
			tempNode.setNext(newNode);
			tempNode = tempNode.getNext();
		}
		
		head3 = new Node<>("Apu");
		tempNode = head3;
		for(int i = 0; i < names3.length; i++) {
			Node<String> newNode = new Node<>(names3[i]);
			tempNode.setNext(newNode);
			tempNode = tempNode.getNext();
		}
		
		head4 = null;
		
		p.setCountClears(0);
		p.setCountSetNext(0);
	}
	
	@Test
	public void testRemovals1() {
		boolean check = p.removeAfter(head1, "Ned") == 4 
				&& p.getCountClears() == 4 
				&& p.getCountSetNext() == 4; 
		assertTrue("Failed to return 4 on removals, countSetNext & countClears on list with target Ned", check);
	}
	
	@Test
	public void testRemovals2() {
		boolean check = p.removeAfter(head2, "Bob") == 2 
				&& p.getCountClears() == 2 
				&& p.getCountSetNext() == 2; 
		assertTrue("Failed to return 2 on removals, countSetNext & countClears on list with target Bob", check);
	}
	
	@Test
	public void testNoRemovals() {
		boolean check = p.removeAfter(head3, "Jim") == 0 
				&& p.getCountClears() == 0 
				&& p.getCountSetNext() == 0; 
		assertTrue("Failed to return 0 on removals, countSetNext & countClears since no copy of Jim was found in list", check);
	}

	@Test
	public void testEmptyList() {
		boolean check = p.removeAfter(head4, "Apu") == 0 
				&& p.getCountClears() == 0 
				&& p.getCountSetNext() == 0; 
		assertTrue("Failed to return 0 on removals, countSetNext & countClears since list is empty", check);
	}

}
