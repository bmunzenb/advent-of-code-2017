package advent;

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

	@Test
	public void isValidWithoutAnagrams() {

		assertTrue(HighEntropyPassphrases.isValidWithoutAnagrams("abcde fghij"));
		assertFalse(HighEntropyPassphrases.isValidWithoutAnagrams("abcde xyz ecdab"));
		assertTrue(HighEntropyPassphrases.isValidWithoutAnagrams("a ab abc abd abf abj"));
		assertTrue(HighEntropyPassphrases.isValidWithoutAnagrams("iiii oiii ooii oooi oooo"));
		assertFalse(HighEntropyPassphrases.isValidWithoutAnagrams("oiii ioii iioi iiio"));
	}
}
