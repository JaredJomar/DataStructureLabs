package lab5.problems;

import lab5.util.dataStructures.LinkedQueue;
import lab5.util.interfaces.Queue;

public class BinaryNumberSequenceProblem {

	private Queue<String> binarySequence;

	public BinaryNumberSequenceProblem() {
		/***************************************************
		 * TODO NOTE: YOU MUST IMPLEMENT THE LinkedQueue *
		 * FROM EXERCISE 4 BEFORE COMPLETING THIS EXERCISE *
		 ***************************************************/
		binarySequence = new LinkedQueue<>();
	}

	public Queue<String> getBinarySequence() {
		return this.binarySequence;
	}

	/**
	 * TODO EXERCISE 6:
	 * 
	 * Decimal numbers use the digits 0,1,2,3,5,6,7,8,9. We form decimal numbers
	 * creating strings
	 * with these digits.
	 * For example: 102, 88, 12. Binary numbers use only 0 and 1.
	 * We can create numbers by creating strings with 0s and 1s.
	 * 
	 * For example, 1 is expressed as 1, 2 is expressed as 10, 3 is expressed as 11.
	 * 
	 * The first 5 integers as 1, 10, 11, 100, 101.
	 * The first 8 integers are expressed as 1, 10, 11, 100, 101, 110, 111, 1000
	 * The first 16 integers are expressed as 1, 10, 11, 100, 101, 110, 111, 1000,
	 * 1001, 1010,
	 * 1011, 1100, 1101, 1110, 1111, 10000.
	 * 
	 * Write a non-member method binaryNumberSequence() that produces the sequence
	 * of the first N integers.
	 * The method receives as parameter the number N, and returns a Queue<String>
	 * with N strings
	 * representing the first N integers. The elements in the queue come in the
	 * sequence order.
	 * 
	 * You must store the results of the method in the queue provided as a private
	 * field.
	 * 
	 * Note: Use one of the Queue Implementations you created in a previous exercise
	 * 
	 * WARNING: You MUST create and store the binary number using division, mods, a
	 * queue, or something similar.
	 * Solutions that use Integer.toBinaryString(number) to avoid using doing so
	 * will receive ZERO credit.
	 */

	public void binaryNumberSequence(int N) {
		/* TODO ADD YOUR CODE HERE */
		int i = 1;
		while (i <= N) {
			int num = i;
			Queue<Integer> temp = new LinkedQueue<Integer>();
			while (num > 0) {
				temp.enqueue(num % 2);
				num = num / 2;
			}
			String bin = "";
			while (!temp.isEmpty()) {
				bin = temp.dequeue() + bin;
			}
			getBinarySequence().enqueue(bin);
			i++;
		}
	}
}
