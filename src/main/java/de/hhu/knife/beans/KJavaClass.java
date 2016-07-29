package de.hhu.knife.beans;

import java.util.List;

public class KJavaClass {
	@SuppressWarnings("unused")
	private List<KJavaMethod> methods;

	public static class Builder {
		private List<KJavaMethod> methods;

		public Builder methods(List<KJavaMethod> methods) {
			this.methods = methods;
			return this;
		}

		public KJavaClass build() {
			KJavaClass kJavaClass = new KJavaClass();
			kJavaClass.methods = methods;
			return kJavaClass;
		}
	}
}
