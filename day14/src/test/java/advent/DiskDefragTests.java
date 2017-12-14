package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DiskDefragTests {

	@Test
	public void solve() {

		boolean[][] grid = DiskDefrag.generateGrid("flqrgnkx");

		assertEquals(8108, DiskDefrag.countTotalUsedSquares(grid));
		assertEquals(1242, DiskDefrag.countRegions(grid));
	}
}
