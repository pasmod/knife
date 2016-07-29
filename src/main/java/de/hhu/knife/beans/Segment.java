package de.hhu.knife.beans;

import java.util.List;

public class Segment {
	@SuppressWarnings("unused")
	private List<KJavaClass> classes;

	public static class Builder {
		private List<KJavaClass> classes;

		public Builder classes(List<KJavaClass> classes) {
			this.classes = classes;
			return this;
		}

		public Segment build() {
			Segment segment = new Segment();
			segment.classes = classes;
			return segment;
		}
	}
}
