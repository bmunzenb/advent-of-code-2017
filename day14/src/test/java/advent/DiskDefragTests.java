package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DiskDefragTests {

	@Test
	public void convertToBits() {

		assertEquals(8108, DiskDefrag.countTotalUsedSquares("flqrgnkx"));
	}
}
