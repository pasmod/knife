package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaField {
  private String codeBlock;
  private String type;
  private String fieldName;

  public static class Builder {
    private String codeBlock;
    private String type;
    private String fieldName;

    public Builder codeBlock(String codeBlock) {
      this.codeBlock = codeBlock;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder fieldName(String fieldName) {
      this.fieldName = fieldName;
      return this;
    }

    public KJavaField build() {
      return new KJavaField(this);
    }
  }

  private KJavaField(Builder builder) {
    this.codeBlock = builder.codeBlock;
    this.type = builder.type;
    this.fieldName = builder.fieldName;
  }
}
