package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaParameter {
  private String type;
  private String name;

  public static class Builder {
    private String type;
    private String name;

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public KJavaParameter build() {
      return new KJavaParameter(this);
    }
  }

  private KJavaParameter(Builder builder) {
    this.type = builder.type;
    this.name = builder.name;
  }
}
