import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int T, min = Integer.MAX_VALUE;
	static int[] price, plan;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			price = new int[4];
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			// index 1부터
			StringTokenizer sts = new StringTokenizer(br.readLine());
			plan = new int[13];
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(sts.nextToken());
			}

			// 1월부터 시작
			// min 초기값 : 1년
			min = price[3];
			run(1, 0);

			bw.write("#" + tc + " " + min + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(int month, int cost) {

		if (cost >= min) {
			return;
		}

		if (month > 12) {
			min = Math.min(min, cost);
			return;
		}

		// 1일
		run(month + 1, cost + plan[month] * price[0]);

		// 1달
		run(month + 1, cost + price[1]);

		// 3개월
		// 11, 12월이면 못 삼
		if (month >= 11)
			return;
		// 다음달, 다다음달 모두 이용계획 있으면
		else if (plan[month] > 0 || plan[month+1] > 0 || plan[month+2] > 0) {
			run(month + 3, cost + price[2]);
		}

	}

}
