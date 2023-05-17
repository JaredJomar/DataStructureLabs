package lab8;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab8.interfaces.Tree;
import lab8.tree.BinaryTree;
import lab8.tree.BinaryTreeNode;

public class GetLevelCountProblemTest {
	
	Tree<String> tree;
	
	@Before
	public void setUp() throws Exception {
		BinaryTreeNode<String> lee = new BinaryTreeNode<String>("Lee"); 
		BinaryTreeNode<String> joe = new BinaryTreeNode<String>("Joe"); 
		BinaryTreeNode<String> ron = new BinaryTreeNode<String>("Ron"); 
		BinaryTreeNode<String> apu = new BinaryTreeNode<String>("Apu"); 
		BinaryTreeNode<String> ken = new BinaryTreeNode<String>("Ken"); 
		BinaryTreeNode<String> ned = new BinaryTreeNode<String>("Ned"); 
		BinaryTreeNode<String> zoe = new BinaryTreeNode<String>("Zoe"); 
		BinaryTreeNode<String> cal = new BinaryTreeNode<String>("Cal"); 
		BinaryTreeNode<String> moe = new BinaryTreeNode<String>("Moe"); 
		BinaryTreeNode<String> rex = new BinaryTreeNode<String>("Rex"); 
		BinaryTreeNode<String> xi = new BinaryTreeNode<String>("Xi"); 
		BinaryTreeNode<String> bob = new BinaryTreeNode<String>("Bob"); 
		BinaryTreeNode<String> lu = new BinaryTreeNode<String>("Lu"); 
		BinaryTreeNode<String> mel = new BinaryTreeNode<String>("Mel"); 

		// Set children
		lee.setLeftChild(joe);
		lee.setRightChild(ron);
		//Set parent
		joe.setParent(lee);
		ron.setParent(lee);
		// Set children
		joe.setLeftChild(apu);
		joe.setRightChild(ken);
		// Set parent
		apu.setParent(joe);
		ken.setParent(joe);
		// Set children
		ron.setLeftChild(ned);
		ron.setRightChild(zoe);
		//Set parent
		ned.setParent(ron);
		zoe.setParent(ron);
		
		//Set child
		// Set parent
		apu.setRightChild(cal);
		cal.setParent(apu);
		// Set child
		cal.setLeftChild(bob);
		//Set parent
		bob.setParent(cal);
		
		// Set child
		zoe.setLeftChild(xi);
		//Set parent
		xi.setParent(zoe);
		// Set children
		ned.setLeftChild(moe);
		ned.setRightChild(rex);
		//Set parent
		moe.setParent(ned);
		rex.setParent(ned);
		//Set shild
		moe.setLeftChild(lu);
		// Set parent
		lu.setParent(moe);
		//Set child
		lu.setRightChild(mel);
		// Set parent
		mel.setParent(lu);
		
		tree = new BinaryTree<>(lee);
		
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		tree.getLevelCount(0);
	}
	
	@Test
	public void testRoot() {
		assertTrue("Failed to return 1 on root node", tree.getLevelCount(1) == 1);
	}
	
	@Test
	public void testLevel2() {
		assertTrue("Failed to return 4 on level 3", tree.getLevelCount(3) == 4);
	}
	
	@Test
	public void testLeaf() {
		assertTrue("Failed to return 2 on level 5", tree.getLevelCount(5) == 2);
	}
	
	

}
