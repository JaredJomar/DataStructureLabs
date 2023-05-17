package lab5.problems;

import java.util.LinkedList;
import java.util.List;

import lab5.util.dataStructures.LinkedStack;
import lab5.util.interfaces.Stack;

public class FullyParensProblem {

	/********************************************************
	 * PLACE YOUR RESULTS AND INVALID EXPRESSIONS *
	 * AS YOU RECEIVE THEM IN YOUR PROGRAM IN THESE *
	 * 2 LINKED LISTS THAT ARE DECALRED FOR YOU. *
	 * *
	 * TESTS WILL FAIL IF YOU DO NOT STORE THE RESULTS *
	 * AND INVALID EXPRESSIONS AS YOU RECEIVE THEM OR *
	 * IF YOU DON'T USE THEM AND JUST PRINT OUT THE RESULTS *
	 * TO THE CONSOLE *
	 ********************************************************/

	private List<String> invalidExpressions;
	private List<String> results;
	private List<String> expressions;

	/***********************************************************
	 * DO NOT REMOVE THESE, YOU MUST USE THEM IN YOUR SOLUTION *
	 ***********************************************************/
	private Stack<Character> operators;
	private Stack<Integer> operands;
	private Stack<Character> parenthesis;

	/***************************************************
	 * ADD ANY PRIVATE FIELDS YOU MAY NEED TO USE HERE *
	 ***************************************************/

	public FullyParensProblem(List<String> expressions) {
		invalidExpressions = new LinkedList<>();
		results = new LinkedList<>();
		this.expressions = expressions;

		operands = new LinkedStack<>();
		operators = new LinkedStack<>();
		parenthesis = new LinkedStack<>();

		/*************************************************************
		 * ADD ANY ADDITIONAL FIELD INTIALIZATIONS YOU MAY NEED HERE *
		 *************************************************************/

	}

	public List<String> getInvalidExpressions() {
		return invalidExpressions;
	}

	public List<String> getResults() {
		return results;
	}

	public List<String> getExpressions() {
		return expressions;
	}

	/**
	 * TODO EXERCISE 3:
	 * 
	 * Write a method named fullyParens() that uses stacks to evaluate fully
	 * parenthesized expressions with variables.
	 * You should use 3 stacks: one for the operators, one for the parenthesis (or
	 * brackets or braces), and one for the operands.
	 * For this exercise, every time you see the word “parenthesis”, keep in mind
	 * that it may well be a bracket (“[“) or a brace (“{“).
	 * 
	 * There will only be two types of expressions:
	 * 
	 * 1) variableName=integerConstant
	 * 2) variableName:expression
	 * 
	 * You may assume that:
	 * 
	 * 1) Variable names will consist of a capital letter, so you’ll have at most 26
	 * variables.
	 * 2) We will be using only integer values.
	 * 3) There will be no spaces in the expressions.
	 * 
	 * WARNING: YOU MUST USE THREE STACKS, IMPLEMENTATIONS THAT DO NOT USE STACKS OR
	 * THAT ARE FORCED OUTPUTS WILL RECEIVE ZERO CREDIT
	 */
	public void fullyParens() {
		/* TODO ADD YOUR CODE HERE */
		for (String expression : expressions) {
			operators.clear();
			operands.clear();
			parenthesis.clear();

			boolean isValid = true;

			for (int i = 0; i < expression.length(); i++) {
				char c = expression.charAt(i);
				if (Character.isDigit(c)) {
					int operand = Character.getNumericValue(c);
					while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
						operand = operand * 10 + Character.getNumericValue(expression.charAt(i + 1));
						i++;
					}
					operands.push(operand);
				} else if (c == '+' || c == '-' || c == '*' || c == '/') {
					operators.push(c);
				} else if (c == '(') {
					parenthesis.push(c);
				} else if (c == ')') {
					if (parenthesis.isEmpty()) {
						isValid = false;
						break;
					}
					parenthesis.pop();

					if (operators.isEmpty() || operands.size() < 2) {
						isValid = false;
						break;
					}

					int operand2 = operands.pop();
					int operand1 = operands.pop();
					char operator = operators.pop();
					int result = 0;

					switch (operator) {
						case '+':
							result = operand1 + operand2;
							break;
						case '-':
							result = operand1 - operand2;
							break;
						case '*':
							result = operand1 * operand2;
							break;
						case '/':
							if (operand2 == 0) {
								isValid = false;
								break;
							}
							result = operand1 / operand2;
							break;
					}

					if (!isValid) {
						break;
					}

					operands.push(result);
				}
			}

			if (isValid && parenthesis.isEmpty() && operands.size() == 1 && operators.isEmpty()) {
				results.add(Integer.toString(operands.pop()));
			} else {
				invalidExpressions.add(expression);
			}
		}
	}

	/***********************************************
	 * ADD ANY AUXILIARY METHODS YOU MAY NEED HERE *
	 ***********************************************/
}
