package de.hhu.knife.extractors;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.thoughtworks.qdox.model.JavaClass;

import de.hhu.knife.beans.KJavaComment;
import de.hhu.knife.mappers.Mapper;

public class Extractor {
    public static List<KJavaComment> extractComments(final JavaClass javaClass) {
        try {
            CompilationUnit parser = JavaParser.parse(new StringReader(javaClass.getCodeBlock()));
            return parser.getComments()
                         .stream()
                         .map(c -> Mapper.from(c))
                         .collect(Collectors.toList());
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
