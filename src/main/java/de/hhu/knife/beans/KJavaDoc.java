package de.hhu.knife.beans;

import java.util.List;

public class KJavaDoc {
	@SuppressWarnings("unused")
	private String text;
	@SuppressWarnings("unused")
	private List<KDocletTag> tags;

	public static class Builder {
		private String text;
		private List<KDocletTag> tags;

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder tags(List<KDocletTag> tags) {
			this.tags = tags;
			return this;
		}

		public KJavaDoc build() {
			return new KJavaDoc(this);
		}
	}

	private KJavaDoc(Builder builder) {
		this.text = builder.text;
		this.tags = builder.tags;
	}
}
