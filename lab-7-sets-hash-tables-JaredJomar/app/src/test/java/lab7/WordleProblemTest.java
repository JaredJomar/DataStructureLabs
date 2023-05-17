package lab7;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lab7.problems.WordleProblem;

public class WordleProblemTest {

	WordleProblem p;

	@Before
	public void setUp() {
		p = new WordleProblem();
	}

	@Test
	public void test1() {
		assertTrue("Failed to return YBBGG on guess RAMPS of secret word PROPS", p.wordle("RAMPS", "PROPS").equals("YBBGG"));
	}

	@Test
	public void test2() {
		assertTrue("Failed to return YBGBY on guess APPLE of secret word CAPER", p.wordle("APPLE", "CAPER").equals("YBGBY"));
	}

	@Test
	public void test3() {
		assertTrue("Failed to return BYBGY on guess MEETS of secret word SPITE", p.wordle("MEETS", "SPITE").equals("BYBGY"));
	}

	@Test
	public void test4() {
		assertTrue("Failed to return YYBBB on guess EERIE of secret word STEEP", p.wordle("EERIE", "STEEP").equals("YYBBB"));
	}

	@Test
	public void test5() {
		assertTrue("Failed to return BBYYB on guess STEEP of secret word EERIE", p.wordle("STEEP", "EERIE").equals("BBYYB"));
	}

}
