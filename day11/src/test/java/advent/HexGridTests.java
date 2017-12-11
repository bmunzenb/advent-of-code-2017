package advent;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class HexGridTests {

	@Test
	public void stepsFromOrigin() {

		assertEquals(3, HexGrid.stepsFromOrigin(Arrays.asList("ne", "ne", "ne")));
		assertEquals(0, HexGrid.stepsFromOrigin(Arrays.asList("ne", "ne", "sw", "sw")));
		assertEquals(2, HexGrid.stepsFromOrigin(Arrays.asList("ne", "ne", "s", "s")));
		assertEquals(3, HexGrid.stepsFromOrigin(Arrays.asList("se", "sw", "se", "sw", "sw")));
	}
}
