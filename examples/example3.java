package de.hhu.knife;

/*
 * This is an example of a file that consists of two
 * classes and none of the classes have a syntax error.
 */
public class Example2 {
    int counter = 5;

    /**
     * Main method of the example
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(2);
        System.out.println(3);
    }
    
    public int add(int a, int b){
        /*
         * Block Comment
         * Line 2
         */
        return a + b;
    }
}

public class SecondClass {

    public void print(int a) {
        System.out.println(a);
        // Line Comment 2
    }

    /**
     * Main method of the example
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(2);
    }
}
