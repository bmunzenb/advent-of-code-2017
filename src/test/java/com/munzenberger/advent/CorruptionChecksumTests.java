package com.munzenberger.advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CorruptionChecksumTests {

	@Test
	public void parseString() {

		String input =
				"5 1 9 5\n" +
				"7 5 3\n" +
				"2 4 6 8";

		List<int[]> parsed = CorruptionChecksum.parse(input);

		List<int[]> expected = new ArrayList<>();
		expected.add(new int[] {5, 1, 9, 5});
		expected.add(new int[] {7, 5, 3});
		expected.add(new int[] {2, 4, 6, 8});

		assertEquals(expected.size(), parsed.size());

		for (int i = 0; i < expected.size(); i++) {
			assertTrue(Arrays.equals(expected.get(i), parsed.get(i)));
		}
	}

	@Test
	public void solvePart1() {

		List<int[]> input = new ArrayList<>();
		input.add(new int[] {5, 1, 9, 5});
		input.add(new int[] {7, 5, 3});
		input.add(new int[] {2, 4, 6, 8});

		assertEquals(18, CorruptionChecksum.solvePart1(input));
	}

	@Test
	public void solvePart2() {

		List<int[]> input = new ArrayList<>();
		input.add(new int[] {5, 9, 2, 8});
		input.add(new int[] {9, 4, 7, 3});
		input.add(new int[] {3, 8, 6, 5});

		assertEquals(9, CorruptionChecksum.solvePart2(input));
	}
}
