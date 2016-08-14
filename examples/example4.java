package de.hhu.knife;

/*
 * This is an example of a file consisting of two
 * classes and the second class has a syntax error.
 */
public class Example2 {
    int counter = 5;

    public static void main(String[] args) {
        /*
         * Block Comment 1 2 2
         */
        System.out.println(2);
        System.out.println(3);
    }
}

public class SecondClass {

    public void print(int a) {
        System.out.println(a);
        // Line Comment 2
    }

    public static void main(String[] args) {
        System.out.println(2);
    }
}
