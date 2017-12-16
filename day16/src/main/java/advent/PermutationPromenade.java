package advent;

import java.util.ArrayList;
import java.util.List;

public class PermutationPromenade {

	public static String spin(String str, int spin) {

		int index = str.length() - spin;
		return str.substring(index) + str.substring(0, index);
	}

	public static String exchange(String str, int a, int b) {

		StringBuffer sb = new StringBuffer(str);

		char ac = str.charAt(a);
		char bc = str.charAt(b);

		sb.setCharAt(a, bc);
		sb.setCharAt(b, ac);

		return sb.toString();
	}

	public static String swap(String str, char a, char b) {

		StringBuffer sb = new StringBuffer(str);

		int indexA = str.indexOf(a);
		int indexB = str.indexOf(b);

		sb.setCharAt(indexA, b);
		sb.setCharAt(indexB, a);

		return sb.toString();
	}

	public static String dance(String str, String move) {

		char m = move.charAt(0);
		String[] opts = move.substring(1).split("/");

		switch (m) {
		case 's': {
			int spin = Integer.parseInt(opts[0]);
			return spin(str, spin);
		}

		case 'x': {
			int a = Integer.parseInt(opts[0]);
			int b = Integer.parseInt(opts[1]);
			return exchange(str, a, b);
		}

		case 'p': {
			char a = opts[0].charAt(0);
			char b = opts[1].charAt(0);
			return swap(str, a, b);
		}

		default:
			throw new IllegalArgumentException("Unknown move: " + m);
		}
	}

	public static String dance(String str, String[] moves) {

		for (String m : moves) {
			str = dance(str, m);
		}

		return str;
	}

	public static int findCycle(String str, String[] moves) {

		List<String> results = new ArrayList<>();

		while (!results.contains(str)) {

			//System.out.println(results.size() + ": " + str);

			results.add(str);
			str = dance(str, moves);
		}

		int index = results.indexOf(str);

		//System.out.println("found match: " + str + " at index " + index);

		int cycle = results.size() - index;

		//System.out.println("cycle = " + cycle);

		return cycle;
	}

	public static String[] parseMoves(String input) {

		return input.split(",");
	}

	public static String danceALot(String input, String[] moves, int iterations) {

		for (int i = 0; i < iterations; i++) {
			input = dance(input, moves);
		}

		return input;
	}
}
