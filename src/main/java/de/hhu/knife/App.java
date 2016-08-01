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

import com.thoughtworks.qdox.model.*;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.parser.ParseException;

import de.hhu.knife.beans.*;
import de.hhu.knife.transformers.JsonTransformer;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException {

		// Configure Spark
		port(4567);

		post("/extract", "application/json", (request, response) -> {
			// TODO: It is necessary to init the builder for each request?
			final JavaProjectBuilder builder = new JavaProjectBuilder();
			// TODO: Exception handling for the case that query param does not
			// exist
			String queryParams = request.queryParams("class");
			System.out.println(queryParams);
			// TODO: The case strange should be investigated more. Why kinds of
			// codes result in Strange?!
			try {
				builder.addSource(new StringReader(queryParams));
			} catch (ParseException e) {
				logger.error(String.format("Error whild parsing the code: %s", queryParams), e);
				return new Segment.Builder().state(State.PARSE_ERROR).build();
			} catch (RuntimeException e) {
				logger.error(String.format("Something strange happend: %s", queryParams), e);
				return new Segment.Builder().state(State.STRANGE).build();
			}

			List<KJavaClass> kJavaClasses = new ArrayList<>();
			for(JavaClass javaClass : builder.getClasses().stream().collect(Collectors.toList()))
			{
				List<KJavaMethod> kJavaMethods = new ArrayList<>();
				List<KJavaField> kJavaFields = new ArrayList<>();
				for (JavaMethod javaMethod : javaClass.getMethods()) {
					List<KJavaParameter> javaParameters = new ArrayList<>();
					for(JavaParameter javaParameter : javaMethod.getParameters()){
						javaParameters.add(new KJavaParameter.Builder().parameterInformation(javaParameter).build());
					}
					kJavaMethods.add(new KJavaMethod.Builder().methodInformation(javaMethod).parameters(javaParameters).build());
				}
				for(JavaField javaField : javaClass.getFields())
				{
					kJavaFields.add(new KJavaField.Builder().fieldInformation(javaField).build());
				}
				kJavaClasses.add(new KJavaClass.Builder().classInformation(javaClass).methods(kJavaMethods).fields(kJavaFields).build());
			}
			return new Segment.Builder().classes(kJavaClasses).state(State.OK).build();
		}, new JsonTransformer());
	}

}
