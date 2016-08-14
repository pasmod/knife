package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaParameter {
    public static class Builder {
        private String type;
        private String name;

        public KJavaParameter build() {
            return new KJavaParameter(this);
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder type(final String type) {
            this.type = type;
            return this;
        }
    }
    private final String type;

    private final String name;

    private KJavaParameter(final Builder builder) {
        this.type = builder.type;
        this.name = builder.name;
    }
}
