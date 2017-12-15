package advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class GeneratorTests {

	@Test
	public void next() {

		Generator genA = new Generator(new BigInteger("16807")).start(new BigInteger("65"));

		assertEquals(new BigInteger("1092455"), genA.next());
		assertEquals(new BigInteger("1181022009"), genA.next());
		assertEquals(new BigInteger("245556042"), genA.next());
		assertEquals(new BigInteger("1744312007"), genA.next());
		assertEquals(new BigInteger("1352636452"), genA.next());
	}

	@Test
	public void compareLowest16Bits() {

		assertFalse(Generator.compareLowest16Bits(new BigInteger("1092455"), new BigInteger("430625591")));
		assertFalse(Generator.compareLowest16Bits(new BigInteger("1181022009"), new BigInteger("1233683848")));
		assertTrue(Generator.compareLowest16Bits(new BigInteger("245556042"), new BigInteger("1431495498")));
		assertFalse(Generator.compareLowest16Bits(new BigInteger("1744312007"), new BigInteger("137874439")));
		assertFalse(Generator.compareLowest16Bits(new BigInteger("1352636452"), new BigInteger("285222916")));
	}

	@Test
	public void compareTwoGenerators() {

		Generator genA = new Generator(new BigInteger("16807")).start(new BigInteger("65"));
		Generator genB = new Generator(new BigInteger("48271")).start(new BigInteger("8921"));

		BigInteger fortyMillion = new BigInteger("40000000");

		assertEquals(new BigInteger("588"), Generator.compareLowest16Bits(genA, genB, fortyMillion));
	}
}
