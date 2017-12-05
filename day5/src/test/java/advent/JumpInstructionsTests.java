package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JumpInstructionsTests {

	@Test
	public void steps() {

		assertEquals(5, JumpInstructions.steps(new int[] { 0, 3, 0, 1, -3 }));
	}

	@Test
	public void stepsWithComplication() {

		assertEquals(10, JumpInstructions.stepsWithComplication(new int[] { 0, 3, 0, 1, -3 }));
	}
}
