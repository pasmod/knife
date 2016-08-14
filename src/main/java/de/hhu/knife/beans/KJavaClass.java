package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class KJavaClass {
  private List<KJavaField> fields;
  private List<KJavaMethod> methods;
  private String packageName;
  private String className;
  private List<KJavaComment> comments;
  private boolean implementsInterfaces;
  private boolean isStatic;
  private boolean isPrivate;
  private boolean isPublic;

  public static class Builder {
    private List<KJavaField> fields;
    private List<KJavaMethod> methods;
    private String packageName;
    private String className;
    private List<KJavaComment> comments;
    private boolean implementsInterfaces;
    private boolean isStatic;
    private boolean isPrivate;
    private boolean isPublic;

    public Builder fields(List<KJavaField> fields) {
      this.fields = fields;
      return this;
    }

    public Builder methods(List<KJavaMethod> methods) {
      this.methods = methods;
      return this;
    }

    public Builder packageName(String packageName) {
      this.packageName = packageName;
      return this;
    }

    public Builder className(String className) {
      this.className = className;
      return this;
    }

    public Builder comments(List<KJavaComment> comments) {
      this.comments = comments;
      return this;
    }

    public Builder implementsInterfaces(boolean implementsInterfaces) {
      this.implementsInterfaces = implementsInterfaces;
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

    public KJavaClass build() {
      return new KJavaClass(this);
    }
  }

  private KJavaClass(Builder builder) {
    this.fields = builder.fields;
    this.methods = builder.methods;
    this.packageName = builder.packageName;
    this.className = builder.className;
    this.comments = builder.comments;
    this.implementsInterfaces = builder.implementsInterfaces;
    this.isStatic = builder.isStatic;
    this.isPrivate = builder.isPrivate;
    this.isPublic = builder.isPublic;
  }
}
