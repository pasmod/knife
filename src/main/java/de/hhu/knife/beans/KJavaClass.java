package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class KJavaClass {
    public static class Builder {
        private List<KJavaField> fields;
        private List<KJavaMethod> methods;
        private String packageName;
        private String name;
        private List<KJavaComment> comments;
        private boolean isStatic;
        private boolean isPrivate;
        private boolean isPublic;

        public KJavaClass build() {
            return new KJavaClass(this);
        }

        public Builder comments(final List<KJavaComment> comments) {
            this.comments = comments;
            return this;
        }

        public Builder fields(final List<KJavaField> fields) {
            this.fields = fields;
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

        public Builder methods(final List<KJavaMethod> methods) {
            this.methods = methods;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder packageName(final String packageName) {
            this.packageName = packageName;
            return this;
        }
    }
    private final List<KJavaField> fields;
    private final String packageName;
    private final String name;
    private final List<KJavaMethod> methods;
    private final List<KJavaComment> comments;
    private final boolean isStatic;
    private final boolean isPrivate;

    private final boolean isPublic;

    private KJavaClass(final Builder builder) {
        this.fields = builder.fields;
        this.methods = builder.methods;
        this.packageName = builder.packageName;
        this.name = builder.name;
        this.comments = builder.comments;
        this.isStatic = builder.isStatic;
        this.isPrivate = builder.isPrivate;
        this.isPublic = builder.isPublic;
    }
}
