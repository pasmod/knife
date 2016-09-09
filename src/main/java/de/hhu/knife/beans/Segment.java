package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class Segment {
    public static class Builder {
        private List<KJavaClass> classes;
        private State state;
        private List<String> imports;

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

        public Builder imports(final List<String> imports) {
            this.imports = imports;
            return this;
        }
    }

    private final List<KJavaClass> classes;

    private final State state;
    private final List<String> imports;

    private Segment(final Builder builder) {
        this.classes = builder.classes;
        this.state = builder.state;
        this.imports = builder.imports;
    }
}
