package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaComment {
    public static class Builder {
        private String type;
        private String content;
        private KRange range;

        public KJavaComment build() {
            return new KJavaComment(this);
        }

        public Builder content(final String content) {
            this.content = content;
            return this;
        }

        public Builder range(final KRange range) {
            this.range = range;
            return this;
        }

        public Builder type(final String type) {
            this.type = type;
            return this;
        }
    }
    private final String type;
    private final String content;

    private final KRange range;

    private KJavaComment(final Builder builder) {
        this.type = builder.type;
        this.content = builder.content;
        this.range = builder.range;
    }
}
