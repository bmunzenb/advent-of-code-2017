package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SporificaVirus {

	public static class Point {

		public final int x;
		public final int y;

		public Point(int x, int y) {

			this.x = x;
			this.y = y;
		}

		public Point move(Direction direction) {

			switch (direction) {
			case UP:
				return new Point(x, y-1);
			case DOWN:
				return new Point(x, y+1);
			case LEFT:
				return new Point(x-1, y);
			case RIGHT:
				return new Point(x+1, y);
			default:
				return this;
			}
		}

		@Override public int hashCode() {

			final int prime = 31;

			int result = 1;

			result = prime * result + x;
			result = prime * result + y;

			return result;
		}

		@Override public boolean equals(Object obj) {

			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (getClass() != obj.getClass())
				return false;

			Point other = (Point) obj;

			if (x != other.x)
				return false;

			if (y != other.y)
				return false;

			return true;
		}

		@Override public String toString() {

			return "Point (" + x + "," + y + ")";
		}
	}

	private static enum Direction {

		UP, DOWN, LEFT, RIGHT;

		protected Direction turnLeft() {
			switch (this) {
			case UP:
				return LEFT;
			case DOWN:
				return RIGHT;
			case LEFT:
				return DOWN;
			case RIGHT:
				return UP;
			default:
				return null;
			}
		}

		protected Direction turnRight() {
			switch (this) {
			case UP:
				return RIGHT;
			case DOWN:
				return LEFT;
			case LEFT:
				return UP;
			case RIGHT:
				return DOWN;
			default:
				return null;
			}
		}

		protected Direction reverse() {
			switch (this) {
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			default:
				return null;
			}
		}
	}

	public static enum Status {

		CLEAN, WEAKENED, INFECTED, FLAGGED;

		protected Status next() {
			switch (this) {
			case CLEAN:
				return WEAKENED;
			case WEAKENED:
				return INFECTED;
			case INFECTED:
				return FLAGGED;
			case FLAGGED:
				return CLEAN;
			default:
				return this;
			}
		}
	}

	private Direction direction = Direction.UP;
	private Point point;

	public SporificaVirus(Point point) {

		this.point = point;
	}

	public boolean move(List<Point> infected) {

		boolean clean = infected.contains(point);

		if (clean) {
			direction = direction.turnRight();
			infected.remove(point);
		}
		else {
			direction = direction.turnLeft();
			infected.add(point);
		}

		point = point.move(direction);

		return !clean;
	}

	public int moveAndCountInfections(List<Point> infected, int moves) {

		int total = 0;

		for (int i = 0; i < moves; i++) {
			total += move(infected) ? 1 : 0;
		}

		return total;
	}

	public boolean move(Map<Point, Status> points) {

		Status status = points.getOrDefault(point, Status.CLEAN);

		switch (status) {

		case CLEAN:
			direction = direction.turnLeft();
			break;

		case WEAKENED:
			break;

		case INFECTED:
			direction = direction.turnRight();
			break;

		case FLAGGED:
			direction = direction.reverse();
			break;
		}

		status = status.next();
		points.put(point, status);
		point = point.move(direction);

		return status == Status.INFECTED;
	}

	public int moveAndCountInfections(Map<Point, Status> points, int moves) {

		int total = 0;

		for (int i = 0; i < moves; i++) {
			total += move(points) ? 1 : 0;
		}

		return total;
	}

	public static List<Point> parse(Path path) throws IOException {

		List<Point> points = new ArrayList<>();
		AtomicInteger line = new AtomicInteger(-1);

		Files.lines(path)
			.forEach(str -> points.addAll(parse(line.incrementAndGet(), str)));

		return points;
	}

	public static List<Point> parse(int y, String str) {

		List<Point> points = new ArrayList<>();

		int x = str.indexOf('#');
		while (x != -1) {
			points.add(new Point(x, y));
			x = str.indexOf('#', x+1);
		}

		return points;
	}
}
