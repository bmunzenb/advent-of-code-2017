package advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeneratorTests {

	@Test
	public void next() {

		Generator genA = new Generator(16807).start(65);

		assertEquals(1092455L, genA.next());
		assertEquals(1181022009L, genA.next());
		assertEquals(245556042L, genA.next());
		assertEquals(1744312007L, genA.next());
		assertEquals(1352636452L, genA.next());
	}

	@Test
	public void compareLowest16Bits() {

		assertFalse(Generator.compareLowest16Bits(1092455L, 430625591L));
		assertFalse(Generator.compareLowest16Bits(1181022009L, 1233683848L));
		assertTrue(Generator.compareLowest16Bits(245556042L, 1431495498L));
		assertFalse(Generator.compareLowest16Bits(1744312007L, 137874439L));
		assertFalse(Generator.compareLowest16Bits(1352636452L, 285222916L));
	}

	@Test
	public void compareTwoGenerators() {

		Generator genA = new Generator(16807).start(65);
		Generator genB = new Generator(48271).start(8921);

		long fortyMillion = 40000000L;

		assertEquals(588, Generator.compareLowest16Bits(genA, genB, fortyMillion));

		genA.start(65).multiple(4);
		genB.start(8921).multiple(8);

		long fiveMillion = 5000000L;

		assertEquals(309, Generator.compareLowest16Bits(genA, genB, fiveMillion));
	}

	@Test
	public void compareTwoGeneratorsWithMultiples() {

		Generator genA = new Generator(16807).start(65).multiple(4);
		Generator genB = new Generator(48271).start(8921).multiple(8);

		long fiveMillion = 5000000L;

		assertEquals(309, Generator.compareLowest16Bits(genA, genB, fiveMillion));
	}
}
