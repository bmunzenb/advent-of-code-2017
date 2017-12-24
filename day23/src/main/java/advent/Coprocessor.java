package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Coprocessor {

	public static class Instruction {

		public String inst;
		public String x;
		public String y;

		@Override public String toString() {
			return inst + " " + x + (y == null ? "" : " " + y);
		}
	}

	protected final Map<String, Long> registers = new HashMap<>();
	private final List<Instruction> instructions;
	protected int pc = 0;

	public Coprocessor(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public long register(String r) {
		return registers.getOrDefault(r, Long.valueOf(0));
	}

	protected long value(String x) {

		try {
			return Long.parseLong(x);
		}
		catch (Exception e) {
			return register(x);
		}
	}

	public void set(String r, String y) {

		registers.put(r, value(y));
		pc++;
	}

	private void sub(String r, String y) {

		long value = register(r);
		registers.put(r, value - value(y));
		pc++;
	}

	private void mul(String r, String y) {

		long value = register(r);
		registers.put(r, value * value(y));
		pc++;
	}

	private void jnz(String x, String y) {

		if (value(x) != 0) {
			long jump = value(y);
			pc += jump;
		}
		else {
			pc++;
		}
	}

	private boolean execute(Instruction inst) {

		boolean recovered = false;

		switch (inst.inst) {

		case "set":
			set(inst.x, inst.y);
			break;

		case "sub":
			sub(inst.x, inst.y);
			break;

		case "mul":
			mul(inst.x, inst.y);
			break;

		case "jnz":
			jnz(inst.x, inst.y);
			break;

		default:
			throw new IllegalArgumentException("Unknown instruction: " + inst.inst);
		}

		return recovered;
	}

	public Coprocessor execute() {

		while (pc < instructions.size()) {
			Instruction i = instructions.get(pc);
			execute(i);
			System.out.println(i + " -> " + registers);
		}

		return this;
	}

	public int executeAndCountInstructions(String instruction) {

		int total = 0;

		while (pc < instructions.size()) {
			Instruction i = instructions.get(pc);
			total += i.inst.equals(instruction) ? 1 : 0;
			execute(i);
		}

		return total;
	}

	public static List<Instruction> parse(Path path) throws IOException {

		return Files.lines(path)
				.map(Coprocessor::parse)
				.collect(Collectors.toList());
	}

	public static Instruction parse(String str) {

		String[] split = str.split(" ");

		Instruction i = new Instruction();
		i.inst = split[0];
		i.x = split[1];

		if (split.length == 3) {
			i.y = split[2];
		}

		return i;
	}
}
