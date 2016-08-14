package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class Position {
    public static class Builder {
        private int line;
        private int column;

        public Position build() {
            return new Position(this);
        }

        public Builder column(final int column) {
            this.column = column;
            return this;
        }

        public Builder line(final int line) {
            this.line = line;
            return this;
        }
    }
    private final int line;

    private final int column;

    private Position(final Builder builder) {
        this.line = builder.line;
        this.column = builder.column;
    }
}
