package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class KJavaMethod {
  private List<KJavaParameter> parameters;
  private String codeBlock;
  private String sourceCode;
  private String name;
  private boolean isStatic;
  private boolean isPrivate;
  private boolean isPublic;

  public static class Builder {
    private List<KJavaParameter> parameters;
    private String codeBlock;
    private String sourceCode;
    private String name;
    private boolean isStatic;
    private boolean isPrivate;
    private boolean isPublic;

    public Builder parameters(List<KJavaParameter> parameters) {
      this.parameters = parameters;
      return this;
    }

    public Builder codeBlock(String codeBlock) {
      this.codeBlock = codeBlock;
      return this;
    }

    public Builder sourceCode(String sourceCode) {
      this.sourceCode = sourceCode;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder isStatic(boolean isStatic) {
      this.isStatic = isStatic;
      return this;
    }

    public Builder isPrivate(boolean isPrivate) {
      this.isPrivate = isPrivate;
      return this;
    }

    public Builder isPublic(boolean isPublic) {
      this.isPublic = isPublic;
      return this;
    }

    public KJavaMethod build() {
      return new KJavaMethod(this);
    }
  }

  private KJavaMethod(Builder builder) {
    this.parameters = builder.parameters;
    this.codeBlock = builder.codeBlock;
    this.sourceCode = builder.sourceCode;
    this.name = builder.name;
    this.isStatic = builder.isStatic;
    this.isPrivate = builder.isPrivate;
    this.isPublic = builder.isPublic;
  }
}
