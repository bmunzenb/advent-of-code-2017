package advent;

import java.math.BigInteger;

public class Generator {

	private final BigInteger factor;
	private final BigInteger divisor = new BigInteger("2147483647");

	private BigInteger previous;

	public Generator(BigInteger factor) {
		this.factor = factor;
	}

	public Generator start(BigInteger start) {
		this.previous = start;
		return this;
	}

	public BigInteger next() {

		previous = previous.multiply(factor).mod(divisor);
		return previous;
	}

	private static final BigInteger filter16Bits = new BigInteger("ffff", 16);

	public static boolean compareLowest16Bits(BigInteger a, BigInteger b) {

		BigInteger a16 = a.and(filter16Bits);
		BigInteger b16 = b.and(filter16Bits);

		return a16.equals(b16);
	}

	public static BigInteger compareLowest16Bits(Generator genA, Generator genB, BigInteger iterations) {

		BigInteger count = BigInteger.ZERO;

		while (iterations.compareTo(BigInteger.ZERO) > 0) {

			boolean result = compareLowest16Bits(genA.next(), genB.next());
			if (result) {
				count = count.add(BigInteger.ONE);
			}

			iterations = iterations.subtract(BigInteger.ONE);
		}

		return count;
	}
}
