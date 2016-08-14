package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class Range {
    public static class Builder {
        private Position begin;
        private Position end;

        public Builder begin(final Position begin) {
            this.begin = begin;
            return this;
        }

        public Range build() {
            return new Range(this);
        }

        public Builder end(final Position end) {
            this.end = end;
            return this;
        }
    }
    private final Position begin;

    private final Position end;

    private Range(final Builder builder) {
        this.begin = builder.begin;
        this.end = builder.end;
    }
}
