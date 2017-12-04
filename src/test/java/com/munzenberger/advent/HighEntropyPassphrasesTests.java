package com.munzenberger.advent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HighEntropyPassphrasesTests {

	@Test
	public void isValid() {

		assertTrue(HighEntropyPassphrases.isValid("aa bb cc dd ee"));
		assertFalse(HighEntropyPassphrases.isValid("aa bb cc dd aa"));
		assertTrue(HighEntropyPassphrases.isValid("aa bb cc dd aaa"));
	}
}
