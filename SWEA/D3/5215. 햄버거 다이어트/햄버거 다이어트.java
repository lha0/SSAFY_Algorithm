import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, L;
	static int[] score;
	static int[] kcal;
	static int[] tgt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			score = new int[N];
			kcal = new int[N];
			tgt = new int[N];

			for (int i = 0; i < N; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				int Ti = Integer.parseInt(sts.nextToken());
				int Ki = Integer.parseInt(sts.nextToken());

				score[i] = Ti;
				kcal[i] = Ki;
			}

			int max = 0;
			for (int r = 1; r <= N; r++) {
				for (int c = 0; c < r; c++) {
					tgt[c] = 1;
				}

				Arrays.sort(tgt);

				do {
					int scoreSum = 0;
					int kcalSum = 0;

					for (int i = 0; i < N; i++) {
						if (tgt[i] == 0)
							continue;

						kcalSum += kcal[i];
						scoreSum += score[i];

						if (kcalSum > L)
							break;
						else
							max = Math.max(scoreSum, max);
					}

				} while (np(tgt));
			}

			bw.write("#" + tc + " " + max + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static boolean np(int[] score) {

		int N = score.length;
		int i = N - 1;
		while (i > 0 && score[i - 1] >= score[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (score[i - 1] >= score[j])
			--j;

		swap(score, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(score, i++, k--);
		}
		return true;
	}

	private static void swap(int[] score, int i, int j) {
		int temp = score[i];
		score[i] = score[j];
		score[j] = temp;
	}

}
