import java.io.PrintStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Lab03P5Wrapper {

    public interface List<E> extends Iterable<E> {
        public int size();

        public boolean isEmpty();

        public boolean isMember(E e);

        public int firstIndex(E e);

        public int lastIndex(E e);

        public void add(E e); // at the end

        public void add(E e, int index);

        public E get(int index);

        public E remove(int index);

        public boolean remove(E e);

        public int removeAll(E e);

        public E replace(int index, E newElement);

        public void clear();

        public Object[] toArray();

        public void print(PrintStream out);

        public Comparator<E> getComparator();

    }

    public static class ArrayList<E extends Comparable<E>> implements List<E> {

        private static final int DEFAULT_SIZE = 10;
        private E[] elements;
        private int currentSize;
        private Comparator<E> cmp;

        public class ArrayListIterator<E> implements Iterator<E> {
            private int currentPosition;

            public ArrayListIterator() {
                this.currentPosition = 0;
            }

            @Override
            public boolean hasNext() {
                return this.currentPosition < currentSize;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (this.hasNext()) {
                    E result = (E) elements[this.currentPosition++];
                    return result;

                } else {
                    throw new NoSuchElementException();
                }
            }
        }

        /**
         * TODO ADD HERE THE ADDITIONAL CONSTRUCTOR TO INITIALIZE AN ARRAY LIST THAT IS
         * PASSED THE INITIAL CAPACITY AND THE COMPARATOR (IN THAT ORDER)
         */
        public ArrayList(int initSize, Comparator<E> comparator) {
            if (initSize < 1) {
                throw new IllegalArgumentException("Initial size must be at least 1.");
            }
            this.currentSize = 0;
            this.elements = (E[]) new Comparable[initSize];
            this.cmp = comparator;
        }

        @SuppressWarnings("unchecked")
        public ArrayList(int initSize) {
            if (initSize < 1) {
                throw new IllegalArgumentException("Initial size must be at least 1.");
            }
            this.currentSize = 0;
            this.elements = (E[]) new Comparable[initSize];

            /*
             * TODO ADD THE NECESSARY CODE TO CONSTRUCT AN INSTANCE OF THE GENERIC
             * COMPARATOR USING LAMBDA FUNCTIONS
             */
            this.cmp = (E e1, E e2) -> e1.compareTo(e2);

        }

        public ArrayList() {
            this(DEFAULT_SIZE);
        }

        @Override
        public int size() {
            return this.currentSize;
        }

        @Override
        public boolean isEmpty() {
            return this.size() == 0;
        }

        @Override
        public boolean isMember(E e) {
            return this.firstIndex(e) >= 0;

        }

        @Override
        public int firstIndex(E e) {
            for (int i = 0; i < this.size(); ++i) {
                if (cmp.compare(elements[i], e) == 0) {
                    return i;
                }
            }
            return -1;
            // == compares references or scalar values
        }

        @Override
        public int lastIndex(E e) {
            for (int i = this.currentSize - 1; i >= 0; --i) {
                if (cmp.compare(elements[i], e) == 0) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void add(E e) {
            // add at the end
            if (this.size() == this.elements.length) {
                this.reAllocate();
            }
            this.elements[this.currentSize++] = e;

        }

        @SuppressWarnings("unchecked")
        private void reAllocate() {
            E[] newElements = (E[]) new Comparable[2 * this.size()];

            for (int i = 0; i < this.size(); ++i) {
                newElements[i] = this.elements[i];
            }
            this.elements = newElements;
        }

        @Override
        public void add(E e, int index) {
            if ((index < 0) || (index > this.currentSize)) {
                throw new IndexOutOfBoundsException("Illegal Position");
            }
            // special case: index == this.currentSize
            if (index == this.currentSize) {
                this.add(e);
            } else {
                if (this.size() == this.elements.length) {
                    this.reAllocate();
                }
                for (int i = this.currentSize; i > index; --i) {
                    this.elements[i] = this.elements[i - 1];
                }
                this.elements[index] = e;
                this.currentSize++;
            }
        }

        @Override
        public E get(int index) {
            if ((index < 0) || (index >= this.currentSize)) {
                throw new IndexOutOfBoundsException("Illegal Position");
            }
            return this.elements[index];
        }

        @Override
        public E remove(int index) {
            if ((index < 0) || (index >= this.currentSize)) {
                throw new IndexOutOfBoundsException("Illegal Position");
            }
            E result = this.elements[index]; // el q se borra
            for (int i = index; i < this.size() - 1; ++i) { // i in range [0, currenSize-1]
                this.elements[i] = this.elements[i + 1];
            }
            this.elements[this.size() - 1] = null;
            this.currentSize--;
            return result;
        }

        @Override
        public boolean remove(E e) {
            int targetIndex = this.firstIndex(e);
            if (targetIndex < 0) {
                return false;
            } else {
                this.remove(targetIndex);
                return true;
            }
        }

        @Override
        public int removeAll(E e) {
            int counter = 0;
            while (this.remove(e)) {
                counter++;
            }
            return counter;
        }

        @Override
        public E replace(int index, E newElement) {
            // valid values [0, this.currentSize-1]
            // same as [0, this.size() -1]
            // && both case are True
            // || at least one is true

            if ((index < 0) || (index >= this.currentSize)) {
                throw new IndexOutOfBoundsException("Illegal Position");
            }
            E result = this.elements[index]; // the old one
            this.elements[index] = newElement; // put the new one
            return result; // return old one
        }

        @Override
        public void clear() {
            while (!this.isEmpty()) {
                // empty body
                this.remove(0);
            }
        }

        @Override
        public Object[] toArray() {
            Object[] result = new Object[this.size()];
            for (int i = 0; i < this.size(); ++i) {
                result[i] = this.elements[i];
            }
            return result;
        }

        @Override
        public void print(PrintStream out) {
            for (int i = 0; i < this.size(); ++i) {
                out.print(this.elements[i]);
                out.print(" ");
            }
            out.println();
        }

        @Override
        public Iterator<E> iterator() {
            return new ArrayListIterator<>();
        }

        public E set(int index, E newElement) {
            if ((index < 0) || (index >= this.size())) {
                throw new IndexOutOfBoundsException("index is out of range.");
            }
            E result = this.elements[index];
            this.elements[index] = newElement;
            return result;
        }

        public Comparator<E> getComparator() {
            return cmp;
        }

    }

    public static <E extends Comparable<E>> List<E> sort(List<E> dataSet, Comparator<E> comparator) {
        // If List has one element, the list is already sorted
        if (dataSet.size() == 1)
            return dataSet;

        // Split list in two equal halves
        List<E> left = new ArrayList<>();
        List<E> right = new ArrayList<>();

        for (int i = 0; i < dataSet.size() / 2; i++)
            left.add(dataSet.get(i));

        for (int i = dataSet.size() / 2; i < dataSet.size(); i++)
            right.add(dataSet.get(i));

        // Recursively sort each half
        left = sort(left, comparator);
        right = sort(right, comparator);

        // Merge back together each sorted half
        return merge(left, right, comparator);

    }

    private static <E extends Comparable<E>> List<E> merge(List<E> left, List<E> right, Comparator<E> comparator) {
        int i = 0, j = 0;
        List<E> result = new ArrayList<>();

        // Traverse each halve at the same time using two indices (i & j)
        // While one of the halves still has elements, keep traversing
        while (i < left.size() && j < right.size()) {
            // If the current element in the left half is less than or equal to the current
            // element in the right half
            // then add the element to the result list, if not add the current element in
            // the right half

            /*
             * TODO ADD THE CORRESPONDING IF-ELSE STATEMENTS THAT USE THE COMPARATOR CREATED
             */
            if (comparator.compare(left.get(i), right.get(j)) <= 0)
                result.add(left.get(i++));
            else
                result.add(right.get(j++));
        }

        // Add the leftover elements from the other halve that may have been left empty
        while (i < left.size())
            result.add(left.get(i++));
        while (j < right.size())
            result.add(right.get(j++));

        return result;
    }
}