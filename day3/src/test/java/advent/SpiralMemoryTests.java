package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpiralMemoryTests {

	@Test
	public void solvePart1() {

		assertEquals(0, SpiralMemory.solvePart1(1));
		assertEquals(3, SpiralMemory.solvePart1(12));
		assertEquals(2, SpiralMemory.solvePart1(23));
		assertEquals(31, SpiralMemory.solvePart1(1024));
	}
}
