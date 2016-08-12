package de.hhu.knife.beans;

public class KJavaComment {
	@SuppressWarnings("unused")
	private String type;
	@SuppressWarnings("unused")
	private String content;
	@SuppressWarnings("unused")
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
			KJavaComment kJavaComment = new KJavaComment();
			kJavaComment.type = type;
			kJavaComment.content = content;
			kJavaComment.range = range;
			return kJavaComment;
		}
	}
}
