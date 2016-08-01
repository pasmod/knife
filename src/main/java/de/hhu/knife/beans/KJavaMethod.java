package de.hhu.knife.beans;

import com.thoughtworks.qdox.model.JavaMethod;

public class KJavaMethod {

	@SuppressWarnings("unused")
	private String codeBlock;
	@SuppressWarnings("unused")
	private boolean isStatic;
	@SuppressWarnings("unused")
	private boolean isPrivate;
	@SuppressWarnings("unused")
	private boolean isPublic;	
	@SuppressWarnings("unused")
	private String methodName;

	public static class Builder {
		private JavaMethod javaMethod;

		public Builder methodInformation(JavaMethod javaMethod){
			this.javaMethod = javaMethod;
			return this;
		}

		public KJavaMethod build() {
			KJavaMethod kJavaMethod = new KJavaMethod();
			kJavaMethod.codeBlock = javaMethod.getCodeBlock();
			kJavaMethod.methodName = javaMethod.getName();
			kJavaMethod.isPublic = javaMethod.isPublic();
			kJavaMethod.isPrivate = javaMethod.isPrivate();
			kJavaMethod.isStatic = javaMethod.isStatic();			
			return kJavaMethod;
		}
	}
}
