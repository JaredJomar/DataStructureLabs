/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the
 * Pascal's triangle.
 * Pascal's triangle, each number is the sum of the two numbers directly
 * above it as shown:
 * 
 * For example:
 * rowIndex = 2
 * 
 * Then getRowPascalTriangle() you should return [1,2,1] because it is the
 * second row of the triangle.
 * 
 * Hint: Use this formula
 * curr = (prev * (n-i + 1) / i) , where n = rowIndex, i = currElmIdx
 * WARNING: You CANNOT use external data structures from the Java Collections
 * Framework (HashSet, HashMap, ArrayList, etc.). Solutions that include any of
 * these will receive ZERO credit upon inspection from the professor and/or TAs
 * 
 * WARNING: Your method MUST run on
 * O(n)
 * time, implementations that do not run in linear time will be given half the
 * credit obtained upon inspection from the professor and/or the TAs
 */
public class Lab03P1Wrapper {

    public static int[] getRowPascalTriangle(int rowIndex) {
        /* ADD YOUR CODE HERE */
        int[] row = new int[rowIndex + 1];
        row[0] = 1;
        for (int i = 1; i < row.length; i++) {
            row[i] = (row[i - 1] * (rowIndex - i + 1)) / i;
        }
        return row;
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
 * follow any specific syntax, it's pseudocode. (If it does meet the minimum
 * requirements, ignore this question)
 */

/**
 * 1- El tiempo de ejecución del algoritmo implementado es O(n), donde n es el
 * índice de la fila. La implementación utiliza un for loop que recorre todos
 * los elementos de la fila, por lo que la complejidad es lineal.
 * 
 * 2- La implementación consta de dos partes: la creación de una fila con un
 * tamaño específico y el llenado de esa fila con los valores correspondientes.
 * La creación de la fila toma O(1) tiempo, ya que simplemente se asigna un
 * tamaño a un arreglo. Luego el llenado de la fila toma O(n) tiempo, ya que el
 * for loop recorre todos los elementos de la fila. En total, el tiempo de
 * ejecución es O(n).
 * 
 * 3- El código implementado es óptimo en términos de tiempo de ejecución. No
 * hay una solución más óptima que O(n), ya que es necesario recorrer todos los
 * elementos de la fila para calcular los valores correspondientes.
 *  
 * 4- Este código cumple con los requisitos mínimos de tiempo de ejecución, por
 * lo que no es necesario agregar un pseudocódigo para la solución óptima.
 */