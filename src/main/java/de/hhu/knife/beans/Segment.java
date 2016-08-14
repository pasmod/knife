package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class Segment {
    public static class Builder {
        private List<KJavaClass> classes;
        private State state;

        public Segment build() {
            return new Segment(this);
        }

        public Builder classes(final List<KJavaClass> classes) {
            this.classes = classes;
            return this;
        }

        public Builder state(final State state) {
            this.state = state;
            return this;
        }
    }
    private final List<KJavaClass> classes;

    private final State state;

    private Segment(final Builder builder) {
        this.classes = builder.classes;
        this.state = builder.state;
    }
}
