
/**
 * You are given a grid of 0's and 1's where 0's represent trees and 1's
 * represent rocks. You are also given the coordinates of a square on the grid
 * where there is a fire. In the below example, the fire starts at position (3,
 * 3), where the first coordinate is the row and the second coordinate is the
 * column. Assume that it takes fire 1 minute to spread from a tree in one
 * square to a tree in an adjacent square. Fire cannot spread to squares that
 * contain rocks. Return a new grid where the number in that square of the grid
 * represents how long it took the fire to reach that square. The square where
 * the fire started should contain a 0, the directly adjacent squares containing
 * trees should contain a 1, etc. If the fire never reaches a square, that cell
 * should contain a -1.
 * (Image)(Image)
 * Write a method called forestFire() that takes an instance of a Grid<Integer>
 * called grid and an instance of Position<Integer> called src that represents
 * the position at which the fire started and also gives you how burnt that
 * position is (0 begin completely burnt, -1 being not burnt at all).
 * The classes that define Grid<E> and Position<E> are included in the provided
 * wrapper code. Take your time to study these classes that the problem uses to
 * be able to tackle it in an optimal manner.
 * You may assume the following things:
 * If a grid cell contains a rock, or the fire cannot reach the grid cell
 * because it is surrounded by rocks, return a -1 in that grid cell.
 * You should not modify the input grid of 0's and 1's. Create a new grid to
 * return.
 * You may assume the input grid contains only 0's and 1's.
 * You may assume the input grid is rectangular (but it might not be square!)
 * You may assume the grid is not an empty list.
 * You may assume the given src coordinate (r, c) lies within the bounds of the
 * grid
 * You may assume the grid at the src position contains a tree, not a rock.
 * (i.e. it will always be a 0, never a 1)
 * Example:
 * A call to forestFire(grid, src), where
 * grid = [
 * [0,0,1,0,0],
 * [1,0,1,1,0],
 * [0,1,0,0,0],
 * [0,0,0,0,1],
 * [0,0,0,1,1],
 * [0,0,0,0,0]
 * ]
 * and src = Position<>(3, 3, 0)
 * should return
 * result = [
 * [-1, -1, -1, 5, 4],
 * [-1, -1, -1, -1, 3],
 * [4, -1, 2, 1, 2],
 * [3, 2, 1, 0, -1],
 * [4, 3, 2, -1, -1],
 * ]
 * Note that this is just a visual representation, in reality, grid will have
 * the following form:
 * grid = [
 * [Position<>(0,0,0), Position<>(0,1,0), Position<>(0,2,1), ...]
 * ....
 * [...., Position<>(5, 4, 0)]
 * ]
 * where each position hold in the (row, col) coordinate in the grid and the
 * element that is in that coordinate.
 */

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForestFireWrapper {

    /**
     * Class that encapsulates a M x N grid with positions.
     * Each position in the grid contains an instance of a Position<E> class
     * that contains its (X,Y) coordinate, as well as the value that is in that
     * position.
     * 
     * @author Fernando J. Bermudez - bermed28
     *
     * @param <E> Represents a generic value used for grid positions
     */
    public static class Grid<E> {

        private Position<E>[][] grid;

        /**
         * Creates an empty M x N grid with an initializer that sets
         * all the grid positions to that same element. The initializer
         * is an generic type chosen by the user.
         * 
         * Example:
         * 
         * Grid<Integer> grid = new Grid(3, 4, -1);
         * 
         * grid = {
         * {-1, -1, -1, -1},
         * {-1, -1, -1, -1},
         * {-1, -1, -1, -1}
         * }
         * 
         * @param rows        The number of rows the grid will have
         * @param columns     The number of columns the grid will have
         * @param initializer The default value for each position in the grid
         */
        @SuppressWarnings("unchecked")
        public Grid(int rows, int columns, E initializer) {
            this.grid = new Position[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    grid[i][j] = new Position<>(i, j, initializer);
                }
            }
        }

        public Position<E>[][] getGrid() {
            return grid;
        }

        public Position<E> getPosition(int x, int y) {
            return this.grid[x][y];
        }

        public void setPosition(Position<E> p) {
            if (p.getX() >= grid.length || p.getY() >= grid[0].length)
                throw new IndexOutOfBoundsException(
                        "Position (" + p.getX() + ", " + p.getY() + ", " + p.getElement() + ") "
                                + "is out of bounds of the grid dimensions " + grid.length + " x " + grid[0].length);
            this.grid[p.getX()][p.getY()] = p;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            Grid<E> G = (Grid<E>) obj;

            if (grid[0].length != G.getGrid()[0].length || grid.length != G.getGrid().length)
                return false;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    Position<E> p = G.getPosition(i, j);
                    if (!grid[i][j].equals(p))
                        return false;
                }
            }

            return true;
        }

        public void print(PrintStream p) {
            Arrays.stream(grid).forEach((row) -> {
                p.print("[ ");
                Arrays.stream(row).forEach((el) -> {
                    if (el.getY() == grid[0].length - 1) {
                        if ((Integer) el.getElement() >= 0)
                            p.print(" " + el.getElement() + " ");
                        else
                            p.print(el.getElement() + " ");
                    } else
                        p.print(el.getElement() + "\t");

                });
                System.out.println("]");
            });
        }

    }

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

    public static class Position<E> extends Coordinate {

        private E element;

        public Position(int x, int y, E elm) {
            super(x, y);
            this.element = elm;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Coordinate getCoordinate() {
            return new Coordinate(getX(), getY());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (obj instanceof Position<?>) {
                Position<E> p = (Position<E>) obj;
                return (this.getX() == p.getX() && this.getY() == p.getY()) && this.getElement() == p.getElement();
            }

            else {
                Coordinate c = (Coordinate) obj;
                if (c.getX() == 3 && c.getY() == 3) {
                    System.out.println("gotem");
                }
                return getCoordinate().equals(obj);
            }

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
     * TODO EXERCISE 1:
     * 
     * You are given a grid of 0's and 1's where 0's represent trees and 1's
     * represent rocks.
     * You are also given the coordinates of a square on the grid where there is a
     * fire.
     * 
     * In the below example, the fire starts at position (3, 3), where the first
     * coordinate is the row and
     * the second coordinate is the column.
     * 
     * grid = [
     * [0,0,1,0,0],
     * [1,0,1,1,0],
     * [0,1,0,0,0],
     * [0,0,0,0,1],
     * [0,0,0,1,1],
     * [0,0,0,0,0]
     * ]
     * 
     * Assume that it takes fire 1 minute to spread from a tree in one square to a
     * tree in an adjacent square.
     * Fire cannot spread to squares that contain rocks. Return a new grid where the
     * number
     * in that square of the grid represents how long it took the fire to reach that
     * square.
     * 
     * The square where the fire started should contain a 0, the directly adjacent
     * squares
     * containing trees should contain a 1, etc. If the fire never reaches a square,
     * that cell should contain a -1.
     * 
     * The example above with a starting position of (3, 3) returns
     * 
     * result = [
     * [-1, -1, -1, 5, 4],
     * [-1, -1, -1, -1, 3],
     * [4, -1, 2, 1, 2],
     * [3, 2, 1, 0, -1]
     * [4, 3, 2, -1, -1],
     * [5, 4, 3, 4, 5]
     * ]
     * 
     * @param grid M x N grid of Position<Integer> of the form (X, Y, level).
     *             The level denotes if the position id a rock or not, and it
     *             will always be a 0 or 1 initially.
     * @param src  Starting position where the fire breaks out, the level will
     *             always be 0.
     * @return A new M x N grid denoting the levels of burn in each position, rocks
     *         will be -1,
     *         grass will range from 0 to the length of the grid dimension,
     *         depending on how far
     *         it is from src
     */
    public static Grid<Integer> forestFire(Grid<Integer> grid, Position<Integer> src) {
        /* TODO ADD YOUR CODE HERE */
        // Create a new grid to store the burn levels
        int rows = grid.getGrid().length;
        int columns = grid.getGrid()[0].length;
        Grid<Integer> result = new Grid<>(rows, columns, -1);

        // Mark the fire source as level 0 and add it to the BFS queue
        src.setElement(0);
        DoublyLinkedQueue<Position<Integer>> queue = new DoublyLinkedQueue<>();
        queue.enqueue(src);

        // Explore the grid using BFS
        while (!queue.isEmpty()) {
            Position<Integer> curr = queue.dequeue();
            int x = curr.getX();
            int y = curr.getY();
            int level = curr.getElement();

            // Get current position's burn level and check if it's not a rock
            int currBurnLevel = grid.getPosition(x, y).getElement();
            if (currBurnLevel == 0
                    && (result.getPosition(x, y).getElement() == -1 || result.getPosition(x, y).getElement() > level)) {
                result.setPosition(new Position<>(x, y, level));
            }

            // Add unexplored neighbor positions to the queue
            if (x > 0) {
                int leftBurnLevel = grid.getPosition(x - 1, y).getElement();
                int leftResultLevel = result.getPosition(x - 1, y).getElement();
                if (leftBurnLevel != 1 && leftResultLevel == -1) {
                    queue.enqueue(new Position<>(x - 1, y, level + 1));
                }
            }
            if (y > 0) {
                int upBurnLevel = grid.getPosition(x, y - 1).getElement();
                int upResultLevel = result.getPosition(x, y - 1).getElement();
                if (upBurnLevel != 1 && upResultLevel == -1) {
                    queue.enqueue(new Position<>(x, y - 1, level + 1));
                }
            }
            if (x < rows - 1) {
                int rightBurnLevel = grid.getPosition(x + 1, y).getElement();
                int rightResultLevel = result.getPosition(x + 1, y).getElement();
                if (rightBurnLevel != 1 && rightResultLevel == -1) {
                    queue.enqueue(new Position<>(x + 1, y, level + 1));
                }
            }
            if (y < columns - 1) {
                int downBurnLevel = grid.getPosition(x, y + 1).getElement();
                int downResultLevel = result.getPosition(x, y + 1).getElement();
                if (downBurnLevel != 1 && downResultLevel == -1) {
                    queue.enqueue(new Position<>(x, y + 1, level + 1));
                }
            }
        }

        return result;
    }
}