package com.munzenberger.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CorruptionChecksum {

	public static List<int[]> parse(String input) {

		List<int[]> parsed = new ArrayList<>();

		StringTokenizer rows = new StringTokenizer(input, "\r\n");
		while (rows.hasMoreTokens()) {

			StringTokenizer cols = new StringTokenizer(rows.nextToken());

			int[] data = new int[cols.countTokens()];
			int i = 0;

			while (cols.hasMoreTokens()) {
				data[i++] = Integer.parseInt(cols.nextToken());
			}

			parsed.add(data);
		}

		return parsed;
	}

	public static int solvePart1(List<int[]> input) {

		int checksum = 0;

		for (int row = 0; row < input.size(); row++) {

			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;

			for (int col = 0; col < input.get(row).length; col++) {

				Integer value = input.get(row)[col];

				min = Integer.min(min, value.intValue());
				max = Integer.max(max, value.intValue());
			}

			checksum += max - min;
		}

		return checksum;
	}

	public static int solvePart2(List<int[]> input) {

		int checksum = 0;

		for (int row = 0; row < input.size(); row++) {

			for (int i = 0; i < input.get(row).length; i++) {

				int valA = input.get(row)[i];

				for (int j = 0; j < input.get(row).length; j++) {

					int valB = input.get(row)[j];

					if (valA % valB == 0) {

						int result = valA / valB;

						if (result > 1) {
							checksum += result;
							break;
						}
					}
				}
			}
		}

		return checksum;
	}
}
