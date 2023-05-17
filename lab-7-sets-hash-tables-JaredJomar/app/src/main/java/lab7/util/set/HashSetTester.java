package lab7.util.set;

import lab7.interfaces.Set;

public class HashSetTester {

	public static void main(String[] args) {
		Set<Integer> hashSet = new HashSet<>(2);
		Set<Integer> hashSet2 = new HashSet<>(5);
		Set<Integer> hashSet3 = new HashSet<>(5);
		
		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(3); //Should Trigger rehash()
		hashSet.add(4);
		hashSet.add(5); //Should Trigger rehash()
		
		System.out.println("------------------------");
		
		System.out.println("hashSet1:");
		printSet(hashSet); //Test Iterator
		
		System.out.println("------------------------");
		
		System.out.println("Removing Element 4 & 5\n");
		
		/*CHECK DEBUGGER TO SEE IF IT RETURNS TRUE OR FALSE*/
		
		/*Should return true*/
		hashSet.remove(4);
		hashSet.remove(5);
		
		/*Should return false*/
		hashSet.remove(802);
		
		System.out.println("hashSet1: ");
		printSet(hashSet);
		
		System.out.println();
		hashSet.add(4);
		hashSet.add(5); 
		
		System.out.println("------------------------");
		
		System.out.println("Is 7 in hashSet? " + hashSet.isMember(7));
		System.out.println("Is 3 in hashSet? " + hashSet.isMember(3));
		
		System.out.println("------------------------");
		
		System.out.println("Adding Elements to hashSet2: \n");
		hashSet2.add(1);
		hashSet2.add(2);
		hashSet2.add(3); 
		hashSet2.add(4);
		hashSet2.add(5);
		
		System.out.println();
		
		System.out.println("hashSet2:");
		printSet(hashSet2);
		
		System.out.println();
		
		System.out.println("Adding Elements to hashSet3: \n");
		hashSet3.add(1);
		hashSet3.add(7);
		hashSet3.add(4); 
		hashSet3.add(1); //Should return false when trying to add (run debugger to test if true)
		hashSet3.add(3);
		
		System.out.println();
		
		System.out.println("hashSet3:");
		printSet(hashSet3);
		
		System.out.println("------------------------");
		
		System.out.println("Union of hashSet and hashSet2 is: \n");
		Set<Integer> union = hashSet.union(hashSet2); //Should be equal
		System.out.println();
		printSet(union);
		
		System.out.println("------------------------");

		System.out.println("Union of hashSet2 and hashSet3 is: \n");
		Set<Integer> union2 = hashSet2.union(hashSet3); //Should be 1,2,3,4,5,7
		System.out.println();
		printSet(union2);
		
		System.out.println("------------------------");

		System.out.println("Difference of hashSet and hashSet2 is: ");
		Set<Integer> difference = hashSet.difference(hashSet2); //Should be empty
		System.out.println();
		printSet(difference);
		
		System.out.println("------------------------");

		System.out.println("Difference of hashSet2 and hashSet3 is: \n");
		Set<Integer> difference2 = hashSet2.difference(hashSet3); //Should be 5,2
		System.out.println();
		printSet(difference2);
		
		System.out.println("------------------------");

		System.out.println("Intersection of hashSet and hashSet2 is: \n");
		Set<Integer> intersection = hashSet.intersection(hashSet2); //Should be equal
		System.out.println();
		printSet(intersection);
		
		System.out.println("------------------------");

		System.out.println("Intersection of hashSet and hashSet3 is: \n");
		Set<Integer> intersection2 = hashSet.intersection(hashSet3); //Should be 1,3,4
		System.out.println();
		printSet(intersection2);
		
		
		System.out.println("Done!");

	}
	
	public static void printSet(Set<Integer> s) {
		int i = 0;
		System.out.println("Size: " + s.size()); //Test size()
		if(s.isEmpty()) System.out.println("Set is Empty");
		for (Integer integer : s) {
			System.out.println("Element " + i + ": " + integer);
			i++;
		}
	}

}
