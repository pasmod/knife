package de.hhu.knife.extractors;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import com.thoughtworks.qdox.model.JavaClass;

import de.hhu.knife.beans.KJavaComment;
import de.hhu.knife.beans.Position;
import de.hhu.knife.beans.Range;

public class Extractor {

  public static List<KJavaComment> extractComments(final JavaClass javaClass) {
    List<Comment> comments = new ArrayList<>();
    List<KJavaComment> kJavaComments = new ArrayList<>();
    CompilationUnit parser;
    try {
      parser = JavaParser.parse(new StringReader(javaClass.getCodeBlock()));
      comments = parser.getComments();
      for (Comment c : comments) {  
        Position begin = new Position.Builder().line(c.getRange().begin.line)
            .column(c.getRange().begin.column).build();
        Position end = new Position.Builder().line(c.getRange().end.line)
            .column(c.getRange().end.column).build();
        kJavaComments.add(
            new KJavaComment.Builder().content(c.getContent()).type(c.getClass().getSimpleName())
                .range(new Range.Builder().begin(begin).end(end).build()).build());
      }
      return kJavaComments;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
