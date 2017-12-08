package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Machine {

	Map<String, Integer> registers = new HashMap<>();

	int highestEver = Integer.MIN_VALUE;

	public static class Instruction {

		String register;
		boolean operation;  // true = increment, false = decrement
		int value;
		String compareRegister;
		String comparator;
		int compareValue;
	}

	public int getRegisterValue(String register) {
		Integer value = registers.get(register);
		return value == null ? 0 : value.intValue();
	}

	private int operate(String register, int value, boolean operation) {
		int i = getRegisterValue(register);
		i = operation ? i + value : i - value;
		registers.put(register, i);
		return i;
	}

	public void executeInstructions(Path input) throws IOException {

		Files.lines(input)
			.map(Machine::parseInstruction)
			.forEach(this::execute);
	}

	public int findLargestValue() {

		return registers.values().stream()
				.mapToInt(i -> i.intValue())
				.max()
				.getAsInt();
	}

	public void execute(Instruction instruction) {

		int i = getRegisterValue(instruction.compareRegister);

		boolean satisfied = false;

		if ("<".equals(instruction.comparator)) {
			satisfied = i < instruction.compareValue;
		}
		else if ("<=".equals(instruction.comparator)) {
			satisfied = i <= instruction.compareValue;
		}
		else if (">".equals(instruction.comparator)) {
			satisfied = i > instruction.compareValue;
		}
		else if (">=".equals(instruction.comparator)) {
			satisfied = i >= instruction.compareValue;
		}
		else if ("==".equals(instruction.comparator)) {
			satisfied = i == instruction.compareValue;
		}
		else if ("!=".equals(instruction.comparator)) {
			satisfied = i != instruction.compareValue;
		}
		else {
			throw new IllegalArgumentException("Unrecognized comparator: " + instruction.comparator);
		}

		if (satisfied) {
			int value = operate(instruction.register, instruction.value, instruction.operation);
			highestEver = Math.max(highestEver, value);
		}
	}

	public static Instruction parseInstruction(String line) {

		Instruction instruction = new Instruction();

		StringTokenizer tokens = new StringTokenizer(line);

		if (tokens.countTokens() != 7) {
			throw new IllegalArgumentException("Wrong number of tokens in instruction: " + line);
		}

		instruction.register = tokens.nextToken();
		instruction.operation = tokens.nextToken().equalsIgnoreCase("inc"); // true = increment, anything else = decrement
		instruction.value = Integer.parseInt(tokens.nextToken());
		tokens.nextToken(); // "if"
		instruction.compareRegister = tokens.nextToken();
		instruction.comparator = tokens.nextToken();
		instruction.compareValue = Integer.parseInt(tokens.nextToken());

		return instruction;
	}
}
