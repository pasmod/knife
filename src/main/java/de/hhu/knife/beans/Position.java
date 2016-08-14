package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class Position {
  private int line;
  private int column;

  public static class Builder {
    private int line;
    private int column;

    public Builder line(int line) {
      this.line = line;
      return this;
    }

    public Builder column(int column) {
      this.column = column;
      return this;
    }

    public Position build() {
      return new Position(this);
    }
  }

  private Position(Builder builder) {
    this.line = builder.line;
    this.column = builder.column;
  }
}
