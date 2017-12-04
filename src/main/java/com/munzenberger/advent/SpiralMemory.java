package com.munzenberger.advent;

public class SpiralMemory {

	static enum Dir {
		UP, DOWN, LEFT, RIGHT
	}

	public static int solvePart1(int input) {

		int x = 0, y = 0;
		int maxX = x, maxY = y;
		int minX = x, minY = y;
		Dir dir = Dir.RIGHT;

		for (int i = 2; i <= input; i++) {

			switch (dir) {
			case LEFT:
				x--;
				if (x < minX) {
					minX = x;
					dir = Dir.DOWN;
				}
				break;

			case RIGHT:
				x++;
				if (x > maxX) {
					maxX = x;
					dir = Dir.UP;
				}
				break;

			case UP:
				y++;
				if (y > maxY) {
					maxY = y;
					dir = Dir.LEFT;
				}
				break;

			case DOWN:
				y--;
				if (y < minY) {
					minY = y;
					dir = Dir.RIGHT;
				}
				break;
			}
		}

		return Math.abs(x) + Math.abs(y);
	}

	public static int solvePart2(int input) {

		

		return 0;
	}
}
