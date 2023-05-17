package lab8;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab8.interfaces.Tree;
import lab8.tree.BinaryTree;
import lab8.tree.BinaryTreeNode;

public class IsCompleteProblemTest {
	
	Tree<String> T1, T2, T3, T4;
	
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
		
		T1 = new BinaryTree<>(lee);
		
		
		/**
		 * 		 1
	     *      / \
	     * 	   2   3
	     * 	  / \ /
	     * 	 4  5 6 
		 */
		T2 = new BinaryTree<>(new BinaryTreeNode<>("1"), 
								  new BinaryTree<>(
										  new BinaryTreeNode<>("2"),
										  new BinaryTree<>(new BinaryTreeNode<>("4")),
										  new BinaryTree<>(new BinaryTreeNode<>("5"))
								  ),
								  new BinaryTree<>(
										  new BinaryTreeNode<>("3"),
										  new BinaryTree<>(new BinaryTreeNode<>("6")),
										  null
								  )
							  );
		
		/**
		 * 		 1
	     *      /
	     * 	   2   
	     * 	  / \ 
	     * 	 4   5 
		 */
		T3 = new BinaryTree<>(new BinaryTreeNode<>("1"), 
				  new BinaryTree<>(
						  new BinaryTreeNode<>("2"),
						  new BinaryTree<>(new BinaryTreeNode<>("4")),
						  new BinaryTree<>(new BinaryTreeNode<>("5"))
				  ), null);
		
		
		/**
		 * 		 1
	     *      / \
	     * 	   2   3
	     * 	    \ /
	     * 		5 6
		 */
		T4 = new BinaryTree<>(new BinaryTreeNode<>("1"), 
								  new BinaryTree<>(
										  new BinaryTreeNode<>("2"),
										  null,
										  new BinaryTree<>(new BinaryTreeNode<>("5"))
								  ),
								  new BinaryTree<>(
										  new BinaryTreeNode<>("3"),
										  new BinaryTree<>(new BinaryTreeNode<>("6")),
										  null
								  )
							  );

	}
	

	@Test
	public void testExampleTree() {
		assertFalse("Failed to return false on example tree T1", T1.isComplete());
	}
	
	@Test
	public void testT2() {
		assertTrue("Failed to return true on tree T2", T2.isComplete());
	}

	@Test
	public void testT3() {		
		assertFalse("Failed to return false on tree T3", T3.isComplete());
	}
	
	@Test
	public void testT4() {		
		assertFalse("Failed to return false on tree T4", T4.isComplete());
	}
	

}
