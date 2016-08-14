package de.hhu.knife.beans;

@SuppressWarnings("unused")
public class Range {
    private Position begin;
    private Position end;

    public static class Builder {
	private Position begin;
	private Position end;

	public Builder begin(Position begin) {
	    this.begin = begin;
	    return this;
	}

	public Builder end(Position end) {
	    this.end = end;
	    return this;
	}

	public Range build() {
	    return new Range(this);
	}
    }

    private Range(Builder builder) {
	this.begin = builder.begin;
	this.end = builder.end;
    }
}
