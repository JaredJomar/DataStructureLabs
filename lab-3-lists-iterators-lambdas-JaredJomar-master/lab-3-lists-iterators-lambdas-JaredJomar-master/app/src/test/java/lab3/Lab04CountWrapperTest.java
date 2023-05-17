package lab3;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class Lab04CountWrapperTest {
	
	private Random r;
	private Lab04CountWrapper.List<String>[] inputs1;
	private Lab04CountWrapper.List<String>[] inputs2;
	private Lab04CountWrapper.List<String>[] inputs3;
	private Lab04CountWrapper.List<String>[] inputs4;
	private Lab04CountWrapper.List<String>[] inputs5;
	private String[] s;
	private String[] courses = {"CIIC3015", "CIIC4010", "CIIC4020", "CIIC4050", "CIIC4060", "CIIC4070", "CIIC3081", "CIIC4082", "CIIC4025", "CIIC4030"};

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		r = new Random();
		
		Lab04CountWrapper.List<String> L1 = new Lab04CountWrapper.ArrayList<>(5);
		Lab04CountWrapper.List<String> L2 = new Lab04CountWrapper.ArrayList<>(5);
		Lab04CountWrapper.List<String> L3 = new Lab04CountWrapper.ArrayList<>(5);
		Lab04CountWrapper.List<String> L4 = new Lab04CountWrapper.ArrayList<>(5);
		Lab04CountWrapper.List<String> L5 = new Lab04CountWrapper.ArrayList<>(5);
		
		inputs1 = new Lab04CountWrapper.ArrayList[5];
		
		inputs1[0] = L1;
		inputs1[1] = L2;
		inputs1[2] = L3;
		inputs1[3] = L4;
		inputs1[4] = L5;
		
		L1 = new Lab04CountWrapper.ArrayList<>(5);
		L2 = new Lab04CountWrapper.ArrayList<>(5);
		L3 = new Lab04CountWrapper.ArrayList<>(5);
		L4 = new Lab04CountWrapper.ArrayList<>(5);
		L5 = new Lab04CountWrapper.ArrayList<>(5);
		
		for (int i = 0; i < 4; i++) L1.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L2.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L3.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L4.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L5.add(courses[r.nextInt(courses.length)]);
		

		inputs2 = new Lab04CountWrapper.ArrayList[5];
		
		inputs2[0] = L1;
		inputs2[1] = L2;
		inputs2[2] = L3;
		inputs2[3] = L4;
		inputs2[4] = L5;
		
		L1 = new Lab04CountWrapper.ArrayList<>(5);
		L2 = new Lab04CountWrapper.ArrayList<>(5);
		L3 = new Lab04CountWrapper.ArrayList<>(5);
		L4 = new Lab04CountWrapper.ArrayList<>(5);
		L5 = new Lab04CountWrapper.ArrayList<>(5);
		
		for (int i = 0; i < 4; i++) L1.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L2.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L3.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L4.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L5.add(courses[r.nextInt(courses.length)]);
		
		inputs3 = new Lab04CountWrapper.ArrayList[5];
		
		inputs3[0] = L1;
		inputs3[1] = L2;
		inputs3[2] = L3;
		inputs3[3] = L4;
		inputs3[4] = L5;
		
		L1 = new Lab04CountWrapper.ArrayList<>(5);
		L2 = new Lab04CountWrapper.ArrayList<>(5);
		L3 = new Lab04CountWrapper.ArrayList<>(5);
		L4 = new Lab04CountWrapper.ArrayList<>(5);
		L5 = new Lab04CountWrapper.ArrayList<>(5);
		
		for (int i = 0; i < 4; i++) L1.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L2.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L3.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L4.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L5.add(courses[r.nextInt(courses.length)]);
		
		inputs4 = new Lab04CountWrapper.ArrayList[5];
		
		inputs4[0] = L1;
		inputs4[1] = L2;
		inputs4[2] = L3;
		inputs4[3] = L4;
		inputs4[4] = L5;
		
		L1 = new Lab04CountWrapper.ArrayList<>(5);
		L2 = new Lab04CountWrapper.ArrayList<>(5);
		L3 = new Lab04CountWrapper.ArrayList<>(5);
		L4 = new Lab04CountWrapper.ArrayList<>(5);
		L5 = new Lab04CountWrapper.ArrayList<>(5);
		
		for (int i = 0; i < 4; i++) L1.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L2.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L3.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L4.add(courses[r.nextInt(courses.length)]);
		for (int i = 0; i < 4; i++) L5.add(courses[r.nextInt(courses.length)]);
		
		inputs5 = new Lab04CountWrapper.ArrayList[5];
		
		inputs5[0] = L1;
		inputs5[1] = L2;
		inputs5[2] = L3;
		inputs5[3] = L4;
		inputs5[4] = L5;
	}
	
	@Test
	public void test1() {		
		assertTrue("Failed to return 0 on array of empty lists", Lab04CountWrapper.totalCount(inputs1, "test") == 0);
	}
	
	@Test
	public void test2() {
		int solution = solution(inputs2, "CIIC4020");
		boolean check = solution == Lab04CountWrapper.totalCount(inputs2, "CIIC4020");
		assertTrue("Failed to return " + solution + " on random array of lists of courses", check);
	}
	
	@Test
	public void test3() {
		int solution = solution(inputs3, "CIIC4050");
		boolean check = solution == Lab04CountWrapper.totalCount(inputs3, "CIIC4050");
		assertTrue("Failed to return " + solution + " on random array of lists of courses", check);
	}
	
	@Test
	public void test4() {
		int solution = solution(inputs4, "CIIC4060");
		boolean check = solution == Lab04CountWrapper.totalCount(inputs4, "CIIC4060");
		assertTrue("Failed to return " + solution + " on random array of lists of courses", check);
	}
	
	@Test
	public void test5() {
		int solution = solution(inputs5, "CIIC4010");
		boolean check = solution == Lab04CountWrapper.totalCount(inputs5, "CIIC4010");
		assertTrue("Failed to return " + solution + " on random array of lists of courses", check);
	}

	public static int solution(Lab04CountWrapper.List<String>[] listArray, String s) {
		int count = 0;
		for (Lab04CountWrapper.List<String> list : listArray) {
			for (String str : list) {
				if(str.equals(s)) count++;
			}
		}
		
		return count;
		
	}
}
