import java.util.*;
import java.io.*;

public class Solution {
	static int T, N, K, answer;
	static String[] inputs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			inputs = br.readLine().split("");
			answer = 0;
			
			run();

			bw.write("#" + tc + " " + answer + "\n");
		}

		bw.close();
		br.close();

	}

	private static void run() {
		HashMap<String, Integer> str = new HashMap<String, Integer>();

		// N/4 번씩 반복
		for (int rot = 0; rot < N / 4; rot++) {
			if (rot != 0) {
				// 1칸씩 이동
				String last = inputs[inputs.length - 1];
				for (int i = inputs.length - 1; i > 0; i--) {
					inputs[i] = inputs[i - 1];
				}
				inputs[0] = last;
			}


			// 숫자 자르기
			for (int i = 0; i < inputs.length; i += N / 4) {
				String s = "";
				for (int j = i; j < i + N / 4; j++) {
					s += inputs[j];
				}

				str.put(s, 1);
			}
		}
		
		// key 내림차순 정렬
		List<Integer> nums = new ArrayList<>();
		for (String key : str.keySet()) {
			nums.add(Integer.parseInt(key, 16));
		}

		Collections.sort(nums, (o1, o2) -> {
			return o2 - o1;
		});
		
		answer = nums.get(K-1);
	}

}
