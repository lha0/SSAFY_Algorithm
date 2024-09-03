import java.util.*;
import java.io.*;

public class Solution {
	static int T, change, max;
	static String[] numStr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			numStr = st.nextToken().split("");
			change = Integer.parseInt(st.nextToken());

			int[] num = new int[numStr.length];
			for (int i = 0; i < numStr.length; i++) {
				num[i] = Integer.parseInt(numStr[i]);
			}
			
			max = 0;
			
			if (num.length < change) {
				change = num.length;
			}
			run(0, num);

			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();
		br.close();

	}

	public static void run(int c, int[] num) {

		if (c == change) {
			// 합치기
			int result = 0;
			int mult = 1;
			for (int i = num.length - 1; i >= 0; i--) {
				result += num[i] * mult;
				mult *= 10;
			}

			max = Math.max(max, result);

			return;
		}

		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				int temp = num[i];
				num[i] = num[j];
				num[j] = temp;
				
				run(c+1, num);

				temp = num[i];
				num[i] = num[j];
				num[j] = temp;

			}
		}
	}

}
