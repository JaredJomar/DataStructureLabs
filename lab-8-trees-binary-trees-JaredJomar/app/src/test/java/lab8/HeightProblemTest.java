package lab8;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab8.tree.BinaryTree;
import lab8.tree.BinaryTreeNode;

public class HeightProblemTest {
	BinaryTree<String> T1;
	BinaryTree<String> T2;
	BinaryTree<String> T3;
	BinaryTree<String> T4;
	BinaryTree<String> T5;


	@Before
	public void setUp() throws Exception {
		// T1
		T1 = new BinaryTree<String>();
		
		// T2
		T2 = new BinaryTree<String>(new BinaryTreeNode<String>("Ned"));
		
		// T3
		BinaryTree<String> t2  = new BinaryTree<String>(new BinaryTreeNode<String>("Ned"));
		BinaryTree<String> t3 = new BinaryTree<String>(new BinaryTreeNode<String>("Jim"));
		BinaryTree<String> t4 = new BinaryTree<String>(new BinaryTreeNode<String>("Bob"), t2, t3);
		BinaryTree<String> t5 = new BinaryTree<String>(new BinaryTreeNode<String>("Joe"));
		BinaryTree<String> t6 = new BinaryTree<String>(new BinaryTreeNode<String>("Kim"));
		BinaryTree<String> t7 = new BinaryTree<String>(new BinaryTreeNode<String>("Al"), t5, t6);
		T3 = new BinaryTree<String>(new BinaryTreeNode<String>("Moe"), t7, t4);
		
		// T4
		t2 = new BinaryTree<String>(new BinaryTreeNode<String>("Ned"));
		t3 = new BinaryTree<String>(new BinaryTreeNode<String>("Jim"));
		T4 =  new BinaryTree<String>(new BinaryTreeNode<String>("Bob"), t2, t3);

		// T5
		t2 = new BinaryTree<String>(new BinaryTreeNode<String>("Ned"));
		T5 =  new BinaryTree<String>(new BinaryTreeNode<String>("Apu"), t2, null);

	}

	@Test
	public void testHeight1() {
		assertEquals("Fails height(\"Joe\") on empty tree. Expected height to be -1.", -1, this.T1.height("Joe"));
		
	}
	@Test
	public void testHeight2() {
	
		assertEquals("Fails height(\"Ned\") on tree with one node, where root is Ned. Expected height to be 0.", 0, this.T2.height("Ned"));
		
	}
	@Test
	public void testHeight3() {
	
		assertEquals("Fails height(\"Bob\") on tree with 3 nodes, where root is Bob. Expected height to be 1.", 1, this.T4.height("Bob"));
	
	}
	
	@Test
	public void testHeight4() {
	
		assertEquals("Fails height(\"Moe\") on tree with 7 nodes, where root is Moe. Expected height to be 2.", 2, this.T3.height("Moe"));

	
	}
	
	@Test
	public void testHeight5() {
		assertEquals("Fails height(\"Bob\") on tree with 7 nodes, where root is Moe. Expected height to be 1.", 1, this.T3.height("Bob"));
		
		
	}
	

}