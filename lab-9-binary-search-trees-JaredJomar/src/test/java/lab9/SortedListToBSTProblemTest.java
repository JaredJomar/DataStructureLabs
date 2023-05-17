package lab9;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lab9.problems.SortedListToBSTProblem;
import lab9.tree.BTNode;


public class SortedListToBSTProblemTest {
	
	SortedListToBSTProblem<Integer, Integer> p;
	List<Integer> L1;
	
	@Before
	public void setUp() throws Exception {
		L1 = new LinkedList<>();

		L1.add(2);
		L1.add(20);
		L1.add(39);
		L1.add(90);
		
		p = new SortedListToBSTProblem<>();
		
	}
	
	@Test
	public void test1() {
		BTNode<Integer, Integer> T = p.sortedListToBST(L1);
		assertEquals("Fails sortedListToBST(L1). Not height balanced.", true, p.isBalanced(T));
		assertEquals("Failed to convert sorted list to BST, order is wrong.", L1, p.flatten(T));
	}

	@Test
	public void test2() {
		L1.clear();
		L1.add(-1);
		L1.add(2);
		L1.add(20);
		L1.add(39);
		L1.add(45);
		L1.add(90);
		L1.add(100);
		
		BTNode<Integer, Integer> T = p.sortedListToBST(L1);
		assertEquals("Fails sortedListToBST(L1). Not height balanced.", true, p.isBalanced(T));
		assertEquals("Failed to convert sorted list to BST, order is wrong.", L1, p.flatten(T));
	}

	@Test
	public void test3() {
		L1.add(2, 0);
		L1.add(200);
		BTNode<Integer, Integer> T = p.sortedListToBST(L1);
		assertEquals("Fails sortedListToBST(L1). Not height balanced.", true, p.isBalanced(T));
		assertEquals("Failed to convert sorted list to BST, order is wrong.", L1, p.flatten(T));
	}
	
	@Test
	public void test4() {
		L1.add(0, 0);
		L1.add(3, 2);
		L1.add(200);
		L1.add(210);
		BTNode<Integer, Integer> T = p.sortedListToBST(L1);
		assertEquals("Fails sortedListToBST(L1). Not height balanced.", true, p.isBalanced(T));
		assertEquals("Failed to convert sorted list to BST, order is wrong.", L1, p.flatten(T));
	}
	
	@Test
	public void test5() {
		L1.add(0, 0);
		L1.add(1, 1);

		L1.add(4, 2);
		L1.add(200);
		L1.add(210);
		BTNode<Integer, Integer> T = p.sortedListToBST(L1);
		assertEquals("Fails sortedListToBST(L1). Not height balanced.", true, p.isBalanced(T));
		assertEquals("Failed to convert sorted list to BST, order is wrong.", L1, p.flatten(T));
	}

}
