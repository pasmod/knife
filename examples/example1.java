package de.hhu.knife;

/*
 * This is an example for a file that consists of one
 * class and does not have any syntax errors.
 */
public class example1 {
    int counter = 5;

    /**
     * Main method of the example
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(add(2, 3));
        System.out.println(add(2, 3));
        System.out.println(add(2, 3));
    }

    public static int add(final int a, final int b) {
        /*
         * Block Comment Line 2
         */
        return a + b;
    }

    public double divide(double a, double b) {
        return 1.0 * a / b; // Line Comment 1
    }

}