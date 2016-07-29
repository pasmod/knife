package de.hhu.knife;

import static spark.Spark.port;
import static spark.Spark.post;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.qdox.JavaProjectBuilder;

import de.hhu.knife.transformers.JsonTransformer;

public class App {

	public static void main(String[] args) throws IOException {

		// Configure Spark
		port(4567);

		post("/method/blocks", "application/json", (request, response) -> {
			// TODO: It is necessary to init the builder for each request?
			// TODO: Add exception handling (case: code not parsable)
			final JavaProjectBuilder builder = new JavaProjectBuilder();
			builder.addSource(new StringReader(request.queryParams("class")));
			List<String> methods = builder.getClasses().stream().collect(Collectors.toList()).get(0).getMethods()
					.stream().map(m -> m.getCodeBlock()).collect(Collectors.toList());
			return methods;
		}, new JsonTransformer());
	}

}
