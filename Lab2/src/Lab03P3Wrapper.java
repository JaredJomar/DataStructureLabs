/**
 * Create a method called isValidSudoku() that determines if the 9 x 9 Sudoku
 * board given as a parameter is valid. Only the filled cells need to be
 * validated according to the following rules:
 * 
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
 * without repetition.
 * Notes:
 * 
 * A Sudoku board (partially filled) could be valid but is not necessarily
 * solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * WARNING: You CAN NOT use external data structures from the Java Collections
 * Framework (HashSet, HashMap, ArrayList, etc.). Solutions that include any of
 * these will receive ZERO credit upon inspection from the professor and/or TAs
 * 
 * WARNING: The method should be completed in
 * O(1)
 * time, implementations that are not
 * O(1)
 * will receive half the credit obtained after inspection from the professor
 * and/or TAs (why O(1)?
 * Think about the details of the problem to try to understand why it is
 * possible to do it in constant time).
 */
public class Lab03P3Wrapper {
    public static boolean isValidSudoku(char[][] board) {
        /* ADD YOUR CODE HERE */
        int[] row = new int[9];
        int[] column = new int[9];
        int[] box = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int k = (i / 3) * 3 + j / 3;
                    if ((row[i] & (1 << num)) > 0 || (column[j] & (1 << num)) > 0 || (box[k] & (1 << num)) > 0) {
                        return false;
                    }
                    row[i] |= (1 << num);
                    column[j] |= (1 << num);
                    box[k] |= (1 << num);
                }
            }
        }
        return true;
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
 * 1- El tiempo de ejecución del algoritmo implementado es O(9^2), que es una
 * complejidad temporal de O(n^2). Esto se debe a que el código utiliza dos
 * nester loops, cada loop tiene una complejidad temporal constante de O(1) para
 * el bloque de código interior.
 * 
 * 2- La primera parte del código es la declaración de tres arreglos: row,
 * column,y box. Cada arreglo tiene 9 elementos, y la complejidad temporal de
 * esta parte es O(1). La segunda parte del código son los nester loops, que se
 * utilizan para recorrer el tablero. El loop exterior tiene una complejidad
 * temporal de O(9) al igual que el loop interior . El bloque de código interior
 * de los loops tiene una complejidad temporal de O(1) debido a las operaciones
 * bit a bit que se realizan. La complejidad temporal total de los loops es
 * O(9^2).
 * 
 * 3- El código no tiene una solución más óptima que la implementada. Esta
 * solución utiliza la manipulación de bits para almacenar el estado de cada
 * fila, columna y cuadro de 3x3. Mediante el uso de la manipulación de bits,
 * permite el acceso constante en tiempo y la verificación de cada fila, columna
 * y cuadro, lo que reduce la complejidad general del tiempo a O(1). El código
 * ya está optimizado y cumple con el requisito de tiempo de ejecución mínimo de
 * O(1). No se requieren cambios.
 * 
 * 4- Este código cumple con los requisitos mínimos de tiempo de ejecución, por
 * lo que no es necesario agregar un pseudocódigo para la solución óptima.
 */