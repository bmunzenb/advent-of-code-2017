package com.munzenberger.advent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpiralMemory {

	static class Pos {

		final int x;
		final int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pos other = (Pos) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		Pos up() {
			return new Pos(x, y+1);
		}

		Pos down() {
			return new Pos(x, y-1);
		}

		Pos left() {
			return new Pos(x-1, y);
		}

		Pos right() {
			return new Pos(x+1, y);
		}

		Pos setX(Pos x) {
			return new Pos(x.x, y);
		}

		Pos setY(Pos y) {
			return new Pos(x, y.y);
		}

		int abs() {
			return Math.abs(x) + Math.abs(y);
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

	static enum Dir {
		UP, DOWN, LEFT, RIGHT
	}

	public static int solvePart1(int input) {

		Pos pos = new Pos(0, 0);
		Pos max = pos;
		Pos min = pos;
		Dir dir = Dir.RIGHT;

		for (int i = 2; i <= input; i++) {

			switch (dir) {
			case LEFT:
				pos = pos.left();
				if (pos.x < min.x) {
					min = min.setX(pos);
					dir = Dir.DOWN;
				}
				break;

			case RIGHT:
				pos = pos.right();
				if (pos.x > max.x) {
					max = max.setX(pos);
					dir = Dir.UP;
				}
				break;

			case UP:
				pos = pos.up();
				if (pos.y > max.y) {
					max = max.setY(pos);
					dir = Dir.LEFT;
				}
				break;

			case DOWN:
				pos = pos.down();
				if (pos.y < min.y) {
					min = min.setY(pos);
					dir = Dir.RIGHT;
				}
				break;
			}
		}

		return pos.abs();
	}

	public static int solvePart2(int input) {

		Map<Pos, Integer> memory = new HashMap<>();

		Pos pos = new Pos(0, 0);
		memory.put(pos, 1);

		Pos max = pos;
		Pos min = pos;
		Dir dir = Dir.RIGHT;

		int value = 1;

		while (input >= value) {

			switch (dir) {
			case LEFT:
				pos = pos.left();
				value = calculateAndWriteValue(pos, memory);
				if (pos.x < min.x) {
					min = min.setX(pos);
					dir = Dir.DOWN;
				}
				break;

			case RIGHT:
				pos = pos.right();
				value = calculateAndWriteValue(pos, memory);
				if (pos.x > max.x) {
					max = max.setX(pos);
					dir = Dir.UP;
				}
				break;

			case UP:
				pos = pos.up();
				value = calculateAndWriteValue(pos, memory);
				if (pos.y > max.y) {
					max = max.setY(pos);
					dir = Dir.LEFT;
				}
				break;

			case DOWN:
				pos = pos.down();
				value = calculateAndWriteValue(pos, memory);
				if (pos.y < min.y) {
					min = min.setY(pos);
					dir = Dir.RIGHT;
				}
				break;
			}
		}

		return value;
	}

	private static int calculateAndWriteValue(Pos pos, Map<Pos, Integer> memory) {

		int value = Arrays.asList(
				pos.left().down(),
				pos.left(),
				pos.left().up(),
				pos.up(),
				pos.right().up(),
				pos.right(),
				pos.right().down(),
				pos.down())
		.stream()
		.map(memory::get)
		.filter(i -> i != null)
		.mapToInt(i -> i.intValue())
		.sum();

		memory.put(pos, value);

		//System.out.println(String.format("wrote value %d to position %s", value, pos));

		return value;
	}
}
