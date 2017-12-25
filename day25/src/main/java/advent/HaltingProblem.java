package advent;

import java.util.HashSet;
import java.util.Set;

public class HaltingProblem {

	public interface State {

		State tick(TuringMachine machine);
	}

	public static class TuringMachine {

		private final Set<Integer> tape = new HashSet<>();
		private int cursor = 0;

		public boolean isSet() {
			return tape.contains(cursor);
		}

		public void set() {
			tape.add(cursor);
		}

		public void clear() {
			tape.remove(cursor);
		}

		public void increment() {
			cursor++;
		}

		public void decrement() {
			cursor--;
		}

		public int checksum() {
			return tape.size();
		}

		public TuringMachine execute(State state, long iterations) {

			for (long i = 0; i < iterations; i++) {
				state = state.tick(this);
			}

			return this;
		}
	}
}
