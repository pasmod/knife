package de.hhu.knife;

import static spark.Spark.get;
import static spark.Spark.port;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

public class App {

	public static void main(String[] args) throws IOException {

		// Configure Spark
		port(4567);

		get("/methods", "application/json", (request, response) -> {
			final JavaProjectBuilder builder = new JavaProjectBuilder();
			final String clazz = request.queryParams("class");
			System.out.println(clazz);
			builder.addSource(new StringReader(clazz));
			JavaClass javaClass = builder.getClasses().stream().collect(Collectors.toList()).get(0);
			List<JavaMethod> javaMethods = javaClass.getMethods();
			List<String> methods = javaMethods.stream().map(m -> m.getCodeBlock()).collect(Collectors.toList());
			return methods;
		});
		;
	}

}
