
/**
 * Snakes and Ladders is a board game in which players roll a 6-sided die to
 * determine how many squares forward they move on their turn, in a race to the
 * finish. Some special squares are the start of ladders, and if a player ends
 * on that square they move forward to the end of the ladder; other squares are
 * the start of snakes, and if a player ends on that square they move back to
 * the end of the snake.
 * (Image) (Image) (Image)
 * An example Snakes and Ladders board is shown above, with 1 snake and 1
 * ladder. The spaces are numbered in a zig-zag pattern beginning from 0. The
 * player begins at space 0 and wins when they reach the last space, in this
 * case 29. After the first roll, the player could be at one of 6 spaces. In
 * this case, the player would get an advantage if they rolled a 1 since that
 * would automatically move them ahead to the end of the ladder.
 * (Image) (Image)
 * One optimal set of rolls would be rolling 1, 6, 3, 5, since that would put
 * the player at space 29 in just 4 rolls. There are other combinations of 4
 * rolls that would win, but it's not possible to do better than 4 for this
 * board.
 * If the space is a normal space with no snake and no ladder, it will be a -1.
 * Since each space on the board is numbered from start to finish, we'll
 * "unwind" the 2-dimensional board store all the data in a 1-dimensional list.
 * In this case, the board would have 30 items, all -1's except the ladder at 1
 * with a value of 14 and the snake at 20 with a value of 4.
 * (Image)
 * →
 * (Image)
 * Write a method called minimumMoves() that takes a List<Integer> called board
 * and returns an integer. Said integer will represent the minimum number of
 * rolls needed to get to the final space on the board. The method returns -1 if
 * it's not possible to win.
 * You can assume the following things:
 * The given board has at least 1 space
 * The player begins at 0
 * The player ends at n-1, where n is the length of board
 * If a ladder/snake ends at the start of another ladder/snake, you do not
 * automatically take the second ladder/snake (i.e. no combos)
 * Examples
 * A call to minimumMoves(board), where board = {-1, -1, -1, 7, -1, -1, -1, -1}
 * should return 1, since rolling a 3 would allow the player to use a ladder to
 * get to the space 7.
 * A call to minimumMoves(board), where board = {-1, -1, -1, -1, -1, -1, -1, -1}
 * should return 2, since an extra roll is required to get to space 7 when
 * there's no ladder. A valid winning combo would be 6, 1.
 */

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakesLaddersWrapper {

    public static class Coordinate {

        private int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Coordinate c = (Coordinate) obj;
            return c.getX() == getX() && c.getY() == getY();
        }

    }

    public static interface Queue<E> {
        public void enqueue(E newEntry);

        public E dequeue();

        public E front();

        public boolean isEmpty();

        public int size();

        public void clear();
    }

    public static class EmptyQueueException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public EmptyQueueException(String s) {
            super(s);
        }

        public EmptyQueueException() {
            super();
        }

    }

    public static class DoublyLinkedQueue<E> implements Queue<E> {

        private List<E> list;

        public DoublyLinkedQueue() {
            list = new DoublyLinkedList<>();
        }

        @Override
        public void enqueue(E newEntry) {
            list.add(size(), newEntry);
        }

        @Override
        public E dequeue() {
            E elm = front();
            list.remove(0);
            return elm;
        }

        @Override
        public E front() {
            if (isEmpty())
                throw new EmptyQueueException();
            return list.get(0);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public void clear() {
            list.clear();
        }

    }

    public static interface List<E> extends Iterable<E> {

        public void add(E obj);

        public void add(int index, E obj);

        public boolean remove(E obj);

        public boolean remove(int index);

        public int removeAll(E obj);

        public E get(int index);

        public E set(int index, E obj);

        public E first();

        public E last();

        public int firstIndex(E obj);

        public int lastIndex(E obj);

        public int size();

        public boolean isEmpty();

        public boolean contains(E obj);

        public void clear();

        public int replaceAll(E e, E f);

        public List<E> reverse();

        public boolean equals(List<E> l);
    }

    public static class DoublyLinkedList<E> implements List<E> {

        private class Node {
            private E value;
            private Node next, prev;

            public Node(E value, Node next, Node prev) {
                this.value = value;
                this.next = next;
                this.prev = prev;
            }

            public Node(E value) {
                this(value, null, null); // Delegate to other constructor
            }

            public Node() {
                this(null, null, null); // Delegate to other constructor
            }

            public E getValue() {
                return value;
            }

            public void setValue(E value) {
                this.value = value;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public Node getPrev() {
                return prev;
            }

            public void setPrev(Node prev) {
                this.prev = prev;
            }

            public void clear() {
                value = null;
                next = prev = null;
            }
        } // End of Node class

        private class ListIterator implements Iterator<E> {

            private Node nextNode;

            public ListIterator() {
                nextNode = header.getNext();
            }

            @Override
            public boolean hasNext() {
                return nextNode != trailer;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E val = nextNode.getValue();
                    nextNode = nextNode.getNext();
                    return val;
                } else
                    throw new NoSuchElementException();
            }

        } // End of ListIterator class

        /* private fields */
        private Node header, trailer; // "dummy" nodes
        private int currentSize;

        public DoublyLinkedList() {
            header = new Node();
            trailer = new Node();
            header.setNext(trailer);
            trailer.setPrev(header);
            currentSize = 0;
        }

        public DoublyLinkedList(E[] elements) {
            this();
            for (E e : elements)
                add(e);
        }

        @Override
        public Iterator<E> iterator() {
            return new ListIterator();
        }

        @Override
        public void add(E obj) {
            Node nextNode = this.trailer;
            Node prevNode = this.trailer.getPrev();
            Node newNode = new Node(obj, nextNode, prevNode);
            nextNode.setPrev(newNode);
            prevNode.setNext(newNode);

            currentSize++;
        }

        @Override
        public void add(int index, E obj) {
            Node curNode, newNode;
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();
            if (index == size())
                add(obj); // Use our "append" method
            else {
                curNode = get_node(index - 1);
                newNode = new Node(obj, curNode.getNext(), curNode);
                curNode.getNext().setPrev(newNode);
                curNode.setNext(newNode);

                currentSize++;
            }
        }

        @Override
        public boolean remove(E obj) {
            Node curNode = header;
            Node nextNode = curNode.getNext();
            Node replacementNode = nextNode.getNext();

            // Traverse the list until we find the element or we reach the end
            while (nextNode != trailer && !nextNode.getValue().equals(obj)) {
                curNode = nextNode;
                nextNode = nextNode.getNext();
            }

            // Need to check if we found it
            if (nextNode != trailer) { // Found it!
                curNode.setNext(replacementNode);
                replacementNode.setPrev(curNode);
                nextNode.clear(); // free up resources
                currentSize--;
                return true;
            } else
                return false;
        }

        @Override
        public boolean remove(int index) {
            Node rmNode;
            // First confirm index is a valid position
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            // If we have A <-> B <-> C, need to get to A <-> C
            rmNode = get_node(index); // Get the node that is to be removed

            Node prevNode = rmNode.getPrev();
            Node nextNode = rmNode.getNext();

            nextNode.setPrev(prevNode);
            prevNode.setNext(nextNode);
            rmNode.clear();
            currentSize--;

            return true;
        }

        /* Private method to return the node at position index */
        private Node get_node(int index) {
            Node curNode;

            /*
             * First confirm index is a valid position
             * Allow -1 so that header node may be returned
             */
            if (index < -1 || index >= size())
                throw new IndexOutOfBoundsException();
            curNode = header;
            // Since first node is pos 0, let header be position -1
            for (int curPos = -1; curPos < index; curPos++)
                curNode = curNode.getNext();
            // Perhaps we could traverse backwards instead if index > size/2...
            return curNode;
        }

        @Override
        public int removeAll(E obj) {
            int counter = 0;
            Node curNode = header;
            Node nextNode = curNode.getNext();

            // Traverse the entire list
            while (nextNode != trailer) {
                if (nextNode.getValue().equals(obj)) {
                    // Remove nextNode

                    nextNode.getNext().setPrev(nextNode.getPrev());
                    nextNode.getPrev().setNext(nextNode.getNext());

                    nextNode.clear();
                    currentSize--;
                    counter++;
                    /*
                     * Node that was pointed to by nextNode no longer exists
                     * so reset it such that it's still the node after curNode
                     */
                    nextNode = curNode.getNext();
                } else {
                    curNode = nextNode;
                    nextNode = nextNode.getNext();
                }
            }
            return counter;
        }

        @Override
        public E get(int index) {
            // get_node allows for index to be -1, but we don't want get to allow that
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            return get_node(index).getValue();
        }

        @Override
        public E set(int index, E obj) {
            // get_node allows for index to be -1, but we don't want set to allow that
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            Node theNode = get_node(index);
            E theValue = theNode.getValue();
            theNode.setValue(obj);
            return theValue;
        }

        @Override
        public E first() {
            return get(0);
        }

        @Override
        public E last() {
            return get(size() - 1);
        }

        @Override
        public int firstIndex(E obj) {
            Node curNode = header.getNext();
            int curPos = 0;
            // Traverse the list until we find the element or we reach the end
            while (curPos < this.size() && !curNode.getValue().equals(obj)) {
                curPos++;
                curNode = curNode.getNext();
            }
            if (curPos < this.size())
                return curPos;
            else
                return -1;
        }

        @Override
        public int lastIndex(E obj) {
            Node curNode = trailer.getPrev();
            int curPos = size() - 1;
            // Traverse the list (backwards) until we find the element or we reach the
            // beginning
            while (curNode != header && !curNode.getValue().equals(obj)) {
                curPos--;
                curNode = curNode.getPrev();
            }
            return curPos; // Will be -1 if we reached the header
        }

        @Override
        public int size() {
            return currentSize;
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public boolean contains(E obj) {
            return firstIndex(obj) != -1;
        }

        @Override
        public void clear() {
            // Avoid throwing an exception if the list is already empty
            while (size() > 0)
                remove(0);
        }

        @Override
        public int replaceAll(E e, E f) {
            int result = 0;

            for (Node curNode = this.header.getNext(); curNode != this.trailer; curNode = curNode.getNext()) {
                if (curNode.getValue().equals(e)) {
                    curNode.setValue(f);
                    result++;
                }
            }

            return result;
        }

        @Override
        public List<E> reverse() {
            List<E> result = new DoublyLinkedList<E>();
            for (Node curNode = trailer.getPrev(); curNode != header; curNode = curNode.getPrev()) {
                result.add(curNode.getValue());
            }
            return result;
        }

        @Override
        public boolean equals(List<E> l) {
            int i = 0;
            for (Node curNode = this.header.getNext(); curNode != this.trailer; curNode = curNode.getNext()) {
                if (!curNode.getValue().equals((((DoublyLinkedList<E>) l).get(i)))) {
                    return false;
                }
                i++;
            }

            return true;

        }
    }

    /**
     * Snakes and Ladders is a board game in which players roll a 6-sided die
     * to determine how many squares forward they move on their turn, in a race to
     * the finish.
     * 
     * Some special squares are the start of ladders, and if a player ends on
     * that square they move forward to the end of the ladder; other squares are
     * the start of snakes, and if a player ends on that square they move back to
     * the
     * end of the snake.
     * 
     * Given a particular Snakes and Ladders board, determine the minimum number
     * of rolls needed to win the game. If the game is impossible to win, the method
     * returns -1.
     * 
     * Hint: Use the Coordinate Class to store your position and roll in once place
     * 
     * @param board A 1-dimensional representation of the board stored inside a
     *              List<Integer>
     * @return The minimum number of rolls needed to get to the final space on the
     *         board.
     *         Returns -1 if it's not possible to win.
     */
	public static int minimumMoves(List<Integer> board) {
		/* TODO ADD YOUR CODE HERE */
            int n = board.size();
            Queue<Integer> q = new DoublyLinkedQueue<>();
            boolean[] visited = new boolean[n];
            int[] moves = new int[n];
            
            q.enqueue(0);
            visited[0] = true;
            for (int i = 0; i < n; i++) {
                moves[i] = Integer.MAX_VALUE;
            }
            moves[0] = 0;
            
            while (!q.isEmpty()) {
                int currCell = q.dequeue();
                for (int i = 1; i <= 6 && currCell + i < n; i++) {
                    int nextCell = currCell + i;
                    if (board.get(nextCell) != -1) {
                        nextCell = board.get(nextCell);
                    }
                    if (!visited[nextCell]) {
                        visited[nextCell] = true;
                        moves[nextCell] = moves[currCell] + 1;
                        q.enqueue(nextCell);
                    }
                }
            }
            return moves[n - 1] == Integer.MAX_VALUE ? -1 : moves[n - 1];
        } 
    }