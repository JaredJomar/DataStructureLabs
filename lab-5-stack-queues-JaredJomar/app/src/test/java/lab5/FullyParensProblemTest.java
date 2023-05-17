package lab5;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lab5.problems.FullyParensProblem;

public class FullyParensProblemTest {
	
	FullyParensProblem p1, p2, p3, p4;
	
	List<String> test1, test1Invalid, test1Results;
	List<String> test2, test2Invalid, test2Results;
	List<String> test3, test3Invalid, test3Results;
	List<String> test4, test4Invalid, test4Results;
	
	@Before
	public void setUp() {

		test1 = new ArrayList<>();
		test1Invalid = new ArrayList<>();
		test1Results = new ArrayList<>();
		
		test1.add("L=5");
		test1.add("W=10");
		test1.add("A:(L*W)"); // A=50
		test1.add("X=100");
		test1.add("L:[X-W]"); // L=90
		test1.add("L:(A-X))");
		test1.add("L:((A-X)");
		test1.add("J:([X-(A/W])*L)");
		test1.add("J:([X-(A/W)]*L)"); //J=8550
		test1.add("W:A+X");
		test1.add("L:((L+A)*W)");
		
		test1Invalid.add("L:(A-X))");
		test1Invalid.add("L:((A-X)");
		test1Invalid.add("J:([X-(A/W])*L)");
		test1Invalid.add("W:A+X");

		test1Results.add("A=50");
		test1Results.add("J=8550");
		test1Results.add("L=1400");
		test1Results.add("W=10");
		test1Results.add("X=100");
		
		test2 = new ArrayList<>();
		test2Invalid = new ArrayList<>();
		test2Results = new ArrayList<>();
		
		test2.add("L=50");
		test2.add("W=30");
		test2.add("A:(L+W)");
		test2.add("X=2022");
		test2.add("L:[X*W]");
		test2.add("L:(A-W)]");
		test2.add("L:([A-W]");
		test2.add("J:([X-(A/W]*L)");
		test2.add("J:([X-(A/W)]*L)");
		test2.add("W:A+X");
		test2.add("L:((L+A)*W)");
		
		test2Invalid.add("L:(A-W)]");
		test2Invalid.add("L:([A-W]");
		test2Invalid.add("J:([X-(A/W]*L)");
		test2Invalid.add("W:A+X");

		test2Results.add("A=80");
		test2Results.add("J=122533200");
		test2Results.add("L=1822200");
		test2Results.add("W=30");
		test2Results.add("X=2022");
		
		test3 = new ArrayList<>();
		test3Invalid = new ArrayList<>();
		test3Results = new ArrayList<>();
		
		test3.add("L=2021");
		test3.add("W=2022");
		test3.add("A:(L-W)");
		test3.add("X=2023");
		test3.add("L:[X/W]");
		test3.add("L:{A-W}]");
		test3.add("L:{[A-W]");
		test3.add("J:({W+(A/X}*L)");
		test3.add("J:([X-(A/W)]*X)");
		test3.add("W:J+X");
		test3.add("L:((X+A)*W)");
		
		test3Invalid.add("L:{A-W}]");
		test3Invalid.add("L:{[A-W]");
		test3Invalid.add("J:({W+(A/X}*L)");
		test3Invalid.add("W:J+X");

		test3Results.add("A=-1");
		test3Results.add("J=4092529");
		test3Results.add("L=4088484");
		test3Results.add("W=2022");
		test3Results.add("X=2023");
		
		test4 = new ArrayList<>();
		test4Invalid = new ArrayList<>();
		test4Results = new ArrayList<>();
		
		test4.add("C=4020");
		test4.add("I=4035");
		test4.add("I:(C*(I/{I-C}))");
		test4.add("C:{[I-I]/C}");
		test4.add("A:{I+({C/(I-C)}*C]}");
		test4.add("A:{I+({C/(I-C)}*C)}");
		test4.add("B:<A-C>");
		test4.add("B=4050");
		test4.add("D:{B+(I-[A*(C/B>])}");
		test4.add("D:{B+(I-[A*(C/B)])}");
		test4.add("E=-1");
		test4.add("F:{B+E}");
		test4.add("G:[B-E]");
		test4.add("H:(B*E)");
		test4.add("T:{B/E)");
		
		test4Invalid.add("A:{I+({C/(I-C)}*C]}");
		test4Invalid.add("B:<A-C>");
		test4Invalid.add("D:{B+(I-[A*(C/B>])}");
		test4Invalid.add("T:{B/E)");

		test4Results.add("A=1081380");
		test4Results.add("B=4050");
		test4Results.add("C=0");
		test4Results.add("D=1085430");
		test4Results.add("E=-1");
		test4Results.add("F=4049");
		test4Results.add("G=4051");
		test4Results.add("H=-4050");
		test4Results.add("I=1081380");
		
		p1 = new FullyParensProblem(test1);
		p2 = new FullyParensProblem(test2);
		p3 = new FullyParensProblem(test3);
		p4 = new FullyParensProblem(test4);
	}
	
	@Test
	public void test1() {
		p1.fullyParens();
		boolean assertion = p1.getResults().equals(test1Results) && p1.getInvalidExpressions().equals(test1Invalid);
		assertTrue("Failed to execute fullyParens on Test 1 inputs", assertion);
	}
	
	@Test
	public void test2() {
		p2.fullyParens();
		boolean assertion = p2.getResults().equals(test2Results) && p2.getInvalidExpressions().equals(test2Invalid);
		assertTrue("Failed to execute fullyParens on Test 2 inputs", assertion);
	}
	
	@Test
	public void test3() {
		p3.fullyParens();
		boolean assertion = p3.getResults().equals(test3Results) && p3.getInvalidExpressions().equals(test3Invalid);
		assertTrue("Failed to execute fullyParens on Test 3 inputs", assertion);
	}
	
	@Test
	public void test4() {
		p4.fullyParens();
		boolean assertion = p4.getResults().equals(test4Results) && p4.getInvalidExpressions().equals(test4Invalid);
		assertTrue("Failed to execute fullyParens on Test 4 inputs", assertion);
	}
}
