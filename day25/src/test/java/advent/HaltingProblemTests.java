package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import advent.HaltingProblem.State;
import advent.HaltingProblem.TuringMachine;

public class HaltingProblemTests {

	class StateB implements State {

		@Override
		public State tick(TuringMachine machine) {

			if (machine.isSet()) {
				// If the current value is 1:
				//	- Write the value 1.
				//	- Move one slot to the right.
				//	- Continue with state A.

				machine.set();
				machine.increment();
			}
			else {
				//  If the current value is 0:
				//	- Write the value 1.
				//	- Move one slot to the left.
				//	- Continue with state A.

				machine.set();
				machine.decrement();
			}

			return new StateA();
		}
	}

	class StateA implements State {

		@Override public State tick(TuringMachine machine) {

			if (machine.isSet()) {
				//  If the current value is 1:
				//    - Write the value 0.
				//    - Move one slot to the left.
				//    - Continue with state B.

				machine.clear();
				machine.decrement();
			}
			else {
				//  If the current value is 0:
				//    - Write the value 1.
				//    - Move one slot to the right.
				//    - Continue with state B.

				machine.set();
				machine.increment();
			}

			return new StateB();
		}
	}

	@Test
	public void test() {

		TuringMachine machine = new TuringMachine();
		State state = new StateA();

		assertEquals(3, machine.execute(state, 6).checksum());
	}
}
