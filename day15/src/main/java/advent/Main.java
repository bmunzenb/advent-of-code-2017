package advent;

public class Main {

	public static void main(String[] args) {

		Generator genA = new Generator(16807).start(618);
		Generator genB = new Generator(48271).start(814);

		System.out.println(Generator.compareLowest16Bits(genA	, genB, 40000000L));

		genA.start(618).multiple(4);
		genB.start(814).multiple(8);

		System.out.println(Generator.compareLowest16Bits(genA	, genB, 5000000L));
	}
}
