package advent;

import advent.HaltingProblem.State;
import advent.HaltingProblem.TuringMachine;

public class Main {

	static class StateA implements State {
		@Override public State tick(TuringMachine machine) {
			if (machine.isSet()) {
				machine.set();
				machine.decrement();
				return new StateE();
			}
			else {
				machine.set();
				machine.increment();
				return new StateB();
			}
		}
	}

	static class StateB implements State {
		@Override public State tick(TuringMachine machine) {
			if (machine.isSet()) {
				machine.set();
				machine.increment();
				return new StateF();
			}
			else {
				machine.set();
				machine.increment();
				return new StateC();
			}
		}
	}

	static class StateC implements State {
		@Override public State tick(TuringMachine machine) {
			if (machine.isSet()) {
				machine.clear();
				machine.increment();
				return new StateB();
			}
			else {
				machine.set();
				machine.decrement();
				return new StateD();
			}
		}
	}

	static class StateD implements State {
		@Override public State tick(TuringMachine machine) {
			if (machine.isSet()) {
				machine.clear();
				machine.decrement();
				return new StateC();
			}
			else {
				machine.set();
				machine.increment();
				return new StateE();
			}
		}
	}

	static class StateE implements State {
		@Override public State tick(TuringMachine machine) {
			if (machine.isSet()) {
				machine.clear();
				machine.increment();
				return new StateD();
			}
			else {
				machine.set();
				machine.decrement();
				return new StateA();
			}
		}
	}

	static class StateF implements State {
		@Override public State tick(TuringMachine machine) {
			if (machine.isSet()) {
				machine.set();
				machine.increment();
				return new StateC();
			}
			else {
				machine.set();
				machine.increment();
				return new StateA();
			}
		}
	}

	public static void main(String[] args) {

		TuringMachine machine = new TuringMachine();
		State state = new StateA();

		System.out.println(machine.execute(state, 12459852).checksum());
	}
}
