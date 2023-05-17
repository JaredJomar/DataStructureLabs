package lab4;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab4.problems.HasPrefixSumProblem;
import lab4.problems.HasPrefixSumProblem.Node;

public class HasPrefixSumTest {
	
	HasPrefixSumProblem p;
	Node<Integer> head1, head2, head3, head4;
	
	@Before
	public void setUp() {
		p = new HasPrefixSumProblem();
		
		Node<Integer> tempNode;

		// Digits from 1-10: {1,2,3,4,5,6,7,8,9,10}
		head1 = new Node<>(1);
		tempNode = head1;
		for(int i = 2; i <= 10; i++) {
			Node<Integer> newNode = new Node<>(i);
			tempNode.setNext(newNode);
			tempNode = tempNode.getNext();
		}

		// Factors of 2: {2,4,6,8,10,12}
		head2 = new Node<>(2);
		tempNode = head2;
		for(int i = 4; i <= 10; i += 2) {
			Node<Integer> newNode = new Node<>(i);
			tempNode.setNext(newNode);
			tempNode = tempNode.getNext();
		}
		
		// Powers of 2: {2,4,8,12,16,32,64,128,256,512,1024,2048}
		head3 = new Node<>(2);
		tempNode = head3;
		for(int i = 4; i <= Math.pow(2, 11); i *= 2) {
			Node<Integer> newNode = new Node<>(i);
			tempNode.setNext(newNode);
			tempNode = tempNode.getNext();
		}
		
		// Empty List
		head4 = null;
		
	}
	
	@Test
	public void testExactSum() {
		assertTrue("Failed to return on 5 on {1,2,3,4,5,6,7,8,9,10} with n = 15", p.hasPrefixSum(head1, 15) == 5);
	}

	@Test
	public void testUnderSum() {
		assertTrue("Failed to return -5 on {2,4,6,8,10} with n = 50", p.hasPrefixSum(head2, 50) == -5);
	}
	
	@Test
	public void testExceededSum() {
		assertTrue("Failed to return -9 on {2,4,8,12,16,32,64,128,256,512,1024,2048} with n = 1000", p.hasPrefixSum(head3, 1000) == -9);
	}
	
	@Test
	public void testEmptyList() {
		assertTrue("Failed to return 0 on empty list", p.hasPrefixSum(head4, 10) == 0);
	}

}
