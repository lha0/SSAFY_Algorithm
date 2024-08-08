import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[][] match;
	static int max = Integer.MIN_VALUE;
	static int n;
	static int cnt;

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] sel = new int[arr.length];
		boolean[] v = new boolean[arr.length];
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		match = new int[n][9];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				match[i][j] = sc.nextInt();
			}
		}

		sel[4] = 0; // 1번 선수는 항상 4번 타자 (인덱스 3)
		v[0] = true;
		rec(arr, sel, v, 0);
		System.out.println(max);
	}

	private static void rec(int[] arr, int[] sel, boolean[] v, int k) {
		if (k == arr.length) {
			max = Math.max(max, baseball(sel));
			return;
		}

		if (k == 3) {
			rec(arr, sel, v, k + 1);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = arr[i];
				rec(arr, sel, v, k + 1);
				v[i] = false;
			}
		}
	}

	private static int baseball(int[] arr) {

		int result = 0;
		int start = 0; // 현재 타자
		for (int k = 0; k < n; k++) {
			int out = 0;
			boolean[] base = new boolean[4]; // base[0]은 홈, base[1]은 1루, base[2]은 2루, base[3]은 3루
			while (out < 3) {
				int score = match[k][arr[start]];
				if (score == 0) {
					out++;
				} else {

					// 루에 있는 주자들 진루
					for (int i = 3; i >= 0; i--) {
						if (base[i]) {
							int value = i + score;
							if (value >= 4) {
								result++; // 홈런
							} else {
								base[value] = true; // 진루
							}
							base[i] = false; // 출루한 주자 처리
						}
					}
					// 현재 타자 처리
					if (score >= 4) {
						result++; // 홈런
					} else {
						base[score] = true; // 현재 타자 진루
					}
				}
				start++;
				if (start == 9) {
					start = 0;
				}
			}
		}
		return result;
	}
}