package de.hhu.knife.beans;

import java.util.List;

import com.thoughtworks.qdox.model.JavaClass;

public class KJavaClass {
	@SuppressWarnings("unused")
	private List<KJavaField> fields;
	@SuppressWarnings("unused")
	private List<KJavaMethod> methods;
	@SuppressWarnings("unused")
	private boolean implementsInterfaces;
	@SuppressWarnings("unused")
	private boolean isStatic;
	@SuppressWarnings("unused")
	private boolean isPrivate;
	@SuppressWarnings("unused")
	private boolean isPublic;
	@SuppressWarnings("unused")
	private String packageName;
	@SuppressWarnings("unused")
	private String className;
	@SuppressWarnings("unused")
	private List<KJavaComment> comments;

	public static class Builder {
		private List<KJavaMethod> methods;
		private List<KJavaField> fields;
		private JavaClass javaClass;
		private List<KJavaComment> comments;

		public Builder methods(List<KJavaMethod> methods) {
			this.methods = methods;
			return this;
		}

		public Builder fields(List<KJavaField> fields) {
			this.fields = fields;
			return this;
		}

		public Builder classInformation(JavaClass javaClass) {
			this.javaClass = javaClass;
			return this;
		}

		public Builder comments(List<KJavaComment> comments) {
			this.comments = comments;
			return this;
		}

		public KJavaClass build() {
			KJavaClass kJavaClass = new KJavaClass();
			kJavaClass.className = javaClass.getName();
			kJavaClass.isPublic = javaClass.isPublic();
			kJavaClass.isPrivate = javaClass.isPrivate();
			kJavaClass.isStatic = javaClass.isStatic();
			kJavaClass.packageName = javaClass.getPackageName();
			kJavaClass.implementsInterfaces = javaClass.getInterfaces().size() > 0;
			kJavaClass.methods = methods;
			kJavaClass.fields = fields;
			kJavaClass.comments = comments;
			return kJavaClass;
		}
	}
}
