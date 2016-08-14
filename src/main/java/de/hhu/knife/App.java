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

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        // Configure Spark
        port(4567);
        post("/extract", "application/json", (request, response) -> {
            final JavaProjectBuilder builder = new JavaProjectBuilder();
            final String queryParams = request.queryParams("class");
            try {
                builder.addSource(new StringReader(queryParams));
            } catch (final ParseException e) {
                logger.error(String.format("Error whild parsing the code: %s", queryParams), e);
                return new Segment.Builder().state(State.PARSE_ERROR)
                                            .build();
            } catch (final RuntimeException e) {
                logger.error(String.format("Something strange happend: %s", queryParams), e);
                return new Segment.Builder().state(State.STRANGE)
                                            .build();
            }

            final List<KJavaClass> kJavaClasses = builder.getClasses()
                                                   .stream()
                                                   .map(jc -> Mapper.from(jc))
                                                   .collect(Collectors.toList());

            final Segment segment = new Segment.Builder().classes(kJavaClasses)
                                                   .state(State.OK)
                                                   .build();
            return segment;

        }, new JsonTransformer());
    }

}
