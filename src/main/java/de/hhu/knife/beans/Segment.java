package de.hhu.knife.beans;

import java.util.List;

@SuppressWarnings("unused")
public class Segment {
    private List<KJavaClass> classes;
    private State state;

    public static class Builder {
	private List<KJavaClass> classes;
	private State state;

	public Builder classes(List<KJavaClass> classes) {
	    this.classes = classes;
	    return this;
	}

	public Builder state(State state) {
	    this.state = state;
	    return this;
	}

	public Segment build() {
	    return new Segment(this);
	}
    }

    private Segment(Builder builder) {
	this.classes = builder.classes;
	this.state = builder.state;
    }
}
