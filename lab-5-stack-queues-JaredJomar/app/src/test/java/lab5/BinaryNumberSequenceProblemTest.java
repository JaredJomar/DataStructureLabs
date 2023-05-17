package lab5;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lab5.problems.BinaryNumberSequenceProblem;
import lab5.util.interfaces.Queue;

public class BinaryNumberSequenceProblemTest {

	Queue<String> Q;
	BinaryNumberSequenceProblem p;
	
	@Before
	public void setUp() throws Exception {
		p = new BinaryNumberSequenceProblem();
	}

	@Test
	public void test1() {
		p.binaryNumberSequence(1);
		Q = p.getBinarySequence();
		assertEquals("On binaryNumberSequence(1) , method fails to return {1}. ", true, 
				Q.size() == 1 && Q.dequeue().equals("1"));
	}

	@Test
	public void test2() {
		p.binaryNumberSequence(2);
		Q = p.getBinarySequence();
		assertEquals("On binaryNumberSequence(2) , method fails to return {1, 10}. ", true, 
				Q.size() == 2 && Q.dequeue().equals("1") && Q.dequeue().equals("10"));
	}
	
	@Test
	public void test3() {
		p.binaryNumberSequence(4);
		Q = p.getBinarySequence();
		assertEquals("On binaryNumberSequence(4) , method fails to return {1, 10, 11, 100}. ", true, 
				Q.size() == 4 && Q.dequeue().equals("1") && Q.dequeue().equals("10") 
				&& Q.dequeue().equals("11")  && Q.dequeue().equals("100"));
	}

	@Test
	public void test4() {
		p.binaryNumberSequence(5);
		Q = p.getBinarySequence();
		assertEquals("On binaryNumberSequence(5) , method fails to return {1, 10, 11, 100, 101}. ", true, 
				Q.size() == 5 && Q.dequeue().equals("1") && Q.dequeue().equals("10") 
				&& Q.dequeue().equals("11")  && Q.dequeue().equals("100") && Q.dequeue().equals("101"));
		
		
	}
	
	@Test
	public void test5() {
		p.binaryNumberSequence(6);
		Q = p.getBinarySequence();
		assertEquals("On binaryNumberSequence(6) , method fails to return {1, 10, 11, 100, 101, 110}. ", true, 
				Q.size() == 6 && Q.dequeue().equals("1") && Q.dequeue().equals("10") 
				&& Q.dequeue().equals("11")  && Q.dequeue().equals("100") && Q.dequeue().equals("101")
				&& Q.dequeue().equals("110"));
	}

	@Test
	public void test6() {
		p.binaryNumberSequence(7);
		Q = p.getBinarySequence();
		assertEquals("On binaryNumberSequence(7) , method fails to return {1, 10, 11, 100, 101, 110, 111}. ", true, 
				Q.size() == 7 && Q.dequeue().equals("1") && Q.dequeue().equals("10") 
				&& Q.dequeue().equals("11")  && Q.dequeue().equals("100") && Q.dequeue().equals("101")
				&& Q.dequeue().equals("110") && Q.dequeue().equals("111"));
	}

}
