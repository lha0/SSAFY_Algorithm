import java.io.*;
import java.util.*;

public class Main {
	static int total = 0;
	static int[][] board;
	static List<int[]> blank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		blank = new ArrayList<int[]>();
		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();

		run(0, 0, bw);
	}

	private static void run(int x, int y, BufferedWriter bw) throws IOException {
//		System.out.println(x + " " + y);

		if (x == 8 && y == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					bw.write(board[i][j] + " ");
				}
				bw.write("\n");
			}

			bw.flush();
			bw.close();

			System.exit(0);
		}

		if (y == 9) {
			run(x + 1, 0, bw);
			return;
		}

		if (board[x][y] != 0) {
			run(x, y + 1, bw);
			return;
		}

//		 print();

		// 숫자 포함되어 있는지 확인
		boolean[] nums = new boolean[10];

		// 가로 확인
		checkRow(x, y, nums);

		// 세로 확인
		checkCol(x, y, nums);

		// 같은 칸 확인
		checkBox(x, y, nums);

		// nums 값이 false인 애들 하나씩 넣고 다음 칸으로
		for (int i = 1; i < nums.length; i++) {
			if (!nums[i]) {
				board[x][y] = i;
				run(x, y + 1, bw);
				board[x][y] = 0;
			}
		}

	}

	private static void print() {
		for (int i = 0; i < 9; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}

	private static void checkRow(int x, int y, boolean[] nums) {
		for (int i = 0; i < 9; i++) {
			if (nums[board[x][i]])
				continue;
			nums[board[x][i]] = true;
		}

	}

	private static void checkCol(int x, int y, boolean[] nums) {
		for (int i = 0; i < 9; i++) {
			if (nums[board[i][y]])
				continue;
			nums[board[i][y]] = true;
		}

	}

	private static void checkBox(int x, int y, boolean[] nums) {
		x = x / 3 * 3;
		y = y / 3 * 3;

		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (nums[board[i][j]])
					continue;
				nums[board[i][j]] = true;
			}
		}
	}

}
