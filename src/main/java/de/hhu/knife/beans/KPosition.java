package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KPosition {
    public static class Builder {
        private int line;
        private int column;

        public KPosition build() {
            return new KPosition(this);
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

    private KPosition(final Builder builder) {
        this.line = builder.line;
        this.column = builder.column;
    }
}
