package advent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import advent.Duet.Instruction;

public class DuetTests {

	@Test
	public void test() {

		List<Instruction> instructions = new ArrayList<>();
		instructions.add(Duet.parse("set a 1"));
		instructions.add(Duet.parse("add a 2"));
		instructions.add(Duet.parse("mul a a"));
		instructions.add(Duet.parse("mod a 5"));
		instructions.add(Duet.parse("snd a"));
		instructions.add(Duet.parse("set a 0"));
		instructions.add(Duet.parse("rcv a"));
		instructions.add(Duet.parse("jgz a -1"));
		instructions.add(Duet.parse("set a 1"));
		instructions.add(Duet.parse("jgz a -2"));

		Duet duet = new Duet(instructions);

		duet.executeNextInstruction();
		duet.executeNextInstruction();
		duet.executeNextInstruction();
		duet.executeNextInstruction();

		assertEquals(4, duet.register("a"));

		duet.executeNextInstruction();

		assertEquals(4, duet.lastSound());

		assertEquals(4, duet.executeUntilRecover().lastSound());
	}
}
