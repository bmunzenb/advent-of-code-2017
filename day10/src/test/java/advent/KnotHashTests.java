package advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class KnotHashTests {

	@Test
	public void reverse() {

		assertTrue( Arrays.equals(new int[] {2, 1, 0, 3, 4}, KnotHash.reverse(new int[] {0, 1, 2, 3, 4}, 0, 3)) );
		assertTrue( Arrays.equals(new int[] {4, 3, 0, 1, 2}, KnotHash.reverse(new int[] {2, 1, 0, 3, 4}, 3, 4)) );
	}

	@Test
	public void process() {

		assertTrue( Arrays.equals(new int[] {3, 4, 2, 1, 0}, KnotHash.process(new int[] {0,  1, 2, 3, 4}, new int[] {3, 4, 1, 5})));
	}

	@Test
	public void solvePart2() {

		assertEquals("a2582a3a0e66e6e86e3812dcb672a272", KnotHash.generate(""));
		assertEquals("33efeb34ea91902bb2f59c9920caa6cd", KnotHash.generate("AoC 2017"));
		assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", KnotHash.generate("1,2,3"));
		assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", KnotHash.generate("1,2,4"));
	}
}
