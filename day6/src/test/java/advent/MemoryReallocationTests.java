package advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MemoryReallocationTests {

	@Test
	public void reallocate() {

		assertTrue(Arrays.equals(new int[] {2, 4, 1, 2}, MemoryReallocation.reallocate(new int[] {0, 2, 7, 0})));
		assertTrue(Arrays.equals(new int[] {3, 1, 2, 3}, MemoryReallocation.reallocate(new int[] {2, 4, 1, 2})));
		assertTrue(Arrays.equals(new int[] {0, 2, 3, 4}, MemoryReallocation.reallocate(new int[] {3, 1, 2, 3})));
		assertTrue(Arrays.equals(new int[] {1, 3, 4, 1}, MemoryReallocation.reallocate(new int[] {0, 2, 3, 4})));
		assertTrue(Arrays.equals(new int[] {2, 4, 1, 2}, MemoryReallocation.reallocate(new int[] {1, 3, 4, 1})));
	}

	@Test
	public void countCycles() {

		assertEquals(5, MemoryReallocation.countCycles(new int[] {0, 2, 7, 0}).cycles);
	}

	@Test
	public void sizeOfLoop() {

		assertEquals(4, MemoryReallocation.sizeOfLoop(new int[] {0, 2, 7, 0}).cycles);
	}
}
