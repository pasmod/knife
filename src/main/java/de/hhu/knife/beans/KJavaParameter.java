package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaParameter {
  private String type;
  private String parameterName;

  public static class Builder {
    private String type;
    private String parameterName;

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder parameterName(String parameterName) {
      this.parameterName = parameterName;
      return this;
    }

    public KJavaParameter build() {
      return new KJavaParameter(this);
    }
  }

  private KJavaParameter(Builder builder) {
    this.type = builder.type;
    this.parameterName = builder.parameterName;
  }
}
