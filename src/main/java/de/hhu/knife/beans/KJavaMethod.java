package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class KJavaMethod {
    public static class Builder {
        private List<KJavaParameter> parameters;
        private String codeBlock;
        private String sourceCode;
        private String name;
        private boolean isStatic;
        private boolean isPrivate;
        private boolean isPublic;

        public KJavaMethod build() {
            return new KJavaMethod(this);
        }

        public Builder codeBlock(final String codeBlock) {
            this.codeBlock = codeBlock;
            return this;
        }

        public Builder isPrivate(final boolean isPrivate) {
            this.isPrivate = isPrivate;
            return this;
        }

        public Builder isPublic(final boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public Builder isStatic(final boolean isStatic) {
            this.isStatic = isStatic;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder parameters(final List<KJavaParameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder sourceCode(final String sourceCode) {
            this.sourceCode = sourceCode;
            return this;
        }
    }

    private final List<KJavaParameter> parameters;
    private final String codeBlock;
    private final String sourceCode;
    private final String name;
    private final boolean isStatic;
    private final boolean isPrivate;

    private final boolean isPublic;

    private KJavaMethod(final Builder builder) {
        this.parameters = builder.parameters;
        this.codeBlock = builder.codeBlock;
        this.sourceCode = builder.sourceCode;
        this.name = builder.name;
        this.isStatic = builder.isStatic;
        this.isPrivate = builder.isPrivate;
        this.isPublic = builder.isPublic;
    }
}
