package advent;

public class PermutationPromenade {

	public static String spin(String str, int spin) {

		int index = str.length() - spin;
		return str.substring(index) + str.substring(0, index);
	}

	public static String exchange(String str, int a, int b) {

		StringBuffer sb = new StringBuffer(str);

		char ac = sb.charAt(a);
		char bc = sb.charAt(b);

		sb.setCharAt(a, bc);
		sb.setCharAt(b, ac);

		return sb.toString();
	}

	public static String swap(String str, char a, char b) {

		StringBuffer sb = new StringBuffer(str);

		int indexA = sb.indexOf(Character.toString(a));
		int indexB = sb.indexOf(Character.toString(b));

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

	public static String[] parseMoves(String input) {

		return input.split(",");
	}
}
