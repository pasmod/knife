package de.hhu.knife.beans;

public class KJavaMethod {

	@SuppressWarnings("unused")
	private String codeBlock;

	public static class Builder {
		private String codeBlock;

		public Builder codeBlock(String codeBlock) {
			this.codeBlock = codeBlock;
			return this;
		}

		public KJavaMethod build() {
			KJavaMethod kJavaMethod = new KJavaMethod();
			kJavaMethod.codeBlock = codeBlock;
			return kJavaMethod;
		}
	}
}
