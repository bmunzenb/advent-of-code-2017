package advent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HexGrid {

	public static List<String> parseInput(String input) {

		List<String> list = new ArrayList<>();

		StringTokenizer tokens = new StringTokenizer(input, ",");
		while (tokens.hasMoreTokens()) {
			list.add(tokens.nextToken());
		}

		return list;
	}

	public static int stepsFromOrigin(List<String> input) {

		int northSouth = 0;
		int eastWest = 0;

		for (String d : input) {

			switch (d) {
			case "nw":
				northSouth++;
				eastWest--;
				break;

			case "n":
				northSouth += 2;
				break;

			case "ne":
				northSouth++;
				eastWest++;
				break;

			case "se":
				northSouth--;
				eastWest++;
				break;

			case "s":
				northSouth -= 2;
				break;

			case "sw":
				northSouth--;
				eastWest--;
				break;
			}
		}

		northSouth = Math.abs(northSouth);
		eastWest = Math.abs(eastWest);

		int steps = 0;

		while (Math.min(northSouth, eastWest) > 0) {
			steps++;
			northSouth--;
			eastWest--;
		}

		return steps + (northSouth / 2) + eastWest;
	}
}
