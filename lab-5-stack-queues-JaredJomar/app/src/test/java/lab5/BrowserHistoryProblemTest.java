package lab5;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lab5.problems.*;

public class BrowserHistoryProblemTest {
	
	BrowserHistoryProblem p;
	
	@Before
	public void setUp() {
		p = new BrowserHistoryProblem();
	}

	@Test
	public void test1() {
		String[] input = {"Google", "Facebook", "Instagram", "<", ">"};
		String[] expected = {"Google", "Facebook", "Instagram", "Facebook", "Instagram"};
		assertArrayEquals(expected, p.browserHistory(input));
	}

	@Test
	public void test2() {
		String[] input = {"Google", "Facebook", "Instagram", "<", "<", ">", "Wikipedia", "Reddit", "<", "<", ">"};
		String[] expected = {"Google", "Facebook", "Instagram", "Facebook", "Google", "Facebook", "Wikipedia", "Reddit", "Wikipedia", "Facebook", "Wikipedia"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
	@Test
	public void test3() {
		String[] input = {"Google", "Facebook", "<", "<"};
		String[] expected = {"Google", "Facebook", "Google"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
	@Test
	public void test4() {
		String[] input = {"Google", "Instagram", "<", "Wikipedia", ">"};
		String[] expected = {"Google", "Instagram", "Google", "Wikipedia"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
	@Test
	public void test5() {
		String[] input = {"Google", "Instagram", "Instagram", "Wikipedia", "<", "<"};
		String[] expected = {"Google", "Instagram", "Wikipedia", "Instagram", "Google"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
	@Test
	public void test6() {
		String[] input = {"Google", "Instagram", "Reddit", "Wikipedia", "<", "Reddit"};
		String[] expected = {"Google", "Instagram", "Reddit", "Wikipedia", "Reddit"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
	@Test
	public void test7() {
		String[] input = {"Google", "Instagram", "Reddit", "Wikipedia", "Reddit", "<", "<", "<"};
		String[] expected = {"Google", "Instagram", "Reddit", "Wikipedia", "Reddit", "Wikipedia", "Reddit", "Instagram"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
	@Test
	public void test8() {
		String[] input = {"Google","Instagram","Reddit","<","<",">",">"};
		String[] expected = {"Google", "Instagram", "Reddit", "Instagram", "Google", "Instagram", "Reddit"};
		assertArrayEquals(expected, p.browserHistory(input));
	}
	
}