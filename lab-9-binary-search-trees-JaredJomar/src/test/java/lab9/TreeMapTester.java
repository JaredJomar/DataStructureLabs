package lab9;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lab9.interfaces.List;
import lab9.interfaces.Map;
import lab9.tree.TreeMap;
import lab9.util.list.LinkedList;


public class TreeMapTester {

	private Map<Integer, Integer> bst;
	
	@Before
	public void setup() {
		/*
		 * BST to use for testing
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
		 *           
		 * In-Order Output
		 * 13, 16, 18, 20, 32, 39, 44, 55, 61, 79, 80, 85, 92
		 * 
		 * Pre-Order Output
		 * 55, 20, 13, 16, 18, 32, 44, 39, 79, 61, 92, 80, 84
		 * 
		 * Post-Order Output
		 * 18, 16, 13, 39, 44, 32, 20, 61, 85, 80, 92, 79, 55
		 */
		
		bst = new TreeMap<Integer, Integer>();
	}
	
	@Test
	public void testPut(){
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
		
		List<Integer> inorder = bst.getKeys();
		List<Integer> expected = new LinkedList<>(new Integer[] {13, 16, 18, 20, 32, 39, 44, 55, 61, 79, 80, 85, 92});
		assertTrue("Failed to add elements to TreeMap incorrectly, output in-order is not in ascending order", equals(expected, inorder));
		
	}
	
	@Test
	public void testGetAndContainsKey() {
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
		
		assertTrue("Failed to return true for containsKey(13)", bst.containsKey(13));
		assertFalse("Failed to return false for containsKey(0)", bst.containsKey(0));
		
		assertTrue("Failed to return true for get(80) == 80", bst.get(80) == 80);
		assertFalse("Failed to return false for get(21) == 21", bst.get(21) != null);
	}
	
	@Test
	public void testOther() {
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
		
		assertTrue("Failed to return 13 for size() before clear", bst.size() == 13);
		assertFalse("Failed to return false for isEmpty() before clear", bst.isEmpty());
		
		bst.clear();
		
		assertTrue("Failed to return 0 for size() after clear", bst.size() == 0);
		assertTrue("Failed to return true for isEmpty() after clear", bst.isEmpty());
		assertTrue("Failed to clear map", bst.getRoot() == null);
	}
	
	private boolean equals(List<Integer> L1, List<Integer> L2) {
		if(L1.size() == L2.size()) {
			for (int i = 0; i < L1.size(); i++) 
				 if(L1.get(i) != L2.get(i)) 
					 return false;
			return true;
		}
		
		return false;
	}
	
}
