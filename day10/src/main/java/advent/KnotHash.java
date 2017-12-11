package advent;

import java.nio.charset.Charset;
import java.util.Arrays;

public class KnotHash {

	private static int[] defaultList() {

		int[] list = new int[256];

		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}

		return list;
	}

	public static int solvePart1(int[] input) {

		int[] list = defaultList();

		list = process(list, input);
		return list[0] * list[1];
	}

	public static String solvePart2(String input) {

		int[] list = defaultList();

		byte[] bytes = input.getBytes(Charset.forName("ASCII"));
		int[] lengths = new int[bytes.length + 5];

		for (int i = 0; i < bytes.length; i++) {
			lengths[i] = bytes[i];
		}

		lengths[bytes.length+0] = 17;
		lengths[bytes.length+1] = 31;
		lengths[bytes.length+2] = 73;
		lengths[bytes.length+3] = 47;
		lengths[bytes.length+4] = 23;

		list = process(list, lengths, 64);

		int[] dense = denseHash(list);

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < dense.length; i++) {
			sb.append(String.format("%02x", dense[i]));
		}

		return sb.toString();
	}

	public static int[] process(int[] list, int[] input) {

		return process(list, input, 1);
	}

	public static int[] process(int[] list, int[] input, int iterations) {

		int index = 0;
		int skip = 0;

		for (int a = 0; a < iterations; a++) {

			for (int i = 0; i < input.length; i++) {

				int length = input[i];

				list = reverse(list, index, length);
				index += length + skip++;
			}
		}

		return list;
	}

	public static int[] reverse(int[] list, int index, int length) {

		int[] result = Arrays.copyOf(list, list.length);

		int end = index + length - 1;

		while (index < end) {
			swap(result, index, end);
			index++;
			end--;
		}

		return result;
	}

	private static void swap(int[] list, int left, int right) {

		left = left % list.length;
		right = right % list.length;

		int tmp = list[left];
		list[left] = list[right];
		list[right] = tmp;
	}

	public static int[] denseHash(int[] input) {

		int[] dense = new int[input.length / 16];

		int block = 0;

		while (block < dense.length) {

			int indexOffset = 16 * block;

			dense[block] = input[indexOffset];

			for (int i = 1; i < 16; i++) {
				dense[block] ^= input[indexOffset + i];
			}

			block++;
		}

		return dense;
	}
}
