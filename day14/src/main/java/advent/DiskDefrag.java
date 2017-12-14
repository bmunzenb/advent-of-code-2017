package advent;

public class DiskDefrag {

	public static int countTotalUsedSquares(boolean[][] grid) {

		int total = 0;

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				total += grid[x][y] ? 1 : 0;
			}
		}

		return total;
	}

	public static int countRegions(boolean[][] grid) {

		int count = 0;

		int[][] regions = new int[128][128];

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {

				if (regions[x][y] == 0 && grid[x][y]) {
					buildRegion(++count, grid, x, y, regions);
				}
			}
		}

		return count;
	}

	private static void buildRegion(int region, boolean[][] grid, int x, int y, int[][] regions) {

		// check that the coordinates are in range
		if ((0 <= x && x < grid.length) && (0 <= y && y < grid[x].length)) {

			// only process if the bit is set at this coordinate
			if (grid[x][y]) {

				// check if this coordinate has already been processed
				if (regions[x][y] == region) {
					return;
				}

				regions[x][y] = region;

				buildRegion(region, grid, x-1, y, regions);
				buildRegion(region, grid, x+1, y, regions);
				buildRegion(region, grid, x, y-1, regions);
				buildRegion(region, grid, x, y+1, regions);
			}
		}
	}

	public static boolean[][] generateGrid(String input) {

		boolean[][] grid = new boolean[128][128];

		for (int y = 0; y < 128; y++) {

			String string = String.format("%s-%d", input, y);
			String hash = KnotHash.generate(string);

			convertToBits(hash, grid[y]);
		}

		return grid;
	}

	public static void convertToBits(String hash, boolean[] bits) {

		for (int i = 0; i < hash.length(); i++) {

			char c = hash.charAt(i);
			convertToBits(c, bits, i * 4);
		}
	}

	public static void convertToBits(char c, boolean[] bits, int offset) {

		convertToBits( Integer.parseInt(Character.toString(c), 16), bits, offset );
	}

	public static void convertToBits(int value, boolean[] bits, int offset) {

		bits[offset+3] = value % 2 > 0;
		bits[offset+2] = value % 4 > 1;
		bits[offset+1] = value % 8 > 3;
		bits[offset  ] = value % 16 > 7;
	}
}
