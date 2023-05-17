package lab7.problems;

import lab7.interfaces.Map;
import lab7.util.hashTable.HashTableOA;
import lab7.util.hashTable.SimpleHashFunction;

public class MostFrequentProblem {
	/**
	 * TODO EXERCISE 4:
	 * You are given an integer array nums. 
	 * You are also given an integer key, which is present in nums.
	 * 
	 * For every unique integer target in nums, count the number of times 
	 * target immediately follows an occurrence of key in nums. 
	 * 
	 * In other words, count the number of indices i such that:
	 * 1) 0 <= i <= nums.length - 2
	 * 2) nums[i] == key
	 * 3) nums[i + 1] == target
	 * 
	 * Return the target with the maximum count. 
	 * The test cases will be generated such that the target with maximum count is unique.
	 * @param nums
	 * @param key
	 * @return Target with the maximum count
	 */
	public int mostFrequent(int[] nums, int key) {
		/*TODO ADD YOUR CODE HERE*/
		HashTableOA<Integer, Integer> frequencyMap = new HashTableOA<>();
		int maxFrequency = 0;
		int mostFrequentNumber = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i < nums.length - 1 && nums[i] == key) {
				int target = nums[i + 1];
				int frequency = frequencyMap.containsKey(target) ? frequencyMap.get(target) : 0;
				frequency++;
				frequencyMap.put(target, frequency);

				if (frequency > maxFrequency) {
					maxFrequency = frequency;
					mostFrequentNumber = target;
				}
			}
		}

		return mostFrequentNumber;
	}
}