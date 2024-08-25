import java.io.*;
import java.util.*;

public class Main {
	static int N, cnt, answer;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);

		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		run();

		bw.write(answer + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {
		for (int i = 0; i < nums.length; i++) {
			int target = nums[i];
			
			int start = 0;
			int end = N-1;

			while (true) {
				if (start == i) {
					start++;
				} else if (end == i) {
					end--;
				}
				
				if (start >= end) break;
				
				if (nums[start] + nums[end] > target) {
					end--;
				} else if (nums[start] + nums[end] < target) {
					start++;
				} else {
					answer++;
					break;
				}
			}

		}
	}
}
