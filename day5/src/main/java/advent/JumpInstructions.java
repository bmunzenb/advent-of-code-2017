package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JumpInstructions {

	public static int[] parse(Path source) throws IOException {

		return Files.lines(source).mapToInt(Integer::parseInt).toArray();
	}

	public static int steps(int[] input) {

		int index = 0;
		int steps = 0;

		while (input.length > index && index >= 0) {

			steps++;

			int jump = input[index];

			input[index]++;

			index += jump;
		}

		return steps;
	}

	public static int stepsWithComplication(int[] input) {

		int index = 0;
		int steps = 0;

		while (input.length > index && index >= 0) {

			steps++;

			int jump = input[index];

			if (jump >= 3) {
				input[index]--;
			}
			else {
				input[index]++;
			}

			index += jump;
		}

		return steps;
	}
}
