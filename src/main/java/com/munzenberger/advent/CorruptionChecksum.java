package com.munzenberger.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CorruptionChecksum {

	public static List<int[]> decode(Path source) throws IOException {

		return Files.lines(source).map(s -> decode(s)).collect(Collectors.toList());
	}

	public static int[] decode(String string) {

		StringTokenizer values = new StringTokenizer(string);

		int[] data = new int[values.countTokens()];
		int i = 0;

		while (values.hasMoreTokens()) {
			data[i++] = Integer.parseInt(values.nextToken());
		}

		return data;
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
