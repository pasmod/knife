package de.hhu.knife.beans;

import java.util.List;

import com.thoughtworks.qdox.model.JavaMethod;

public class KJavaMethod {

	@SuppressWarnings("unused")
	private List<KJavaParameter> parameters;
	@SuppressWarnings("unused")
	private String codeBlock;
	@SuppressWarnings("unused")
	private String sourceCode;
	@SuppressWarnings("unused")
	private boolean isStatic;
	@SuppressWarnings("unused")
	private boolean isPrivate;
	@SuppressWarnings("unused")
	private boolean isPublic;
	@SuppressWarnings("unused")
	private String methodName;
	@SuppressWarnings("unused")
	private KJavaDoc javaDoc;

	public static class Builder {
		private JavaMethod javaMethod;
		private List<KJavaParameter> parameters;
		private KJavaDoc javaDoc;

		public Builder methodInformation(JavaMethod javaMethod) {
			this.javaMethod = javaMethod;
			return this;
		}

		public Builder javaDoc(KJavaDoc kJavaDoc) {
			this.javaDoc = kJavaDoc;
			return this;
		}

		public Builder parameters(List<KJavaParameter> parameters) {
			this.parameters = parameters;
			return this;
		}

		public KJavaMethod build() {
			KJavaMethod kJavaMethod = new KJavaMethod();
			kJavaMethod.codeBlock = javaMethod.getCodeBlock();
			kJavaMethod.sourceCode = javaMethod.getSourceCode();
			kJavaMethod.methodName = javaMethod.getName();
			kJavaMethod.isPublic = javaMethod.isPublic();
			kJavaMethod.isPrivate = javaMethod.isPrivate();
			kJavaMethod.isStatic = javaMethod.isStatic();
			kJavaMethod.parameters = this.parameters;
			kJavaMethod.javaDoc = this.javaDoc;
			return kJavaMethod;
		}
	}
}
