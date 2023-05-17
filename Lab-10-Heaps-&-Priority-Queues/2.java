import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapSortWrapper {

    /**
     * TODO EXERCISE 3:
     * 
     * Implement the Heap Sort Algorithm to sort an array of elements given as
     * parameter.
     * 
     * Note: You must do this IN PLACE, DO NOT USE AN EXTENRAL HEAP/PQ
     *
     * @param arr Array to sort
     * @return The Sorted Array
     */
    public static int[] heapSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap in reverse order
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify reduced heap
            heapify(arr, i, 0);
        }

        return arr;
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child position
        int right = 2 * i + 2; // Right child position

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            // Swap root and largest
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}