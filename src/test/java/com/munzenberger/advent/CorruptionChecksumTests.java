package com.munzenberger.advent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CorruptionChecksumTests {

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
