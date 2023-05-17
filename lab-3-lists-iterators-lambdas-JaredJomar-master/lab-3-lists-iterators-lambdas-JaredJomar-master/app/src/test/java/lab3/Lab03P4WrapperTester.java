package lab3;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class Lab03P4WrapperTester {

	Lab03P4Wrapper.List<Integer> testFilter;
	Lab03P4Wrapper.List<Integer> testMap;
	Lab03P4Wrapper.List<Character> testFirstLastIndexOf;

	String test3;

	Random r;

	@Before
	public void setUp() throws Exception {

		r = new Random();
		int numElements = r.nextInt(20);

		test3 = generateString();
		testFirstLastIndexOf = new Lab03P4Wrapper.ArrayList<Character>(test3.length());
		testFilter = new Lab03P4Wrapper.ArrayList<Integer>();
		testMap = new Lab03P4Wrapper.ArrayList<Integer>(); 

		for (int i = 0; i < numElements + 1; i++) testFilter.add(r.nextInt(10));
		for (int i = 0; i < numElements + 1; i++) testMap.add(r.nextInt(10));
		for (char c : test3.toCharArray()) testFirstLastIndexOf.add(c);
	}

	@Test
	public void testFilter() {
		Lab03P4Wrapper.List<Integer> expectedResult, result;
		
		expectedResult = filterSol(x -> x != 1);
		result = testFilter.filter((x) -> x != 1);
		
		boolean tester = equals(expectedResult, result);

		String expectedString = "";
		String input = "";
		String got = "";

		for (Integer integer : testFilter) input += integer + ", ";
		for (Integer integer : expectedResult) expectedString += integer + ", ";
		for(Integer integer : result) got += integer + " ";

		assertTrue("Failed to filter "+ input + "expected " + expectedString  + ", got " + got, tester);
	}

	@Test
	public void testMap() {

		Lab03P4Wrapper.List<Integer> expectedResult, result;
		
		expectedResult = mapSol(x -> x * 10);
		result = testMap.map(x -> x * 10);

		boolean tester = equals(expectedResult, result);
		
		String expectedString = "";
		String input = "";
		String got = "";

		for (Integer integer : testMap) input += integer + " ";
		for (Integer integer : expectedResult) expectedString += integer + " ";
		for(Integer integer : result) got += integer + " ";

		assertTrue("Failed to apply map(x -> x * 10) on "+ input + "expected " + expectedString + ", got " + got, tester);	
	}

	@Test
	public void testFirstIndex() {
		
		char randomChar = test3.charAt(r.nextInt(test3.length()));
		
		int expectedResult = firstIndexSol(x -> x == randomChar);
		int result = testFirstLastIndexOf.firstIndex((x) -> x == randomChar);
		
		boolean tester = expectedResult == result;
		
		assertTrue("Failed to find first index of " + randomChar + " in " + test3.toString() + ", expected " + expectedResult, tester);
	}

	@Test
	public void testLastIndex() {
		
		char randomChar = test3.charAt(r.nextInt(test3.length()));
		
		int expectedResult = lastIndexSol(x -> x == randomChar);
		int result = testFirstLastIndexOf.lastIndex((x) -> x == randomChar);
	
		boolean tester = expectedResult == result;
		
		assertTrue("Failed to find last index of " + randomChar + " in " + test3.toString() + ", expected " + expectedResult, tester);
	}

	private static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public int firstIndexSol(Lab03P4Wrapper.FirstIndexLambda<Character> l) {
		for (int i = 0; i < testFirstLastIndexOf.size(); i++) {
			if (l.firstIndex(testFirstLastIndexOf.get(i))) {
				return i;
			}
		}	
		return -1;
	}


	public int lastIndexSol(Lab03P4Wrapper.LastIndexLambda<Character> l) {
		for (int i = testFirstLastIndexOf.size() - 1; i >= 0; i--) {
			if (l.lastIndex(testFirstLastIndexOf.get(i))) {
				return i;
			}
		}
		return -1;
	}

	public Lab03P4Wrapper.List<Integer> filterSol(Lab03P4Wrapper.LambdaFilter<Integer> l) {
		Lab03P4Wrapper.ArrayList<Integer> filteredList = new Lab03P4Wrapper.ArrayList<>();
		for (int i = 0; i < testFilter.size(); i++) {
			if(l.filter(testFilter.get(i))) {
				filteredList.add(testFilter.get(i));
			}
		}

		return filteredList;
	}

	public Lab03P4Wrapper.List<Integer> mapSol(Lab03P4Wrapper.LambdaMap<Integer> l) {
		Lab03P4Wrapper.ArrayList<Integer> result = new Lab03P4Wrapper.ArrayList<Integer>();
		for (int i = 0; i < testMap.size(); i++) {
			result.add(l.map(testMap.get(i)));
		}
		return result;
	}
	
	private <E> boolean equals(Lab03P4Wrapper.List<E> expectedResult, Lab03P4Wrapper.List<E> result) {
		int i = 0, j = 0;
		
		while(i < expectedResult.size() && j < result.size()) {
			if(!expectedResult.get(i).equals(result.get(j))) {
				return false;
			}
			
			i++;
			j++;
		}
		
		return true;
	}
}