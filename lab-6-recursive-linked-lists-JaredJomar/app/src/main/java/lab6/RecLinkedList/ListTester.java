package lab6.RecLinkedList;

import java.util.Comparator;

@SuppressWarnings("unused")
public class ListTester {

	public static void main(String[] args) {
		listTester("Testing the List ADT based on recursive singly linked list: ",
				new RecursiveLinkedList<Integer>());
	}

	private static void listTester(String msg, List<Integer>  list) {
		System.out.println(msg);

		try {
			list.add(1, 400); // Test adding with invalid index
		}
		catch (Exception e) {
			System.out.println(e);
		}
		list.add(100);    // Test adding without position
		list.add(0, 1);   // Test adding at the beginning
		list.add(2, 200); // Test adding at the end
		list.add(300);    // Test adding without position again

		for (int i=20, j=1; i< 50; i+=5, j++)
			list.add(j, i);

		showList(list);


		showListAfterDeleting(list, 4);
		showListAfterDeleting(list, 0);   // Test deleting at beginning
		showListAfterDeleting(list, 30);  // Test deleting at invalid index
		showListAfterDeleting(list, 7);   // Test deleting at end

		
		showListAfterAdding(list, 3, 700);
		showListAfterAdding(list, 0, 700);
		showListAfterAdding(list, 1, 800);
		showListAfterAdding(list, 2, 900);
		showListAfterAdding(list, 2, 1000);
		showListAfterAdding(list, 7, 1001);
		showListAfterAdding(list, 13, 1002);
		showListAfterAdding(list, 3, 1002);


		showListAfterReplacingElement(list, 0,  1800);
		showListAfterReplacingElement(list, 2,  999);
		showListAfterReplacingElement(list, 2,  2222);
		showListAfterReplacingElement(list, 1,  1511);
		showListAfterReplacingElement(list, list.size()-1, 404);
		
		/*********************************************************************
		 * ADD HERE ANY OTHER TESTS YOU MAY WANT TO DO FOR OTHER ADT METHODS *
		 *********************************************************************/

		/* Comparators using Class/Interface Implementations
		showListAfterSorting(list, new IntegerComparator1());
		showListAfterSorting(list, new IntegerComparator2());
		*/
		
		/* Comparators using Lambda Functions */
		showListAfterSorting(list, (a, b) -> b - a);
		showListAfterSorting(list, (a, b) -> a - b);
		
		//showListAfterReversingContent(list); // See easter egg below! It may serve as good practice...

	}


	private static void showElement(List<Integer> list, int position) {
		try {
			System.out.println(" --Element in position "
					+ position + " is: "+ list.get(position));
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}


	private static void showListAfterSorting(List<Integer> list, Comparator<Integer> cmp) {
		System.out.println("\n Sorted list is: ");
		list.sort(cmp); // TODO UNCOMMENT AFTER FINISHING EXERCISE 3
		showList(list);
	}
	
	private static void showSize(List<Integer> list) {
		System.out.println("\nSize of the list is: "+list.size());
	}
	private static void showList(List<Integer> list) {
		System.out.println("\n*** The " + list.size() + " elements in the list are: ");
		int lpindex = list.size();
		for (int i=0; i < lpindex; i++)
		    showElement(list,i);
	}

	private static void showListAfterDeleting(List<Integer> list, int pos) {
		System.out.println("\n -- deleting element at position " + pos);
		try {
			Integer etr = list.get(pos);
			if (list.remove(pos)) {
				System.out.println(" -- value of deleted element was " + etr);
				showList(list);
			}
			else
				System.out.println("ERROR: Was not able to delete element at position " + pos);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void showListAfterAdding(List<Integer> list, int pos, Integer element) {
		System.out.println("\n -- adding value " + element + " at position " + pos);
		try {
			list.add(pos, element);
			showList(list);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void showListAfterReplacingElement(List<Integer> list, int pos, Integer element) {
		System.out.println("\n -- replacing value at position " + pos + " by " + element);
		try {
			Integer etr = list.set(pos, element);
			System.out.println(" -- value of replaced element was "+ etr);
			showList(list);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/* Easter Egg: How would we go about doing this in a recursive linked list? Think!
	private static void showListAfterReversingContent(List<Integer> list) {
		System.out.println("\n -- reversing content of the list....");
		((RecursiveLinkedList<Integer>) list).reverseContent();
		showList(list);

	}
	*/

	/**************** Integer comparators as class declarations **************************
	/* UNCOMMENT IF YOU WISH TO USE THESE INSTEAD OF LAMBDA FUNCTIONS
	private static class IntegerComparator1 implements Comparator<Integer> {
		public int compare(Integer a, Integer b) {
			return b-a;
		}
	}

	private static class IntegerComparator2 implements Comparator<Integer> {
		public int compare(Integer a, Integer b) {
			return a-b;
		}
	}
	*/

}
