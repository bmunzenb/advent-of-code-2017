package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PermutationPromenadeTests {

	@Test
	public void spin() {

		assertEquals("cdeab", PermutationPromenade.spin("abcde", 3));
	}

	@Test
	public void exchange() {

		assertEquals("eabdc", PermutationPromenade.exchange("eabcd", 3, 4));
	}

	@Test
	public void swap() {

		assertEquals("baedc", PermutationPromenade.swap("eabdc", 'e', 'b'));
	}

	@Test
	public void dance() {

		String[] moves = new String[] { "s1", "x3/4", "pe/b" };

		assertEquals("baedc", PermutationPromenade.dance("abcde", moves));
	}
}
