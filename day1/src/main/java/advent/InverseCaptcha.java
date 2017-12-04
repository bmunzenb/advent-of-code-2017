package advent;

public class InverseCaptcha {

	public static String solvePart1(String input) {

		return solve(input, 1);
	}

	public static String solvePart2(String input) {

		return solve(input, input.length() / 2);
	}

	private static String solve(String input, int jump) {

		int sum = 0;

		for (int i = 0; i < input.length(); i++) {

			int digit = digitAt(i, input);
			int compare = digitAt(i+jump, input);

			if (digit == compare) {
				sum += digit;
			}
		}

		return Integer.toString(sum);
	}

	private static int digitAt(int index, String input) {

		if (index > input.length()-1) {
			index = index % input.length();
		}

		return Integer.parseInt(input.substring(index, index+1));
	}
}
