package advent;

public class Main {

	public static void main(String[] args) {

		int[] input1 = new int[] {70,66,255,2,48,0,54,48,80,141,244,254,160,108,1,41};

		System.out.println(KnotHash.solvePart1(input1));

		String input2 = "70,66,255,2,48,0,54,48,80,141,244,254,160,108,1,41";

		System.out.println(KnotHash.generate(input2));
	}
}
