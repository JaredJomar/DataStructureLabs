package lab5;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab5.problems.TicketProblem;

public class TicketProblemTest {
	
	TicketProblem p;
	
	@Before
	public void setUp() {
		p = new TicketProblem();
	}
	
	// Just one person in the line
	@Test
	public void test1() {
		int[] ticketNeeds = new int[] {5}; 
		int satisfiedCustomers = p.satisfiedCustomers(ticketNeeds, 10);
		assertEquals("FAILED: Didn't return 1 for ticket line {5} with available=10", 1, satisfiedCustomers);
	}
	// Not enough tickets short line
	@Test
	public void test2() {
		int[] ticketNeeds = new int[] {5, 6}; 
		int satisfiedCustomers = p.satisfiedCustomers(ticketNeeds, 10);
		assertEquals("FAILED: Didn't return 1 for ticket line {5, 6} with available=10", 1, satisfiedCustomers);
	}
	// Long line
	@Test
	public void test3() {
		int[] ticketNeeds = new int[] {1, 4, 3, 3, 2, 7, 5, 1, 2}; 
		int satisfiedCustomers = p.satisfiedCustomers(ticketNeeds, 24);
		assertEquals("FAILED: Didn't return 7 for ticket line {1, 4, 3, 3, 2, 7, 5, 1, 2} with available=24", 7, satisfiedCustomers);
	}
	// Lucky them
	@Test
	public void test4() {
		int[] ticketNeeds = new int[] {1, 9, 3, 5, 8, 9}; 
		int satisfiedCustomers = p.satisfiedCustomers(ticketNeeds, 20);
		assertEquals("FAILED: Didn't return 2 for ticket line {1, 9, 3, 5, 8, 9} with available=20", 2, satisfiedCustomers);
	}
	// Not so lucky them
	@Test
	public void test5() {
		int[] ticketNeeds = new int[] {1, 2, 4, 5, 3, 2}; 
		int satisfiedCustomers = p.satisfiedCustomers(ticketNeeds, 17);
		assertEquals("FAILED: Didn't return 6 for ticket line {1, 2, 4, 5, 3, 2} with available=17", 6, satisfiedCustomers);
	}
}
