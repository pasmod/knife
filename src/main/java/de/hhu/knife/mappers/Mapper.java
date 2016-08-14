package de.hhu.knife.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.Range;
import com.github.javaparser.ast.comments.Comment;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

import de.hhu.knife.beans.KJavaClass;
import de.hhu.knife.beans.KJavaComment;
import de.hhu.knife.beans.KJavaField;
import de.hhu.knife.beans.KJavaMethod;
import de.hhu.knife.beans.KJavaParameter;
import de.hhu.knife.beans.KPosition;
import de.hhu.knife.beans.KRange;
import de.hhu.knife.extractors.Extractor;

public class Mapper {

    public static KJavaClass from(final JavaClass javaClass) {
        final List<KJavaField> kJavaFields = javaClass.getFields()
                                                      .stream()
                                                      .map(jf -> from(jf))
                                                      .collect(Collectors.toList());
        final List<KJavaMethod> kJavaMethods = javaClass.getMethods()
                                                        .stream()
                                                        .map(jm -> from(jm))
                                                        .collect(Collectors.toList());
        final List<KJavaComment> kJavaComments = Extractor.extractComments(javaClass);
        return new KJavaClass.Builder().fields(kJavaFields)
                                       .methods(kJavaMethods)
                                       .packageName(javaClass.getPackageName())
                                       .name(javaClass.getName())
                                       .comments(kJavaComments)
                                       .isPrivate(javaClass.isPrivate())
                                       .isStatic(javaClass.isStatic())
                                       .isPublic(javaClass.isPublic())
                                       .build();
    }

    private static KJavaField from(final JavaField javaField) {
        return new KJavaField.Builder().name(javaField.getName())
                                       .type(javaField.getType()
                                                      .getName())
                                       .codeBlock(javaField.getCodeBlock())
                                       .build();
    }

    private static KJavaMethod from(final JavaMethod javaMethod) {
        final List<KJavaParameter> kJavaParameters = javaMethod.getParameters()
                                                               .stream()
                                                               .map(jp -> from(jp))
                                                               .collect(Collectors.toList());
        return new KJavaMethod.Builder().parameters(kJavaParameters)
                                        .codeBlock(javaMethod.getCodeBlock())
                                        .sourceCode(javaMethod.getSourceCode())
                                        .name(javaMethod.getName())
                                        .isPublic(javaMethod.isPublic())
                                        .isPrivate(javaMethod.isPrivate())
                                        .isStatic(javaMethod.isStatic())
                                        .build();
    }

    private static KJavaParameter from(final JavaParameter javaParameter) {
        return new KJavaParameter.Builder().type(javaParameter.getType()
                                                              .getValue())
                                           .name(javaParameter.getName())
                                           .build();
    }

    private static KRange from(final Range range) {
        final KPosition begin = new KPosition.Builder().line(range.begin.line)
                                                       .column(range.begin.column)
                                                       .build();
        final KPosition end = new KPosition.Builder().line(range.end.line)
                                                     .column(range.end.column)
                                                     .build();
        return new KRange.Builder().begin(begin)
                                   .end(end)
                                   .build();
    }

    public static KJavaComment from(final Comment comment) {
        return new KJavaComment.Builder().content(comment.getContent())
                                         .type(comment.getClass()
                                                      .getSimpleName())
                                         .range(Mapper.from(comment.getRange()))
                                         .build();
    }

}
