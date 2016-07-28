package de.hhu.knife;

import static spark.Spark.get;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {

		get("/methods", (req, res) -> "Hello World");
	}

}
