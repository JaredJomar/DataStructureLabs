package lab4;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab4.problems.SinglyLinkedList;
import lab4.util.List;

public class LinkedListReverseTester {

	SinglyLinkedList<String> L;

	@Before
	public void setUp() throws Exception {
		L = new SinglyLinkedList<String>();
	}

	@Test
	public void testCase1() {
		L.clear();
		L.add("Bob");
		L.reverse();
		assertEquals("On L = {Bob}, L.reverse() fails to produce L = {Bob} ", 
				true, L.size() == 1 && L.contains("Bob"));
	}

	@Test
	public void testCase2() {
		L.clear();
		L.add("Bob");
		L.add("Jil");
		L.reverse();
		assertEquals("On L = {Bob, Jil}, L.reverse() fails to produce L = {Jil, Bob} ", 
				true, L.size() == 2 && L.get(0)=="Jil" && L.get(1)=="Bob");
	}


	@Test
	public void testCase3() {
		L.clear();
		L.add("Bob");
		L.add("Jil");
		L.add("Kim");
		L.reverse();
		assertEquals("On L = {Bob, Jil, Kim}, L.reverse() fails to produce L = {Kim, Jil, Bob} ", 
				true, L.size() == 3 && L.get(0)=="Kim" && L.get(1)=="Jil" && L.get(2)=="Bob");
	}

	@Test
	public void testCase4() {
		L.clear();
		L.add("Bob");
		L.add("Jil");
		L.add("Kim");
		L.add("Kim", 0);

		L.reverse();
		assertEquals("On L = {Kim, Bob, Jil, Kim}, L.reverse() fails to produce L = {Kim, Jil, Bob, Kim} ", 
				true, L.size() == 4 && L.get(0)=="Kim" && L.get(1)=="Jil" && L.get(2)=="Bob" && L.get(3)=="Kim");
	}


	@Test
	public void testCase5() {
		L.clear();
		L.add("Bob");
		L.add("Jil");
		L.add("Kim");
		L.add("Kim", 0);
		L.add("Joe");

		L.reverse();
		assertEquals("On L = {Kim, Bob, Jil, Kim, Joe}, L.reverse() fails to produce L = {Joe, Kim, Jil, Bob, Kim} ", 
				true, L.size() == 5 && L.get(0)=="Joe" && L.get(1)=="Kim" && L.get(2)=="Jil" && L.get(3)=="Bob" && L.get(4)=="Kim");
	}

}

