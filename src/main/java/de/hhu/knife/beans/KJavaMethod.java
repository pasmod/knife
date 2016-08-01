package de.hhu.knife.beans;

import java.util.List;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

import de.hhu.knife.beans.KJavaClass.Builder;

public class KJavaMethod {

	@SuppressWarnings("unused")
	private List<KJavaParameter> parameters;	
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
		private List<KJavaParameter> parameters;

		public Builder methodInformation(JavaMethod javaMethod){
			this.javaMethod = javaMethod;
			return this;
		}

		public Builder parameters(List<KJavaParameter> parameters) {
			this.parameters = parameters;
			return this;
		}				
		
		public KJavaMethod build() {
			KJavaMethod kJavaMethod = new KJavaMethod();
			kJavaMethod.codeBlock = javaMethod.getCodeBlock();
			kJavaMethod.methodName = javaMethod.getName();
			kJavaMethod.isPublic = javaMethod.isPublic();
			kJavaMethod.isPrivate = javaMethod.isPrivate();
			kJavaMethod.isStatic = javaMethod.isStatic();
			kJavaMethod.parameters = this.parameters;
			return kJavaMethod;
		}
	}
}
