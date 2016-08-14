package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaField {
    private String codeBlock;
    private String type;
    private String name;

    public static class Builder {
	private String codeBlock;
	private String type;
	private String name;

	public Builder codeBlock(String codeBlock) {
	    this.codeBlock = codeBlock;
	    return this;
	}

	public Builder type(String type) {
	    this.type = type;
	    return this;
	}

	public Builder name(String name) {
	    this.name = name;
	    return this;
	}

	public KJavaField build() {
	    return new KJavaField(this);
	}
    }

    private KJavaField(Builder builder) {
	this.codeBlock = builder.codeBlock;
	this.type = builder.type;
	this.name = builder.name;
    }
}
