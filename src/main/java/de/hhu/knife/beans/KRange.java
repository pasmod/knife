package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KRange {
    public static class Builder {
        private KPosition begin;
        private KPosition end;

        public Builder begin(final KPosition begin) {
            this.begin = begin;
            return this;
        }

        public KRange build() {
            return new KRange(this);
        }

        public Builder end(final KPosition end) {
            this.end = end;
            return this;
        }
    }
    private final KPosition begin;

    private final KPosition end;

    private KRange(final Builder builder) {
        this.begin = builder.begin;
        this.end = builder.end;
    }
}
