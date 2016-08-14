package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class KJavaComment {
    private String type;
    private String content;
    private Range range;

    public static class Builder {
	private String type;
	private String content;
	private Range range;

	public Builder type(String type) {
	    this.type = type;
	    return this;
	}

	public Builder content(String content) {
	    this.content = content;
	    return this;
	}

	public Builder range(Range range) {
	    this.range = range;
	    return this;
	}

	public KJavaComment build() {
	    return new KJavaComment(this);
	}
    }

    private KJavaComment(Builder builder) {
	this.type = builder.type;
	this.content = builder.content;
	this.range = builder.range;
    }
}
