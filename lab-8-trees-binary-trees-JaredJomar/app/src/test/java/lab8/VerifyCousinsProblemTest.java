package lab8;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab8.tree.BinaryTree;
import lab8.tree.BinaryTreeNode;

public class VerifyCousinsProblemTest {
	BinaryTree<String> T1;
	BinaryTree<String> T2;
	BinaryTree<String> T3;
	BinaryTree<String> T4;
	BinaryTree<String> T5;
	BinaryTree<String> T6;
	BinaryTree<String> T7;


	@Before
	public void setUp() throws Exception {
		this.T1 = new BinaryTree<String>(new BinaryTreeNode<String>("Joe"));
		this.T2 = new BinaryTree<String>(new BinaryTreeNode<String>("Ned"), T1, null);
		this.T3 = new BinaryTree<String>(new BinaryTreeNode<String>("Jim"), 
				new BinaryTree<String>(new BinaryTreeNode<String>("Joe")), 
				new BinaryTree<String>(new BinaryTreeNode<String>("Ned")));
				
		this.T7 = new BinaryTree<String>(new BinaryTreeNode<String>("Pol"));
		this.T6 = new BinaryTree<String>(new BinaryTreeNode<String>("Ken"), T7, 
				new BinaryTree<String>(new BinaryTreeNode<String>("Apu")));
	

		this.T5 = new BinaryTree<String>(new BinaryTreeNode<String>("Al"), T3,  T6);

	}


	@Test
	public void test1() {
		assertEquals("Fails verifyCousins(Ned, Joe) on T2 (where Joe is root and Ned its child). Expected value: false", false, T2.verifyCousins("Ned","Joe"));
	}
	
	@Test
	public void test2() {
		assertEquals("Fails verifyCousins(Ned, Joe) on T3 (where Joe and Ned are children of the root). Expected value: true", true, T3.verifyCousins("Ned","Joe"));
	}

	@Test
	public void test3() {
		assertEquals("Fails verifyCousins(Pol, Joe) on T5. Expected value: true", true, T5.verifyCousins("Pol","Joe"));
	}

	@Test
	public void test4() {
		assertEquals("Fails verifyCousins(Apu, Ned) on T5. Expected value: true", true, T5.verifyCousins("Apu","Ned"));
	}

	@Test
	public void test5() {
		assertEquals("Fails verifyCousins(Jim, Apu) on T5. Expected value: false", false, T5.verifyCousins("Jim","Apu"));
	}

}