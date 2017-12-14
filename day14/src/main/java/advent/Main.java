package advent;

import java.net.URISyntaxException;

public class Main {

	public static void main(String[] args) throws URISyntaxException {

		final boolean[][] grid = DiskDefrag.generateGrid("jxqlasbh");

		System.out.println(DiskDefrag.countTotalUsedSquares(grid));
		System.out.println(DiskDefrag.countRegions(grid));
	}
}
