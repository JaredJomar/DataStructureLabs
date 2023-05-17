package lab3;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class Lab03P5WrapperTest {
	
	private class StudentRecord implements Comparable<StudentRecord>{
		private String name;
		private int studentId;
		private String major;
		
		public StudentRecord(String name, int studentId, String major) {
			this.name = name;
			this.studentId = studentId;
			this.major = major;
		}

		public String getName() {
			return name;
		}

		public int getStudentId() {
			return studentId;
		}
		
		public String getMajor() {
			return major;
		}

		@Override
		public int compareTo(StudentRecord o) {
			return -1;
		}
	}
	
	private Lab03P5Wrapper.List<Integer> test1; 
	private Lab03P5Wrapper.List<String> test2; 
	private Lab03P5Wrapper.List<StudentRecord> test3; 
	private Lab03P5Wrapper.List<StudentRecord> test4; 
	private Lab03P5Wrapper.List<StudentRecord> test5; 
	
	private Comparator<Integer> cmp; 
	private Comparator<StudentRecord> cmp3;
	private Comparator<StudentRecord> cmp4;
	private Comparator<StudentRecord> cmp5;
	
	private String[] names = {"Gretchen Bonilla", "Roberto Arias", "Fernando Bermudez", "Yan Aquino", "Maria Alejandra", "Jacob Delgado"};
	private int[] ids = {802121234, 802093456, 802180426, 802197584, 802180578, 802201234};
	private String[] majors = {"ICOM", "ICOM", "CIIC", "INSO", "INSO", "CIIC"};
	
	private static final int DEFAULT_SIZE = 10;
	
	@Before
	public void setUp() {
		
		Random r = new Random();
		
		cmp = (o1, o2) -> o2 - o1;
		
		cmp3 = new Comparator<StudentRecord>() {
			@Override
			public int compare(StudentRecord r1, StudentRecord r2) {
				return r1.getStudentId() - r2.getStudentId();
			}
		};
		
		cmp4 = new Comparator<StudentRecord>() {
			@Override
			public int compare(StudentRecord r1, StudentRecord r2) {
				return r1.getName().compareTo(r2.getName());
			}
		};
		
		cmp5 = (o1, o2) -> o1.getMajor().compareTo(o2.getMajor());
		
		test1 = new Lab03P5Wrapper.ArrayList<>(DEFAULT_SIZE, cmp);
		test2 = new Lab03P5Wrapper.ArrayList<>(DEFAULT_SIZE);
		test3 = new Lab03P5Wrapper.ArrayList<>(DEFAULT_SIZE, cmp3);
		test4 = new Lab03P5Wrapper.ArrayList<>(DEFAULT_SIZE, cmp4);
		test5 = new Lab03P5Wrapper.ArrayList<>(DEFAULT_SIZE, cmp5);
		
		for (int i = 0; i < 10; i++) test1.add(r.nextInt(10));
		for (int i = 0; i < 10; i++) test2.add(generateString());
		for(int i = 0; i < 6; i++) test3.add(new StudentRecord(names[i], ids[i], majors[i]));
		for(int i = 0; i < 6; i++) test4.add(new StudentRecord(names[i], ids[i], majors[i]));
		for(int i = 0; i < 6; i++) test5.add(new StudentRecord(names[i], ids[i], majors[i]));
	}

	@Test
	public void test1() {
		Lab03P5Wrapper.List<Integer> expectedSolution = sort(test1, test1.getComparator());
		Lab03P5Wrapper.List<Integer> gottenSolution = Lab03P5Wrapper.sort(test1, test1.getComparator());
		
		String inputList = toString(test1);
		String expected = toString(expectedSolution);
		String got = toString(gottenSolution);
		
		assertTrue("Failed to sort " + inputList  + " in descending order. Expected " + expected + ", got " + got, expected.equals(got));
	}
	
	@Test
	public void test2() {
		Lab03P5Wrapper.List<String> expectedSolution = sort(test2, test2.getComparator());
		Lab03P5Wrapper.List<String> gottenSolution = Lab03P5Wrapper.sort(test2, test2.getComparator());
		
		String inputList = toString(test2);
		String expected = toString(expectedSolution);
		String got = toString(gottenSolution);
		
		assertTrue("Failed to sort " + inputList  + ". Expected " + expected + ", got " + got, expected.equals(got));

	}
	
	@Test
	public void test3() {
		Lab03P5Wrapper.List<StudentRecord> expectedSolution = sort(test3, test3.getComparator());
		Lab03P5Wrapper.List<StudentRecord> gottenSolution = Lab03P5Wrapper.sort(test3, test3.getComparator());
		
		String inputList = toString(test2);
		String expected = toString(expectedSolution);
		String got = toString(gottenSolution);
		
		assertTrue("Failed to sort " + inputList  + ". Expected " + expected + ", got " + got, expected.equals(got));

	}
	
	@Test
	public void test4() {
		Lab03P5Wrapper.List<StudentRecord> expectedSolution = sort(test4, test4.getComparator());
		Lab03P5Wrapper.List<StudentRecord> gottenSolution = Lab03P5Wrapper.sort(test4, test4.getComparator());
		
		String inputList = toString(test2);
		String expected = toString(expectedSolution);
		String got = toString(gottenSolution);
		
		assertTrue("Failed to sort " + inputList  + ". Expected " + expected + ", got " + got, expected.equals(got));
	}
	
	@Test
	public void test5() {
		Lab03P5Wrapper.List<StudentRecord> expectedSolution = sort(test5, test5.getComparator());
		Lab03P5Wrapper.List<StudentRecord> gottenSolution = Lab03P5Wrapper.sort(test5, test5.getComparator());
		
		String inputList = toString(test2);
		String expected = toString(expectedSolution);
		String got = toString(gottenSolution);
		
		assertTrue("Failed to sort " + inputList  + ". Expected " + expected + ", got " + got, expected.equals(got));
	}
	
	private static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	private static <E> String toString(Lab03P5Wrapper.List<E> l) {
		String result = "[";
		for(int i = 0; i < l.size() - 1; i++) result += l.get(i) + ", ";
		result += l.get(l.size() - 1) + "]";
		return result;
	}
	
	public static <E extends Comparable<E>> Lab03P5Wrapper.List<E> sort(Lab03P5Wrapper.List<E> dataSet, Comparator<E> comparator) {
		if(dataSet.size() == 1) 
			return dataSet;
		
		Lab03P5Wrapper.List<E> left = new Lab03P5Wrapper.ArrayList<>();
		Lab03P5Wrapper.List<E> right = new Lab03P5Wrapper.ArrayList<>();
		
		for(int i = 0; i < dataSet.size() / 2; i++) 
			left.add(dataSet.get(i));
	
		for(int i = dataSet.size() / 2; i < dataSet.size(); i++) 
			right.add(dataSet.get(i));
		
		left = sort(left, comparator);
		right = sort(right, comparator);
		
		return merge(left, right, comparator);

	}

	private static <E extends Comparable<E>> Lab03P5Wrapper.List<E> merge(Lab03P5Wrapper.List<E> left, Lab03P5Wrapper.List<E> right, Comparator<E> comparator) {
		int i = 0, j = 0;
		Lab03P5Wrapper.List<E> result = new Lab03P5Wrapper.ArrayList<>();
				while(i < left.size() && j < right.size()) {
			if(comparator.compare(left.get(i), right.get(j)) <= 0) result.add(left.get(i++));
			else result.add(right.get(j++));
		}
		
		while(i < left.size()) result.add(left.get(i++));
		while(j < right.size()) result.add(right.get(j++));
		
		return result;
	}
}