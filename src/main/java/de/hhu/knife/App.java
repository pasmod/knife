package de.hhu.knife;

import static spark.Spark.port;
import static spark.Spark.post;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.parser.ParseException;

import de.hhu.knife.beans.KJavaClass;
import de.hhu.knife.beans.KJavaMethod;
import de.hhu.knife.beans.Segment;
import de.hhu.knife.beans.State;
import de.hhu.knife.transformers.JsonTransformer;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException {

		// Configure Spark
		port(4567);

		post("/extract", "application/json", (request, response) -> {
			// TODO: It is necessary to init the builder for each request?
			final JavaProjectBuilder builder = new JavaProjectBuilder();
			// TODO: Exception handling for the case that query param does not exist
			String queryParams = request.queryParams("class");
			try {
				builder.addSource(new StringReader(queryParams));
			} catch (ParseException e) {
				logger.error(String.format("Error whild parsing the code: %s", queryParams), e);
				return new Segment.Builder().state(State.PARSE_ERROR).build();
			}
			List<KJavaClass> kJavaClasses = new ArrayList<>();
			for (List<JavaMethod> javaMethodsList : builder.getClasses().stream().collect(Collectors.toList()).stream()
					.map(clazz -> clazz.getMethods()).collect(Collectors.toList())) {
				List<KJavaMethod> kJavaMethods = new ArrayList<>();
				for (JavaMethod javaMethod : javaMethodsList) {
					kJavaMethods.add(new KJavaMethod.Builder().codeBlock(javaMethod.getCodeBlock()).build());
				}
				kJavaClasses.add(new KJavaClass.Builder().methods(kJavaMethods).build());
			}

			return new Segment.Builder().classes(kJavaClasses).build();
		}, new JsonTransformer());
	}

}
