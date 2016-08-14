package de.hhu.knife.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

import de.hhu.knife.beans.KJavaMethod;
import de.hhu.knife.beans.KJavaParameter;

public class Mapper {

  public static KJavaMethod from(final JavaMethod javaMethod) {
    List<KJavaParameter> kJavaParameters =
        javaMethod.getParameters().stream().map(jp -> from(jp)).collect(Collectors.toList());
    return new KJavaMethod.Builder().parameters(kJavaParameters)
        .codeBlock(javaMethod.getCodeBlock()).sourceCode(javaMethod.getSourceCode())
        .name(javaMethod.getName()).isPublic(javaMethod.isPublic())
        .isPrivate(javaMethod.isPrivate()).isStatic(javaMethod.isStatic()).build();
  }

  public static KJavaParameter from(final JavaParameter javaParameter) {
    return new KJavaParameter.Builder().type(javaParameter.getType().getValue())
        .name(javaParameter.getName()).build();
  }

}
