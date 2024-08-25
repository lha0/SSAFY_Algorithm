import java.io.*;
import java.util.*;

public class Main {
	static int N, S, answer;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		nums = new int[N];
		StringTokenizer sts = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(sts.nextToken());
		}

		run(0, new boolean[N]);
		bw.write(answer + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(int k, boolean[] sel) {
		if (k == sel.length) {
			int sum = 0;
			int cnt = 0;
			for (int i = 0; i < k; i++) {
				if (sel[i]) {
					sum += nums[i];
					cnt++;
				}
			}

			if (sum == S && cnt != 0) {
				answer += 1;
			}

			return;

		}

		sel[k] = true;
		run(k + 1, sel);
		sel[k] = false;
		run(k + 1, sel);
	}

}
