package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryReallocation {

	public static class Result {

		public final int cycles;
		public final int[] result;

		private Result(int cycles, int[] result) {
			this.cycles = cycles;
			this.result = result;
		}
	}

	public static Result sizeOfLoop(int[] input) {

		Result r = countCycles(input);
		return countCycles(r.result);
	}

	public static Result countCycles(int[] input) {

		List<int[]> history = new ArrayList<>();

		int[] result = Arrays.copyOf(input, input.length);
		int cycles = 0;

		do {
			cycles++;
			history.add(result);
			result = reallocate(result);
		}
		while (!contains(history, result));

		return new Result(cycles, result);
	}

	public static int[] reallocate(int[] input) {

		int[] result = Arrays.copyOf(input, input.length);
		int maxIndex = findMaxIndex(input);

		int value = input[maxIndex];

		result[maxIndex] = 0;

		int i = maxIndex;

		while (value > 0) {
			i = (i + 1) % input.length;
			result[i]++;
			value--;
		}

		return result;
	}

	public static int findMaxIndex(int[] input) {
		int index = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] > input[index]) {
				index = i;
			}
		}
		return index;
	}

	public static boolean contains(List<int[]> history, int[] result) {

		for (int[] a : history) {
			if (Arrays.equals(a, result)) {
				return true;
			}
		}

		return false;
	}
}
