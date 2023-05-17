package lab9;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab9.problems.MinimumDifferenceProblem;
import lab9.tree.BTNode;

public class MinimumDifferenceProblemTest {

	BTNode<Integer, Integer> root1;
	BTNode<Integer, Integer> root2;
	BTNode<Integer, Integer> root3;
	BTNode<Integer, Integer> root4;
	
	MinimumDifferenceProblem p;

	@Before
	public void setUp() {

		p = new MinimumDifferenceProblem();
		
		//T1
		BTNode<Integer, Integer> n1 = new BTNode<>(1, 1);
		BTNode<Integer, Integer> n2 = new BTNode<>(2, 2);
		BTNode<Integer, Integer> n3 = new BTNode<>(3, 3);
		BTNode<Integer, Integer> n4 = new BTNode<>(4, 4);
		BTNode<Integer, Integer> n6 = new BTNode<>(6, 6);

		root1 = n4;
		root1.setLeftChild(n2);
		root1.setRightChild(n6);

		n2.setLeftChild(n1);
		n2.setRightChild(n3);

		n1.setParent(n2);
		n3.setParent(n2);

		n2.setParent(n4);
		n6.setParent(n4);

		//T2
		BTNode<Integer, Integer> t2n10 = new BTNode<>(10, 10);
		BTNode<Integer, Integer> t2n13 = new BTNode<>(13, 13);
		BTNode<Integer, Integer> t2n2 = new BTNode<>(2, 2);
		BTNode<Integer, Integer> t2n4 = new BTNode<>(4, 4);
		BTNode<Integer, Integer> t2n7 = new BTNode<>(7, 7);
		BTNode<Integer, Integer> t2n11 = new BTNode<>(11, 11);
		BTNode<Integer, Integer> t2n18 = new BTNode<>(18, 18);

		root2 = t2n10;
		root2.setLeftChild(t2n4);
		root2.setRightChild(t2n13);

		t2n4.setParent(t2n10);
		t2n13.setParent(t2n10);

		t2n4.setLeftChild(t2n2);
		t2n4.setRightChild(t2n7);

		t2n2.setParent(t2n4);
		t2n7.setParent(t2n4);

		t2n13.setLeftChild(t2n11);
		t2n13.setRightChild(t2n18);

		t2n11.setParent(t2n13);
		t2n18.setParent(t2n13);

		//T3
		BTNode<Integer, Integer> t3n10 = new BTNode<>(10, 10);
		BTNode<Integer, Integer> t3n13 = new BTNode<>(13, 13);
		BTNode<Integer, Integer> t3n5 = new BTNode<>(5, 5);
		BTNode<Integer, Integer> t3n18 = new BTNode<>(18, 18);

		root3 = t3n10;
		root3.setLeftChild(t3n5);
		root3.setRightChild(t3n13);

		t3n5.setParent(root3);
		t3n13.setParent(root3);

		t3n13.setRightChild(t3n18);
		t3n18.setParent(t3n13);

		//T4
		BTNode<Integer, Integer> t4n1 = new BTNode<>(1, 1);
		BTNode<Integer, Integer> t4n0 = new BTNode<>(0, 0);
		BTNode<Integer, Integer> t4n48 = new BTNode<>(48, 48);
		BTNode<Integer, Integer> t4n12 = new BTNode<>(12, 12);
		BTNode<Integer, Integer> t4n49 = new BTNode<>(49, 49);

		root4 = t4n1;
		
		root4.setLeftChild(t4n0);
		root4.setRightChild(t4n48);
		t4n0.setParent(root4);
		t4n48.setParent(root4);
		
		t4n48.setLeftChild(t4n12);
		t4n48.setRightChild(t4n49);
		t4n12.setParent(t4n48);
		t4n49.setParent(t4n48);
	}

	@Test
	public void test1() {
		assertEquals("Failed to return 1 on [4,2,6,1,3]", 1, p.getMinimumDifference(root1));
	}

	@Test
	public void test2() {
		assertEquals("Failed to return 1 on [10,4,13,2,7,11,18]", 1, p.getMinimumDifference(root2));
	}

	@Test
	public void test3() {
		assertEquals("Failed to return 3 on [10,5,null,null,13,null,18]", 3, p.getMinimumDifference(root3));
	}

	@Test
	public void test4() {
		assertEquals("Failed to return 1 on [1,0,48,null,null,12,49]", 1, p.getMinimumDifference(root4));
	}

}
