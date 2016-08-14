package de.hhu.knife;

import static spark.Spark.port;
import static spark.Spark.post;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.parser.ParseException;

import de.hhu.knife.beans.KJavaClass;
import de.hhu.knife.beans.KJavaComment;
import de.hhu.knife.beans.KJavaField;
import de.hhu.knife.beans.KJavaMethod;
import de.hhu.knife.beans.KJavaParameter;
import de.hhu.knife.beans.Position;
import de.hhu.knife.beans.Range;
import de.hhu.knife.beans.Segment;
import de.hhu.knife.beans.State;
import de.hhu.knife.transformers.JsonTransformer;

public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    // Configure Spark
    port(4567);
    post("/extract", "application/json", (request, response) -> {
      final JavaProjectBuilder builder = new JavaProjectBuilder();
      String queryParams = request.queryParams("class");
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
        List<KJavaComment> kJavaComments = extractComments(javaClass);
        List<KJavaMethod> kJavaMethods = new ArrayList<>();
        List<KJavaField> kJavaFields = new ArrayList<>();
        for (JavaMethod javaMethod : javaClass.getMethods()) {
          extractMethod(kJavaMethods, javaMethod);
        }
        for (JavaField javaField : javaClass.getFields()) {
          KJavaField build = new KJavaField.Builder().fieldName(javaField.getName())
              .type(javaField.getType().getName()).codeBlock(javaField.getCodeBlock()).build();
          kJavaFields.add(build);
        }
        KJavaClass build = new KJavaClass.Builder().fields(kJavaFields).methods(kJavaMethods)
            .packageName(javaClass.getPackageName()).name(javaClass.getName())
            .comments(kJavaComments).isPrivate(javaClass.isPrivate()).isStatic(javaClass.isStatic())
            .isPublic(javaClass.isPublic()).build();
        kJavaClasses.add(build);
      }

      Segment segment = new Segment.Builder().classes(kJavaClasses).state(State.OK).build();
      return segment;

    }, new JsonTransformer());
  }

  private static void extractMethod(List<KJavaMethod> kJavaMethods, JavaMethod javaMethod) {
    List<KJavaParameter> javaParameters = new ArrayList<>();
    for (JavaParameter javaParameter : javaMethod.getParameters()) {
      javaParameters.add(new KJavaParameter.Builder().type(javaParameter.getType().getValue())
          .name(javaParameter.getName()).build());
    }
    kJavaMethods.add(new KJavaMethod.Builder().parameters(javaParameters)
        .codeBlock(javaMethod.getCodeBlock()).sourceCode(javaMethod.getSourceCode())
        .name(javaMethod.getName()).isPublic(javaMethod.isPublic())
        .isPrivate(javaMethod.isPrivate()).isStatic(javaMethod.isStatic()).build());
  }

  private static List<KJavaComment> extractComments(JavaClass javaClass)
      throws com.github.javaparser.ParseException {
    List<Comment> comments = new ArrayList<>();
    List<KJavaComment> kJavaComments = new ArrayList<>();
    CompilationUnit parser = JavaParser.parse(new StringReader(javaClass.getCodeBlock()));

    comments = parser.getComments();
    for (Comment c : comments) {
      Position begin = new Position.Builder().line(c.getRange().begin.line)
          .column(c.getRange().begin.column).build();
      Position end = new Position.Builder().line(c.getRange().end.line)
          .column(c.getRange().end.column).build();
      kJavaComments
          .add(new KJavaComment.Builder().content(c.getContent()).type(c.getClass().getSimpleName())
              .range(new Range.Builder().begin(begin).end(end).build()).build());
    }
    return kJavaComments;
  }

}
