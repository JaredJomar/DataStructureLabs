package lab9;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import lab9.interfaces.List;
import lab9.interfaces.Map;
import lab9.tree.TreeMap;


public class TreeMapTester2 {

	private Map<Integer, Integer> bst;
	private List<Integer> inorder;
	
	@Before
	public void setup() {
		bst = new TreeMap<Integer, Integer>();
		/*
		 * Building BST
		 * 
		 *               55
		 *              /  \
		 *             /    \
		 *            /      \      
		 *          20        79
		 *         /  \      /  \
		 *        /    \    /    \       
		 *       13    32  61    92
		 *         \     \      /
		 *         16    44    80
		 *           \   /        \
		 *           18 39        85
		 */
		bst.put(55, 55);
		bst.put(20, 20);
		bst.put(79, 79);
		bst.put(13, 13);
		bst.put(32, 32);
		bst.put(44, 44);
		bst.put(39, 39);
		bst.put(61, 61);
		bst.put(92, 92);
		bst.put(80, 80);
		bst.put(85, 85);
		bst.put(16, 16);
		bst.put(18, 18);
		// 13, 16, 18, 20 32 39 44 55 61 79 80 85 92
		inorder = bst.getKeys();
	}
	@Test
	public void testRemoveEmptyTree() {
		bst = new TreeMap<Integer, Integer>();
		Integer e = bst.remove(55);
		assertTrue("Failed to return null on an empty BST.", e == null && bst.isEmpty());
	}
	@Test
	public void testRemoveOneNodeTree() {
		bst = new TreeMap<Integer, Integer>();
		// Its only node
		bst.put(55, 55);
		Integer e = bst.remove(55);
		assertTrue("Failed to return entry 55 on BST with only one node.", !bst.containsKey(55) && e != null && e == 55 && bst.isEmpty());
	}
	@Test
	public void testRemoveTwoNodeTree() {
		bst = new TreeMap<Integer, Integer>();
		// Its only node
		bst.put(55, 55);
		bst.put(60, 60);
		Integer e = bst.remove(55);
		assertTrue("Failed to return entry 55 on BST with 2 nodes.", !bst.containsKey(55) && e != null && e == 55 && bst.size() == 1);
	}
	@Test
	public void testRemoveLeaf() {
		Integer e = bst.remove(85);
		List<Integer> results = bst.getKeys();
		inorder.remove((Integer) 85);
		for(int i = 0; i < inorder.size(); i++) {
			if(inorder.get(i) != results.get(i))
				fail("BST removed node 85 (leaf) incorrectly. Values of the resulting BST aren't correct.");
		}
		assertTrue("Removing node 85 (leaf) didn't return the correct entry. Or size is wrong.", !bst.containsKey(85) && e != null && e == 85 && bst.size() == 12);
	}
	@Test
	public void testRemoveNodeWithOneChildLeft() {
		Integer e = bst.remove(92);
		List<Integer> results = bst.getKeys();
		inorder.remove((Integer) 92);
		for(int i = 0; i < inorder.size(); i++) {
			if(inorder.get(i) != results.get(i))
				fail("BST removed node 92 (has left child) incorrectly. Values of the resulting BST aren't correct.");
		}
		assertTrue("Removing node 92 (has left child) didn't return the correct entry. Or size is wrong.",  !bst.containsKey(92) && e != null && e == 92 && bst.size() == 12);
	}
	@Test
	public void testRemoveNodeWithOneChildRight() {
		Integer e = bst.remove(32);
		List<Integer> results = bst.getKeys();
		inorder.remove((Integer) 32);
		for(int i = 0; i < inorder.size(); i++) {
			if(inorder.get(i) != results.get(i))
				fail("BST removed node 32 (has right child) incorrectly. Values of the resulting BST aren't correct.");
		}
		assertTrue("Removing node 32 (has right child) didn't return the correct entry. Or size is wrong.", !bst.containsKey(32) && e != null && e == 32 && bst.size() == 12);
	}
	@Test
	public void testRemoveNodeWithBothChildren() {
		Integer e = bst.remove(20);
		List<Integer> results = bst.getKeys();
		inorder.remove((Integer) 20);
		for(int i = 0; i < inorder.size(); i++) {
			if(inorder.get(i) != results.get(i))
				fail("BST removed node 20 (has both children) incorrectly. Values of the resulting BST aren't correct.");
		}
		assertTrue("Removing node 20 (has both children) didn't return the correct entry. Or size is wrong.", !bst.containsKey(20) && e != null && e == 20 && bst.size() == 12);
	}
	
	// SHould we do this one? Or do we just leave the basic cases?
	@Test
	public void testRemoveRoot() {
		Integer e = bst.remove(55);
		List<Integer> results = bst.getKeys();
		inorder.remove((Integer) 55);
		for(int i = 0; i < inorder.size(); i++) {
			if(inorder.get(i) != results.get(i))
				fail("BST removed node 55 (root has 2 children) incorrectly. Values of the resulting BST aren't correct. In this case the replacement node has a left child that also needs to be moved.");
		}
		assertTrue("Removing node 55 (root has 2 children) didn't return the correct entry. Or size is wrong.", !bst.containsKey(55) && e != null && e == 55 && bst.getRoot().getEntry().getKey() == 44 && bst.size() == 12);
	}
}
