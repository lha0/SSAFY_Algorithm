import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] seq = new int[N];
		int[] dp = new int[N];
		int ans = 1;
		
		for (int i = 0; i < N; i++) {
			seq[i] = sc.nextInt();
			dp[i] = 1;   // 일단 최소값으로 초기화
			int maxDpVal = 1;
			boolean largerNumExists = false;
			
			// 처음부터 내 앞에 있는 숫자까지 검사
			for (int j = 0; j <= i-1; j++) {
				// 현재 값보다 큰 수 중에서 dp값이 최대인 요소를 찾음
				if (seq[j] > seq[i]) {
					maxDpVal = dp[j] > maxDpVal ? dp[j] : maxDpVal;
					largerNumExists = true;
				}
			}
			
			if (largerNumExists) {
				dp[i] = maxDpVal + 1;
				ans = dp[i] > ans ? dp[i] : ans;
			}
		}
		System.out.println(ans);
		sc.close();
 	}
}
