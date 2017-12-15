package advent;

public class Generator {

	private final long factor;
	private final long divisor = 2147483647;

	private long multiple = 1;
	private long previous;

	public Generator(long factor) {
		this.factor = factor;
	}

	public Generator start(long start) {
		this.previous = start;
		return this;
	}

	public Generator multiple(long multiple) {
		this.multiple = multiple;
		return this;
	}

	public long next() {

		do {
			previous = (previous * factor) % divisor;

		} while (previous % multiple != 0);

		return previous;
	}

	private static final long filter16Bits = 0xffff;

	public static boolean compareLowest16Bits(long a, long b) {

		long a16 = a & filter16Bits;
		long b16 = b & filter16Bits;

		return a16 == b16;
	}

	public static long compareLowest16Bits(Generator genA, Generator genB, long iterations) {

		long count = 0;

		while (iterations > 0) {

			boolean result = compareLowest16Bits(genA.next(), genB.next());
			if (result) {
				count++;
			}

			iterations--;
		}

		return count;
	}
}
