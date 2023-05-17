package lab8;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab8.interfaces.List;
import lab8.interfaces.Tree;
import lab8.tree.BinaryTree;
import lab8.tree.BinaryTreeNode;
import lab8.util.list.LinkedList;

public class GetLeveElementsProblemTest {
	
	Tree<String> tree;
	List<String> results;
	
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
		results = tree.getLevelElements(0);
	}
	
	@Test
	public void testRoot() {
		results = tree.getLevelElements(1);
		assertTrue("Failed to return {Lee} on root node", results.get(0).equals("Lee"));
	}
	
	@Test
	public void testLevel2() {
		results = tree.getLevelElements(3);
		List<String> expected = new LinkedList<>();
		expected.add("Apu");
		expected.add("Ken");
		expected.add("Ned");
		expected.add("Zoe");
		boolean check = equals(results, expected);
		assertTrue("Failed to return {Apu, Ken, Ned, Zoe} on level 3", check);
	}
	
	@Test
	public void testLeaf() {
		results = tree.getLevelElements(5);
		List<String> expected = new LinkedList<>();
		expected.add("Bob");
		expected.add("Lu");
		boolean check = equals(results, expected);
		assertTrue("Failed to return {Bob, Lu} on level 5", check);
	}
	
	private static <E> boolean equals(List<E> l1, List<E> l2) {
		if(l1.size() != l2.size()) return false;
		for(int i = 0; i < l1.size(); i++) {
			if(!l1.get(i).equals(l2.get(i))) return false;
		}
		return true;
		
	}
	
	

}
