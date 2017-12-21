package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FractalArt {

	public static class Pattern {

		public final String pattern;
		public final int size;
		private final Set<String> matching;

		public Pattern(String pattern) {

			this.pattern = pattern;
			this.size = pattern.indexOf('/');
			this.matching = matchingSet(pattern);
		}

		public Pattern(Pattern[][] patterns) {

			this(combine(patterns));
		}

		private static String combine(Pattern[][] patterns) {

			StringBuffer sb = new StringBuffer();

			int length = patterns.length;  // assume square

			int size = patterns[0][0].size;
			int max = length * size;

			for (int y = 0; y < max; y++) {
				for (int x = 0; x < max; x++) {

					// translate into coordinates
					int outerY = y / size;
					int outerX = x / size;
					int innerY = y % size;
					int innerX = x % size;

					String[] split = patterns[outerY][outerX].pattern.split("/");
					char c = split[innerY].charAt(innerX);
					sb.append(c);
				}

				if (y < max-1) {
					sb.append("/");
				}
			}

			return sb.toString();
		}

		public static String rotate(String pattern) {

			StringBuffer sb = new StringBuffer();

			String[] split = pattern.split("/");

			for (int x = 0; x < split[0].length(); x++) {
				for (int y = split.length-1; y >= 0; y--) {
					sb.append(split[y].charAt(x));
				}
				if (x < split[0].length()-1) {
					sb.append("/");
				}
			}

			return sb.toString();
		}

		public static String flipHorizontal(String pattern) {

			StringBuffer sb = new StringBuffer();

			String split[] = pattern.split("/");

			for (int y = 0; y < split.length; y++) {
				for (int x = split.length-1; x >= 0; x--) {
					sb.append(split[y].charAt(x));
				}
				if (y < split.length-1) {
					sb.append("/");
				}
			}

			return sb.toString();

		}

		public static String flipVertical(String pattern) {

			StringBuffer sb = new StringBuffer();

			String split[] = pattern.split("/");

			for (int y = split.length-1; y >= 0; y--) {
				sb.append(split[y]);
				if (y > 0) {
					sb.append("/");
				}
			}

			return sb.toString();
		}

		public Pattern subpattern(int y, int x, int size) {

			StringBuffer sb = new StringBuffer();

			String[] split = pattern.split("/");

			for (int i = y; i < y + size; i++) {
				for (int j = x; j < x + size; j++) {
					sb.append(split[i].charAt(j));
				}
				if (i < y+size-1) {
					sb.append("/");
				}
			}

			return new Pattern(sb.toString());
		}

		public Pattern[][] split() {

			int multiple = size % 2 == 0 ? 2 : 3;
			int length = size / multiple;

			Pattern[][] patterns = new Pattern[length][length];

			for (int y = 0; y < length; y++) {
				for (int x = 0; x < length; x++) {
					patterns[y][x] = subpattern(y * multiple, x * multiple, multiple);
				}
			}

			return patterns;
		}

		public int countPixels() {

			int total = 0;

			for (int i = 0; i < pattern.length(); i++) {
				if (pattern.charAt(i) == '#') {
					total++;
				}
			}

			return total;
		}

		@Override public String toString() {

			StringBuffer sb = new StringBuffer();

			String[] split = pattern.split("/");

			for (int i = 0; i < split.length; i++) {
				sb.append(split[i]).append("\n");
			}

			sb.append("size=" + size).append("\n");

			return sb.toString();
		}

		public boolean matches(Pattern pattern) {

			return matching.contains(pattern.pattern);
		}

		private static Set<String> matchingSet(String pattern) {

			Set<String> matching = new HashSet<>();

			matching.add(pattern);
			matching.add(Pattern.flipHorizontal(pattern));
			matching.add(Pattern.flipVertical(pattern));

			for (int i = 0; i < 3; i++) {
				pattern = Pattern.rotate(pattern);
				matching.add(pattern);
				matching.add(Pattern.flipHorizontal(pattern));
				matching.add(Pattern.flipVertical(pattern));
			}

			return matching;
		}
	}

	public static class Rule {

		private final Pattern from;
		private final Pattern to;

		public Rule(Pattern from, Pattern to) { 

			this.from = from;
			this.to = to;
		}

		@Override public String toString() {

			return from.pattern + " -> " + to.pattern;
		}
	}

	public static Pattern process(Pattern input, List<Rule> rules) {

		Pattern[][] split = input.split();

		for (int i = 0; i < split.length; i++) {
			for (int j = 0; j < split[i].length; j++) {

				final int x = i;
				final int y = j;

				split[x][y] = rules.stream()
						.filter(r -> r.from.matches(split[x][y]))
						.map(r -> r.to)
						.findFirst()
						.get();
			}
		}

		return new Pattern(split);
	}

	public static List<Rule> parseRules(Path input) throws IOException {

		return Files.lines(input)
				.map(FractalArt::parseRule)
				.collect(Collectors.toList());
	}

	private static Rule parseRule(String str) {

		String[] split = str.split(" => ");

		Pattern from = new Pattern(split[0]);
		Pattern to = new Pattern(split[1]);

		return new Rule(from, to);
	}
}
