package de.hhu.knife.beans;

import com.thoughtworks.qdox.model.*;

public class KJavaField {
	private String codeBlock;
	private String type;
	private String fieldName;

	public static class Builder {
		private JavaField javaField;

		public Builder fieldInformation(JavaField javaField) {
			this.javaField = javaField;
			return this;
		}

		public KJavaField build() {
			KJavaField kJavaField = new KJavaField();
			kJavaField.codeBlock = this.javaField.getCodeBlock();
			kJavaField.type = this.javaField.getType().toString();
			kJavaField.fieldName = this.javaField.getName();
			return kJavaField;
		}
	}
}
