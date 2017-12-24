package advent;

public class Deconstructed {

	public static void main(String[] args) {
		System.out.println(execute());
	}

	public static long execute() {

		long a = 1;
		long b = 0;
		long c = 0;
		long d = 0;
//		long e = 0;
		long f = 0;
		long g = 0;
		long h = 0;

		b = 84;
		c = b;

		if (a != 0) {

			b *= 100;
			b -= -100000;
			c = b;
			c -= -17000;
		}

		do {

			f = 1;

			for (d = 2; d < b; d++) {
//				for (e = 2; e < b; e++) {
//					if (d * e == b) {
					if (b % d == 0) {
						f = 0;
						break;
					}
//				}
			}

			if (f == 0) {
				h -= -1;
			}

			g = b;
			g -= c;

			if (g == 0) {
				return h;
			}

			b -= -17;

		} while (true);
	}
}
