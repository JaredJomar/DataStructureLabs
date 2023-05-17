package lab5.problems;

import lab5.util.dataStructures.LinkedQueue;
import lab5.util.interfaces.Queue;

public class TicketProblem {

	/**
	 * TODO EXERCISE 7:
	 * 
	 * Assume we have a line of N people waiting to buy a ticket.
	 * The person at position 0 is in front and the person at position N-1 is at the
	 * end of the line.
	 * 
	 * Implement the non-member method satisfiedCustomers(int[ ] ticketsNeeds, int
	 * available).
	 * You will receive as a parameter the integer array tickets of length N, where
	 * tickets[i] represents the amount of tickets person i would like to buy.
	 * You will also receive the parameter available which represents the total
	 * number of tickets available for sell.
	 * 
	 * The method returns the total number of people that were able to buy all the
	 * tickets they needed.
	 * Each person takes exactly 1 second to buy a ticket and a person can only buy
	 * 1 ticket at a time.
	 * Once a person buys a single ticket they go to the back of the line so that
	 * they can buy more tickets.
	 * If a person no longer needs to buy a ticket they will leave the line.
	 * 
	 * WARNING: You MUST use a Queue, any implementation that doesn't use one will
	 * receive ZERO credit.
	 * 
	 * @param ticketNeeds      - People in line wanting to buy ticketNeeds[i] number
	 *                         of tickets
	 * @param availableTickets - Number of remaining tickets
	 * @return How may customers bought all the tickets they wanted
	 */
	public int satisfiedCustomers(int[] ticketNeeds, int availableTickets) {
		/* ADD YOUR CODE HERE */
		Queue<Integer> line = new LinkedQueue<>();
		for (int i = 0; i < ticketNeeds.length; i++) {
			line.enqueue(ticketNeeds[i]);
		}
		int satisfiedCustomers = 0;
		while (!line.isEmpty() && availableTickets != 0) {
			if (line.front() == 0) {
				line.dequeue();
			} else {
				int tickets = line.front();
				tickets--;
				if (tickets == 0) {
					satisfiedCustomers++;
				}
				line.dequeue();
				line.enqueue(tickets);
				availableTickets--;
			}
		}
		return satisfiedCustomers;
	}
}
