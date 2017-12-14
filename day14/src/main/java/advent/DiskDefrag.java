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
