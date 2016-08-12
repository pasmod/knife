package de.hhu.knife.beans;

public class Range {
	@SuppressWarnings("unused")
	private Position begin;
	@SuppressWarnings("unused")
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
			Range range = new Range();
			range.begin = begin;
			range.end = end;
			return range;
		}
	}
}
