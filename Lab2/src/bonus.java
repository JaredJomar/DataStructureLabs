/**
 * BONUS
 * Write a method that takes in a non-empty array of distinct integers and an
 * integer representing a target. If any two numbers in the input array sum up
 * to the target sum, the method should return them in an array, in any order.
 * If no two numbers sum up to the target array, the method returns an empty
 * array.
 * 
 * Note that the target sum has to be obtained by summing two different integers
 * in the array; you can't add a single integer to itself in order to obtain the
 * target sum.
 * 
 * You can assume that there will be at most one pair of numbers summing up to
 * the target sum.
 * 
 * For example, given the parameters array = {3, 5, -4, 8, 11, 1, -1, 6} and
 * targetSum = 10, a call to twoSum() returns the array with the elements {-1,
 * 11} because -1 + 11 = 10
 * 
 * Hint: Does sorting the array first make things easier while maintaining the
 * runtime? Try the static sort method provided in the Arrays class
 * 
 * WARNING: You MUST implement this in
 * O(n∗log2 * n
 * time, solutions that are not log-linear or better will receive HALF the
 * credit obtained upon inspection from the TAs.
 */
import java.util.Arrays;

public class Lab03P1Wrapper {

	public static int[] twoSum(int[] array, int targetSum) {
		/*ADD YOUR CODE HERE*/
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int sum = array[left] + array[right];
            if (sum == targetSum) {
                return new int[] { array[left], array[right] };
            } else if (sum < targetSum) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
	}
}