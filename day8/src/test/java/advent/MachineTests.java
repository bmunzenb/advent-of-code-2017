package advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import advent.Machine.Instruction;

public class MachineTests {

	@Test
	public void instructions() {

		Machine machine = new Machine();

		Instruction i1 = Machine.parseInstruction("b inc 5 if a > 1");
		assertEquals("b", i1.register);
		assertTrue(i1.operation);
		assertEquals(5, i1.value);
		assertEquals("a", i1.compareRegister);
		assertEquals(">", i1.comparator);
		assertEquals(1, i1.compareValue);

		machine.execute(i1);
		assertEquals(0, machine.getRegisterValue("b"));

		Instruction i2 = Machine.parseInstruction("a inc 1 if b < 5");
		assertEquals("a", i2.register);
		assertTrue(i2.operation);
		assertEquals(1, i2.value);
		assertEquals("b", i2.compareRegister);
		assertEquals("<", i2.comparator);
		assertEquals(5, i2.compareValue);

		machine.execute(i2);
		assertEquals(1, machine.getRegisterValue("a"));

		Instruction i3 = Machine.parseInstruction("c dec -10 if a >= 1");
		machine.execute(i3);
		assertEquals(10, machine.getRegisterValue("c"));

		Instruction i4 = Machine.parseInstruction("c inc -20 if c == 10");
		machine.execute(i4);
		assertEquals(-10, machine.getRegisterValue("c"));

		assertEquals(1, machine.findLargestValue());
		assertEquals(10, machine.highestEver);
	}
}
