
/**
 * A Java interface can only contain method signatures and fields, similar to an abstract class with only purely virtual methods in C++. The interface can be used to achieve polymorphism. In this problem, you will practice your knowledge of interfaces.
 * You are given an interface AdvancedArithmetic which contains method signatures like int divisorSum(int n). You need to finish writing a class called AdvancedCalculator which implements the interface.
 * Read the partially completed code and the JavaDoc comments Explaining each method and complete it. You just need to finish writing the AdvancedCalculator class only.
 * Hint: Use the Java API documentation to know how to use ArrayLists in Java
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Lab01P4Wrapper {

    public static interface AdvancedArithmetic {

        /**
         * Method that takes an integer as input and return the sum of all its divisors
         * For example divisors of 6 are 1, 2, 3 and 6, so divisorSum() should return 12
         *
         * @param n - number to take the divisors of
         * @return An integer denoting the sum of each divisor of n
         */
        int divisorSum(int n);

        /**
         * Method that takes an integer as input and return an array of all its prime
         * factors
         * For example prime factors of 12 are 2, 2 and 3, so primeFactors() should
         * return [2, 2, 3]
         *
         * HINT: Use an ArrayList to store the numbers, then pass them on to an int
         * array,
         * similar to a vector in C++.
         *
         * HINT: Remember the difference between Object Data Types and Primitive Data
         * Types
         * so you can succesfully store values and pass them
         *
         * @param n - number to take the divisors of
         * @return An array of type int denoting each prime factor
         */
        int[] primeFactors(int n);

        /**
         * Method that determines whether a given integer n is a prime number
         * 
         * @param n
         * @return if the number is a prime number or not
         */
        boolean isPrime(int n);

        /**
         * Method that return the nth number in the fibonacci sequence
         * 
         * For example if n = 5, then fibonacci(n) returns 5 because the fifth term in
         * the sequence is 5
         * 
         * Remember that the sequence starts with 1 and 1, then the next term in the
         * series is the sum
         * of the previous 2 terms:
         * 
         * 1,1,2,3,5,8,13,21,...
         * 
         * By definition, the fibonacci sequence is defined as follows:
         * 
         * F_0 = 0
         * F_1 = 1
         * F_n = F_(n-2) + F_(n-1)
         * 
         * Think: Doing this recursively is trivial, but it's a bit slow. Can we do this
         * ITERATIVELY?
         * 
         * @param n - index of the fibonacci sequence we want to find
         * @return the value of nth term of the fibonacci sequence
         */
        int fibonacci(int n);

    }

    public static class AdvancedCalculator /* TODO What needs to be added here? */ {

        public int[] testValues; // DO NOT DELETE THIS, TEST WILL FAIL

        public AdvancedCalculator(int[] testValues) {
            this.testValues = testValues;
        }

        /* TODO ADD UNIMPLEMENTED METHODS HERE */
        public int divisorSum(int n) { // A method that takes an integer as input and return the sum of all its
                                       // divisors
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    sum += i;
                }
            }
            return sum;
        }

        public int[] primeFactors(int n) {
            ArrayList<Integer> factors = new ArrayList<Integer>(); // An ArrayList to store the numbers, then pass them
                                                                   // on to an int array
            for (int i = 2; i <= n; i++) {
                while (n % i == 0) { // Check if i is a factor of n and if it is, add it to the ArrayList
                    factors.add(i);
                    n /= i;
                }
            }
            int[] isPrime = new int[factors.size()]; // An array of type int denoting each prime factor
            for (int i = 0; i < factors.size(); i++) {
                isPrime[i] = factors.get(i);
            }
            return isPrime;
        }

        public boolean isPrime(int n) { // returns true if prime, false if not
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i < n; i++) {
                if (n % i == 0) { // Check if i is a factor of n and if it is, return false
                    return false;
                }
            }
            return true;
        }

        public int fibonacci(int n) { // Method for fibonacci sequence generation using recursion algorithm for faster
                                      // execution time and less memory usage than iterative algorithm
            if (n <= 0) {
                return 0;
            } else if (n == 1) {
                return 1;
            } else {
                return fibonacci(n - 2) + fibonacci(n - 1);
            }
        }
    }
}
