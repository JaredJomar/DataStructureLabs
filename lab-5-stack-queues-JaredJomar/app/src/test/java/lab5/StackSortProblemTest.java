package lab5;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import lab5.problems.StackSortProblem;
import lab5.util.StudentRecord;

public class StackSortProblemTest {
	
	Random r;
	
	StackSortProblem<Integer> p;
	StackSortProblem<String> p2;
	StackSortProblem<StudentRecord> p3;

	private Integer[] example = {9, 11, 15, 11, 1, -1, 3, 11};
	private Integer[] test1 = {4, 0, 2, 0};
	private String[] test2;
	private StudentRecord[] test3;
	
	@Before
	public void setUp() {
		
		p = new StackSortProblem<Integer>();
		p2 = new StackSortProblem<String>();
		p3 = new StackSortProblem<StudentRecord>();
		
		r = new Random();
		
		test2 = generateRandomStringArray();
		
		test3 = new StudentRecord[6];
		
		test3[0] = new StudentRecord("Fernando J. Bermudez", 802180426, "CIIC");
		test3[1] = new StudentRecord("Yan Aquino", 802190130, "INSO");
		test3[2] = new StudentRecord("Maria A. Munoz", 802180892, "INSO");
		test3[3] = new StudentRecord("Jacob M. Delgado", 802200106, "CIIC");
		test3[4] = new StudentRecord("Roberto Arias", 802060975, "ICOM");
		test3[5] = new StudentRecord("Gretchen Bonilla", 802120133, "ICOM");
		
	}
	
	@Test
	public void testExample() {
		
		Comparable<Integer>[] result = p.stackSort(example); 
		Integer[] expected = Arrays.copyOf(example, example.length);
		Arrays.sort(expected);
		
		assertArrayEquals("Failed to return true on {9, 11, 15, 11, 1, -1, 3, 11}", result, expected);
		
	}
	
	@Test
	public void test1() {
		Comparable<Integer>[] result = p.stackSort(test1); 
		Integer[] expected = Arrays.copyOf(test1, test1.length);
		Arrays.sort(expected);
		
		assertArrayEquals("Failed to return true on {4, 0, 2, 0}", result, expected);
	}
	
	@Test
	public void test2() {
		Comparable<String>[] result = p2.stackSort(Arrays.copyOf(test2, test2.length));
		String[] expected = Arrays.copyOf(test2, test2.length);
		Arrays.sort(expected);
		
		assertArrayEquals("Failed to return true on " + toString(test2), result, expected);
	}
	
	@Test
	public void test3() {

		Comparable<StudentRecord>[] result = p3.stackSort(Arrays.copyOf(test3, test3.length));
		StudentRecord[] expected = Arrays.copyOf(test3, test3.length);
		Arrays.sort(expected);
		
		assertArrayEquals("Failed to return true on " + toString(test3), result, expected);
	}
	
	private static String generateRandomString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	private String[] generateRandomStringArray() {
		String[] result = new String[r.nextInt(20)];
		
		for (int i = 0; i < result.length; i++) 
			result[i] = generateRandomString();
		
		return result;
	}

	
	private <E> String toString(E[] a) {
		String result = "";
		
		result += "[";
		for (int i = 0; i < a.length - 1; i++)
			result += (a[i] instanceof StudentRecord 
							? toStringRecord((StudentRecord) a[i]) 
							: a[i]) 
									+ ", ";	
		result += (a[a.length - 1] instanceof StudentRecord 
				? toStringRecord((StudentRecord) a[a.length - 1]) 
				: a[a.length - 1]) 
						+ "]";	
		
		return result;
	}

	private String toStringRecord(StudentRecord e) {
		return "{" + e.getName() + ", " + e.getStudentID() + ", " + e.getMajor() + "}";
	}

}