package de.hhu.knife.beans;

import com.thoughtworks.qdox.model.*;

public class KJavaParameter {

	private String type;
	private String parameterName;

	public static class Builder {
		private JavaParameter javaParameter;

		public Builder parameterInformation(JavaParameter javaParameter) {
			this.javaParameter = javaParameter;
			return this;
		}

		public KJavaParameter build() {
			KJavaParameter kJavaParameter = new KJavaParameter();
			kJavaParameter.type = this.javaParameter.getType().toString();
			kJavaParameter.parameterName = this.javaParameter.getName();
			return kJavaParameter;
		}
	}
}
