package advent;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		Spinlock spinlock = new Spinlock(366);

		List<Integer> result = spinlock.step(2017);
		int position = spinlock.getCurrentPosition();

		position = (position + 1) % result.size();

		System.out.println(result.get(position));

		System.out.println(Spinlock.computeIndex1(366, 50000000));
	}
}
