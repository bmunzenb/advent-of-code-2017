package advent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Spinlock {

	private List<Integer> buffer = new LinkedList<>();
	private int currentPosition;
	private final int step;

	public Spinlock(int step) {
		this.step = step;
		buffer.add(0);
		currentPosition = 0;
	}

	public void step() {

		// step forward
		currentPosition = (currentPosition + step) % buffer.size();

		// new current position
		currentPosition = currentPosition + 1;

		// insert new value
		buffer.add(currentPosition, buffer.size());
	}

	public List<Integer> step(int times) {

		for (int i = 0; i < times; i++) {
			step();
		}

		return Collections.unmodifiableList(buffer);
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public static int computeIndex1(int step, int iterations) {

		int position = 1;
		int index1 = -1;

		for (int i = 1; i < iterations; i++) {

			// step forward
			position = (position + step) % i;

			// new current position
			position++;

			// only care about index 1
			if (position == 1) {
				index1 = i;
			}
		}

		return index1;
	}
}
