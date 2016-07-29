package de.hhu.knife.beans;

import java.util.List;

public class Segment {
	@SuppressWarnings("unused")
	private List<KJavaClass> classes;
	@SuppressWarnings("unused")
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
			Segment segment = new Segment();
			segment.classes = classes;
			segment.state = state;
			return segment;
		}
	}
}
