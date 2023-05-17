/**
 * Write the following code in your editor below:A private static class named
 * Arithmetic that has two private fields int a & int b with their appropriate
 * getters and setters. This class will also have the following member methods
 * that do basic mathematical operations:
 * public int add();
 * public int subtract();
 * public int multiply();
 * public double divide();
 * A public static class named Calculator that inherits from a superclass named
 * Arithmetic.
 * Hint: Remember to use the super keyword in the constructor
 */

public class Lab01P1Wrapper {

    private static class Arithmetic {
        /*
         * TODO ADD THE FOLLOWING:
         * PRIVATE FIELDS,
         * CONSTRUCTOR,
         * GETTERS,
         * SETTERS,
         * MEMBER METHODS
         */
        private int a;
        private int b;

        public Arithmetic(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public void setA(int a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int add() {
            return a + b;
        }

        public int subtract() {
            return a - b;
        }

        public int multiply() {
            return a * b;
        }

        public double divide() {
            return a / b;
        }
    }

    public static class Calculator extends Arithmetic /* TODO What is missing here? */ {

        /* TODO ADD CONSTRUCTOR HERE */
        public Calculator(int a, int b) {
            super(a, b);
        }
    }
}
