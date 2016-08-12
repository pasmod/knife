package de.hhu.knife.beans;

public class KDocletTag {
	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private String value;

	public static class Builder {
		private String name;
		private String value;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder value(String value) {
			this.value = value;
			return this;
		}

		public KDocletTag build() {
			return new KDocletTag(this);
		}
	}

	private KDocletTag(Builder builder) {
		this.name = builder.name;
		this.value = builder.value;
	}
}
