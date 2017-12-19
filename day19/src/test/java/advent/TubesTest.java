package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import advent.Tubes.Result;

public class TubesTest {

	@Test
	public void walkPath() {

		String[] input = new String[] {
			"     |          ",
			"     |  +--+    ",
			"     A  |  C    ",
			" F---|----E|--+ ",
			"     |  |  |  D ",
			"     +B-+  +--+ "
		};

		Result r = Tubes.walkPath(input);

		assertEquals("ABCDEF", r.path);
		assertEquals(38, r.steps);
	}
}
