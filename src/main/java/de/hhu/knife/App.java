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
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.parser.ParseException;

import de.hhu.knife.beans.KDocletTag;
import de.hhu.knife.beans.KJavaClass;
import de.hhu.knife.beans.KJavaDoc;
import de.hhu.knife.beans.KJavaField;
import de.hhu.knife.beans.KJavaMethod;
import de.hhu.knife.beans.KJavaParameter;
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
			for (JavaClass javaClass : builder.getClasses().stream().collect(Collectors.toList())) {
				List<KJavaMethod> kJavaMethods = new ArrayList<>();
				List<KJavaField> kJavaFields = new ArrayList<>();
				for (JavaMethod javaMethod : javaClass.getMethods()) {
					String comment = javaMethod.getComment();
					List<DocletTag> tags = javaMethod.getTags();
					List<KDocletTag> kDocletTags = new ArrayList<>();
					for (DocletTag tag : tags) {
						kDocletTags.add(new KDocletTag.Builder().name(tag.getName()).value(tag.getValue()).build());
					}
					KJavaDoc kJavaDoc = new KJavaDoc.Builder().text(comment).tags(kDocletTags).build();
					List<KJavaParameter> javaParameters = new ArrayList<>();
					for (JavaParameter javaParameter : javaMethod.getParameters()) {
						javaParameters.add(new KJavaParameter.Builder().parameterInformation(javaParameter).build());
					}
					kJavaMethods.add(new KJavaMethod.Builder().methodInformation(javaMethod).parameters(javaParameters)
							.javaDoc(kJavaDoc).build());
				}
				for (JavaField javaField : javaClass.getFields()) {
					kJavaFields.add(new KJavaField.Builder().fieldInformation(javaField).build());
				}
				kJavaClasses.add(new KJavaClass.Builder().classInformation(javaClass).methods(kJavaMethods)
						.fields(kJavaFields).build());
			}
			return new Segment.Builder().classes(kJavaClasses).state(State.OK).build();
		}, new JsonTransformer());
	}

}
