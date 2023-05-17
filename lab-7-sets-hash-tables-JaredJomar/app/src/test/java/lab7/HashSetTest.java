package lab7;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import lab7.interfaces.Set;
import lab7.util.set.HashSet;

public class HashSetTest {

	HashSet<Integer> S1, S2, S3, S4;

	@Before
	public void setUp() {
		S1 = new HashSet<>(2);
		S2 = new HashSet<>(5);
		S3 = new HashSet<>(5);
		S4 = new HashSet<>(5);
	}

	@Test
	public void testAdd() {
		S1.getHT().setRehashCount(0);

		S1.add(1);
		S1.add(2);
		S1.add(3);
		S1.add(4);
		S1.add(5);

		int i = 1;
		Object[] s1Arr = S1.toArray();
		Arrays.sort(s1Arr);
		for(Object num : s1Arr) {
			assertTrue(num + " does not match with expected value " + i, (int) num == i++);
		}
		
		assertTrue("Failed to rehash twice", S1.getHT().getRehashCount() == 2);
		S1.getHT().setRehashCount(0);
	}
	

	@Test
	public void testRemove() {
		int countTrue = 0;
		S1.getHT().setRehashCount(0);

		S1.add(1);
		S1.add(2);
		S1.add(3);
		S1.add(4);
		S1.add(5);


		if(S1.remove(4)) countTrue++;
		if(S1.remove(5)) countTrue++;
		if(S1.remove(802)) countTrue++;
		
		S1.getHT().setRehashCount(0);

		assertTrue("Failed to remove twice successfully and fail once", countTrue == 2);
	}
	
	@Test
	public void testADTMethods() {
		S1.getHT().setRehashCount(0);

		S1.add(1);
		S1.add(2);
		S1.add(3);
		S1.add(4);
		S1.add(5);

		assertTrue("Failed to return true on S1.isMember(3) and false on S1.isMember(7)", S1.isMember(3) && !S1.isMember(7));
		assertTrue("Failed to return 5 as the size of S1", S1.size() == 5);
		assertTrue("Failed to return false for isEmpty()", !S1.isEmpty());
		
		S1.clear();
		
		assertTrue("Failed to clear S1", S1.isEmpty());
		S1.getHT().setRehashCount(0);
	}
	
	@Test
	public void testSetOperations() {
		S1.getHT().setRehashCount(0);

		S1.add(1);
		S1.add(2);
		S1.add(3);
		S1.add(4);
		S1.add(5);
		
		S2.add(1);
		S2.add(2);
		S2.add(3);
		S2.add(4);
		S2.add(5);
		
		S3.add(1);
		S3.add(7);
		S3.add(4); 
		S3.add(1); //Should return false when trying to add (run debugger to test if true)
		S3.add(3);
		
		S4.add(10);
		S4.add(8);
		S4.add(9);
	
		Set<Integer> union1 = S1.union(S2);
		Set<Integer> union2 = S2.union(S3);
		Set<Integer> intersection1 = S1.intersection(S2);
		Set<Integer> intersection2 = S2.intersection(S3);
		Set<Integer> difference1 = S1.difference(S2);
		Set<Integer> difference2 = S2.difference(S3);
		
		
		Object[] union1Arr = union1.toArray();
		Object[] union2Arr = union2.toArray();
		Object[] intersection1Arr = intersection1.toArray();
		Object[] intersection2Arr = intersection2.toArray();
		Object[] difference1Arr = difference1.toArray();
		Object[] difference2Arr = difference2.toArray();

		Arrays.sort(union1Arr);
		Arrays.sort(union2Arr);
		Arrays.sort(intersection1Arr);
		Arrays.sort(intersection2Arr);
		Arrays.sort(difference1Arr);
		Arrays.sort(difference2Arr);
		
		assertArrayEquals("Failed to return {1,2,3,4,5} on S1.union(S2)", union1Arr, new Object[]{1,2,3,4,5});
		assertArrayEquals("Failed to return {1,2,3,4,5,7} on S2.union(S3)", union2Arr, new Object[]{1,2,3,4,5,7});
		assertArrayEquals("Failed to return {1,2,3,4,5} on S1.intersection(S2)", intersection1Arr, new Object[]{1,2,3,4,5});
		assertArrayEquals("Failed to return {1,3,4} on S2.intersection(S3)", intersection2Arr, new Object[]{1,3,4});
		assertArrayEquals("Failed to return {} on S1.difference(S2)", difference1Arr, new Object[0]);
		assertArrayEquals("Failed to return {2,5} on S2.difference(S3)", difference2Arr, new Object[]{2,5});
		assertTrue("Failed to return true on S2.difference(S3).isSubSet(S1)", difference2.isSubSet(S1));
		assertTrue("Failed to return false on S4.isSubSet(S1)", !S4.isSubSet(S1));
		S1.getHT().setRehashCount(0);

	}

}
