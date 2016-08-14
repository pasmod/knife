package de.hhu.knife;

/*
 * This is an example for a file that consists of one
 * class and does not have any syntax errors.
 */
public class Example {
    int counter = 5;

    /*
     * JavaDoc Comment 1
     */
    public static void main(String[] args) {
        System.out.println(add(2, 3));
        System.out.println(add(2, 3));
        System.out.println(add(2, 3));
    }

    /*
     * JavaDoc Comment 2 Second Line
     */
    public static int add(final int a, final int b) {
        return a + b;
    }

    public double divide(double a, double b) {
        return 1.0 * a / b; // Line Comment 1
    }

}