package advent;

public class Main {

	public static void main(String[] args) {

		final int[] input = new int[] {2, 8, 8, 5, 4, 2, 3, 1, 5, 5, 1, 2, 15, 13, 5, 14};

		System.out.println(MemoryReallocation.countCycles(input).cycles);
		System.out.println(MemoryReallocation.sizeOfLoop(input).cycles);
	}
}
