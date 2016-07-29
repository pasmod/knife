package de.hhu.knife.beans;

public class KJavaMethod {
	public String getCodeBlock() {
		return codeBlock;
	}

	public void setCodeBlock(String codeBlock) {
		this.codeBlock = codeBlock;
	}

	public KJavaMethod(String codeBlock) {
		super();
		this.codeBlock = codeBlock;
	}

	private String codeBlock;

}
