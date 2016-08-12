package de.hhu.knife.beans;

public class Position {
	@SuppressWarnings("unused")
	private int line;
	@SuppressWarnings("unused")
	private int column;

	public static class Builder {
		private int line;
		private int column;

		public Builder line(int line) {
			this.line = line;
			return this;
		}

		public Builder column(int column) {
			this.column = column;
			return this;
		}

		public Position build() {
			Position position = new Position();
			position.line = line;
			position.column = column;
			return position;
		}
	}
}
