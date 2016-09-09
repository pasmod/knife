package de.hhu.knife;

import static spark.Spark.port;
import static spark.Spark.post;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.parser.ParseException;

import de.hhu.knife.beans.KJavaClass;
import de.hhu.knife.beans.Segment;
import de.hhu.knife.beans.State;
import de.hhu.knife.mappers.Mapper;
import de.hhu.knife.transformers.JsonTransformer;

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
