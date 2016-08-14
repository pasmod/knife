package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaField {
    public static class Builder {
        private String codeBlock;
        private String type;
        private String name;

        public KJavaField build() {
            return new KJavaField(this);
        }

        public Builder codeBlock(final String codeBlock) {
            this.codeBlock = codeBlock;
            return this;
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

    private final String codeBlock;
    private final String type;

    private final String name;

    private KJavaField(final Builder builder) {
        this.codeBlock = builder.codeBlock;
        this.type = builder.type;
        this.name = builder.name;
    }
}
