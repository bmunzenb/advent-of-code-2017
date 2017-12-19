package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Tubes {

	static enum Direction {
		UP, DOWN, LEFT, RIGHT, STOP;
	}

	public static class Result {

		public final String path;
		public final int steps;

		private Result(String path, int steps) {
			this.path = path;
			this.steps = steps;
		}
	}

	public static String[] parse(Path path) throws IOException {

		return Files.lines(path)
				.collect(Collectors.toList())
				.toArray(new String[] {});
	}

	public static Result walkPath(String[] input) {

		StringBuffer result = new StringBuffer();

		int steps = 0;
		int x = -1;
		int y = 0;
		Direction d = Direction.DOWN;

		// find the starting point
		while (input[y].charAt(++x) != '|');

		while (true) {

			char v = input[y].charAt(x);

			switch (v) {

			case '|':
			case '-':
				break;

			case '+':
				if (d != Direction.RIGHT && x > 0 && input[y].charAt(x-1) != ' ') {
					d = Direction.LEFT;
				}
				else if (d != Direction.LEFT && x < input[y].length() && input[y].charAt(x+1) != ' ') {
					d = Direction.RIGHT;
				}
				else if (d != Direction.DOWN && y > 0 && input[y-1].charAt(x) != ' ') {
					d = Direction.UP;
				}
				else if (d != Direction.UP && y < input.length && input[y+1].charAt(x) != ' ') {
					d = Direction.DOWN;
				}
				else {
					d = Direction.STOP;
				}
				break;

			case ' ':
				d = Direction.STOP;
				break;

			default:
				result.append(v);
				break;
			}

			switch (d) {
			case UP:
				y--;
				break;

			case DOWN:
				y++;
				break;

			case LEFT:
				x--;
				break;

			case RIGHT:
				x++;
				break;

			case STOP:
				return new Result(result.toString(), steps);
			}

			steps++;
		}
	}
}
