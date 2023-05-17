package lab7;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import lab7.interfaces.Set;
import lab7.problems.InRangeProblem;
import lab7.util.set.ArraySet;

public class InRangeProblemTest {

	InRangeProblem<Integer> p1, p2;
	Set<Integer> S1, S2;	
	Comparator<Integer> C;
	

	@Before
	public void setUp() throws Exception {
		
		C = (a, b) -> a.compareTo(b); 
		
		S1 = new ArraySet<Integer>();
		S1.add(8);
		S1.add(16);
		S1.add(1);
		S1.add(100);
		S1.add(0);

		S2 = new ArraySet<Integer>();
		S2.add(-1);
		S2.add(100);
		S2.add(20);
		S2.add(30);
		
		p1 = new InRangeProblem<>(S1, C);
		p2 = new InRangeProblem<>(S2, C);

	}

	@Test
	public void testEmptySets() {
		p1.getSet().clear();
		assertEquals("On empty set S1={}, fails to return empty set for inRange(0, 10).", true, p1.inRange(0,10).isEmpty());
	}

	@Test
	public void testOne() {
		assertEquals("On set S1={8, 16, 1, 100, 0}, fails to return set {8, 1, 0} for inRange(0, 10).", true, p1.inRange(0,10).isMember(8) &&
				p1.inRange(0,10).isMember(1) && p1.inRange(0,10).isMember(0));
	}

	@Test
	public void testTwo() {

		assertEquals("On   set S2={-1, 100, 20, 30}, fails to return set {-1} for inRange(-10, 0).", true, p2.inRange(-10,0).isMember(-1) &&
				(p2.inRange(-10,0).size() == 1));
	}

	@Test
	public void testThree() {
		assertEquals("On set S1={8, 16, 1, 100, 0}, fails to return set {16} for inRange(10, 20).", true, p1.inRange(10,20).isMember(16) &&
				(p1.inRange(10,20).size() == 1));
	}

	@Test
	public void testFour() {
		assertEquals("On set S1={8, 16, 1, 100, 0}, fails to return set {} for inRange(200, 300).", true, p1.inRange(200,300).isEmpty());
	}
}
