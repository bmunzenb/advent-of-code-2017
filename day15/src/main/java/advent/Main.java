package advent;

import java.math.BigInteger;
import java.net.URISyntaxException;

public class Main {

	public static void main(String[] args) throws URISyntaxException {

		Generator genA = new Generator(new BigInteger("16807")).start(new BigInteger("618"));
		Generator genB = new Generator(new BigInteger("48271")).start(new BigInteger("814"));

		BigInteger fortyMillion = new BigInteger("40000000");

		System.out.println(Generator.compareLowest16Bits(genA	, genB, fortyMillion));

		genA.start(new BigInteger("618")).multiple(new BigInteger("4"));
		genB.start(new BigInteger("814")).multiple(new BigInteger("8"));

		BigInteger fiveMillion = new BigInteger("5000000");

		System.out.println(Generator.compareLowest16Bits(genA	, genB, fiveMillion));
	}
}
