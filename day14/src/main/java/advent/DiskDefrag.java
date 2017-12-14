package advent;

public class DiskDefrag {

	public static int countTotalUsedSquares(String input) {

		int total = 0;

		for (int i = 0; i < 128; i++) {
			String row = String.format("%s-%d", input, i);
			total += countUsedSquaresForRow(row);
		}

		return total;
	}

	public static int countUsedSquaresForRow(String input) {

		int total = 0;

		String output = KnotHash.generate(input);

		for (int i = 0; i < output.length(); i++) {
			total += countBits(output.charAt(i));
		}

		return total;
	}

	private static int countBits(char c) {

		int total = 0;

		boolean[] bits = convertToBits(c);

		for (boolean b : bits) {
			total += b ? 1 : 0;
		}

		return total;
	}

	public static boolean[] convertToBits(char c) {

		return convertToBits( Integer.parseInt(Character.toString(c), 16) );
	}

	public static boolean[] convertToBits(int value) {

		boolean[] bits = new boolean[4];

		bits[3] = value % 2 > 0;
		bits[2] = value % 4 > 1;
		bits[1] = value % 8 > 3;
		bits[0] = value % 16 > 7;

		return bits;
	}
}
