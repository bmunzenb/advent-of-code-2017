package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public class JumpInstructions {

	public static int[] parse(Path source) throws IOException {

		return Files.lines(source).mapToInt(Integer::parseInt).toArray();
	}

	public static int steps(int[] input) {

		return steps(input, i -> 1);
	}

	public static int stepsWithComplication(int[] input) {

		return steps(input, i -> i >= 3 ? -1 : 1);
	}

	private static int steps(int[] input, Function<Integer, Integer> func) {

		int index = 0;
		int steps = 0;

		while (input.length > index && index >= 0) {

			steps++;

			int jump = input[index];

			input[index] += func.apply(jump);

			index += jump;
		}

		return steps;
	}
}
