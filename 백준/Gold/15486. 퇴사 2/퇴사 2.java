import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		int maxProfit = 0;
		int currMaxProfit = 0;
		
		// '오늘: i+1일'이라고 생각하고 계산
		for (int i = 0; i < N; i++) {
			int T = sc.nextInt();   // i번째 날 부터 상담이 걸리는 시간
			int P = sc.nextInt();   // 상담했을 때 받을 수 있는 금액
			
			if (i + T <= N && dp[i + T] < currMaxProfit + P) {   // dp[0] = 0 이기 때문에 1일차에도 인덱스 에러가 발생하지 않음
				dp[i + T] = currMaxProfit + P;   // 우항: 전날까지 최대한 번 금액에 오늘부터 벌 수 있는 금액을 더한 것
				maxProfit = dp[i + T] > maxProfit ? dp[i + T] : maxProfit;
			}

			// 오늘까지 번 최대이익 갱신하기
			currMaxProfit = dp[i + 1] > currMaxProfit ? dp[i + 1] : currMaxProfit;
		}
		
		System.out.println(maxProfit);
		sc.close();
	}
}