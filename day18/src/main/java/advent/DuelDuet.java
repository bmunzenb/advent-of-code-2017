package advent;

import java.util.List;
import java.util.Queue;

public class DuelDuet extends Duet {

	private final Queue<Long> sendQueue;
	private final Queue<Long> recvQueue;

	private int sent = 0;

	public DuelDuet(List<Instruction> instructions, Queue<Long> sendQueue, Queue<Long> recvQueue, long programId) {
		super(instructions);

		this.sendQueue = sendQueue;
		this.recvQueue = recvQueue;

		registers.put("p", programId);
	}

	@Override protected void snd(String x) {

		sendQueue.add(value(x));
		sent++;
		pc++;
	}

	@Override protected boolean rcv(String x) {

		Long value = recvQueue.poll();
		if (value != null) {
			registers.put(x, value.longValue());
			pc++;
			return false;
		}
		else {
			return true;
		}
	}

	@Override public String toString() {

		return "recvQueue=" + recvQueue + ", registers=" + registers;
	}

	public static int duel(DuelDuet d0, DuelDuet d1) {

		boolean dead0 = false;
		boolean dead1 = false;

		while (!dead0 || !dead1) {

			dead0 = d0.executeNextInstruction();
			dead1 = d1.executeNextInstruction();
		}

		return d1.sent;
	}
}
