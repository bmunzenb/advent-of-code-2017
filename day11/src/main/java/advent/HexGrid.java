package advent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HexGrid {

	int northSouth = 0;
	int eastWest = 0;

	int maxNorthSouth = 0;
	int maxEastWest = 0;

	public HexGrid takeSteps(List<String> input) {

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

			maxNorthSouth = Math.max(maxNorthSouth, Math.abs(northSouth));
			maxEastWest = Math.max(maxEastWest, Math.abs(eastWest));
		}

		return this;
	}

	public static List<String> parseInput(String input) {

		List<String> list = new ArrayList<>();

		StringTokenizer tokens = new StringTokenizer(input, ",");
		while (tokens.hasMoreTokens()) {
			list.add(tokens.nextToken());
		}

		return list;
	}

	public static int stepsFromOrigin(List<String> input) {

		HexGrid hexGrid = new HexGrid().takeSteps(input);
		return stepsFromOrigin(hexGrid.northSouth, hexGrid.eastWest);
	}

	public static int stepsFromOrigin(int northSouth, int eastWest) {

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
