package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Duet {

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

	private long lastSound = 0;
	protected int pc = 0;

	public Duet(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public long lastSound() {
		return lastSound;
	}

	public long register(String r) {
		Long i = registers.get(r);
		return i == null ? 0 : i.longValue();
	}

	protected long value(String x) {

		try {
			return Integer.parseInt(x);
		}
		catch (Exception e) {
			return register(x);
		}
	}

	protected void snd(String x) {

		lastSound = value(x);
		pc++;
	}

	private void set(String r, String y) {

		registers.put(r, value(y));
		pc++;
	}

	private void add(String r, String y) {

		long value = register(r);
		registers.put(r, value + value(y));
		pc++;
	}

	private void mul(String r, String y) {

		long value = register(r);
		registers.put(r, value * value(y));
		pc++;
	}

	private void mod(String r, String y) {

		long value = register(r);
		registers.put(r, value % value(y));
		pc++;
	}

	protected boolean rcv(String x) {

		pc++;
		return value(x) != 0;
	}

	private void jgz(String x, String y) {

		if (value(x) > 0) {
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
		case "snd":
			snd(inst.x);
			break;

		case "set":
			set(inst.x, inst.y);
			break;

		case "add":
			add(inst.x, inst.y);
			break;

		case "mul":
			mul(inst.x, inst.y);
			break;

		case "mod":
			mod(inst.x, inst.y);
			break;

		case "rcv":
			recovered = rcv(inst.x);
			break;

		case "jgz":
			jgz(inst.x, inst.y);
			break;

		default:
			throw new IllegalArgumentException("Unknown instruction: " + inst.inst);
		}

		return recovered;
	}

	public boolean executeNextInstruction() {

		Instruction i = instructions.get(pc);
		return execute(i);
	}

	public Duet executeUntilRecover() {

		while (executeNextInstruction() == false);
		return this;
	}

	public static List<Instruction> parse(Path path) throws IOException {

		return Files.lines(path)
				.map(Duet::parse)
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
