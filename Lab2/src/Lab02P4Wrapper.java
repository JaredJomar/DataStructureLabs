/**
 * Given an integer array nums, return an array such that answer[i] is equal to
 * the product of all the elements of nums except nums[i].
 * 
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 * 
 * Example:
 * 
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * WARNING: You must write an algorithm that runs in
 * O(N)
 * time and without using the division operation. Failure to comply will result
 * in a deduction of half the credit obtained upon inspection from the professor
 * and/or the TAs.
 * 
 * WARNING: You CAN NOT use any data structure from the Java Collections
 * Framework (HashSet, HashMap, ArrayList, etc.). Implementations that do not
 * follow this will obtain ZERO credit upon inspection from the professor and/or
 * the TAs
 */
public class Lab02P4Wrapper {
    public static int[] productExceptSelf(int[] nums) {
        /* ADD YOUR CODE HERE */
        int[] result = new int[nums.length];
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = product;
            product *= nums[i];
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= product;
            product *= nums[i];
        }
        return result;
    }
}

/**
 * For the problem solved above, answer the following items:
 * 1. What is the running time of the algorithm implemented? Explain your
 * thought
 * process behind the implementation
 * 2. Describe in detail the running time breaking down each part of the code
 * implemented into its respective runtimes
 * 3. Does this code have a more optimal solution than the one implemented? If
 * so,
 * explain why, how and what would you change from your code to optimize said
 * solution
 * 4. If your code does not meet the minimum runtime requirements, add below a
 * pseudocode of your implementation for the optimal solution. It does not have
 * to
 * follow any specific syntax, its pseudocode. (If it does meet the minimum
 * requirements, ignore this question)
 */

/**
 * 1- El tiempo de ejecución del algoritmo implementado es O(n). La
 * implementación utiliza dos for loop para calcular el producto total de los
 * números en el arreglo, excepto para el número en la posición actual en cada
 * iteración.
 * 
 * 2- El algoritmo tiene dos loops, el primero itera a través de los elementos
 * del arreglo de entrada y calcula el producto de todos los elementos antes del
 * elemento actual y lo almacena en el arreglo resultante. El segundo loop
 * comienza desde el final del arreglo de entrada y calcula el producto de todos
 * los elementos después del elemento actual y lo multiplica con el valor ya
 * almacenado en el arreglo resultante. Ambos loop toman tiempo O(n), por lo que
 * el tiempo de ejecución total del algoritmo es O(n).
 * 
 * 3- El código implementado es óptimo en términos de tiempo de ejecución. No
 * hay una solución más óptima que O(n), no hay otros algoritmos más rápidos
 * para resolver este problema.
 * 
 * 4- Este código cumple con los requisitos mínimos de tiempo de ejecución, por
 * lo que no es necesario agregar un pseudocódigo para la solución óptima.
 * 
 */