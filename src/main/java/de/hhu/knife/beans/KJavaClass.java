package de.hhu.knife.beans;

import java.util.List;

public class KJavaClass {
	public List<KJavaMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<KJavaMethod> methods) {
		this.methods = methods;
	}

	public KJavaClass(List<KJavaMethod> methods) {
		super();
		this.methods = methods;
	}

	private List<KJavaMethod> methods;

}
