package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpinlockTests {

	@Test
	public void computeIndex1() {

		assertEquals(9, Spinlock.computeIndex1(3, 10));
	}
}
