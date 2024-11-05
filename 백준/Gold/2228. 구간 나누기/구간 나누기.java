import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] sum;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sum = new int[N+1];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum[i+1] = sum[i] + num;
		}
		
		run();
		
		bw.write(dp[N][M] + "\n");
		
		br.close();
		bw.close();
	}

	private static void run() {
		dp = new int[N+1][M+1];

		for(int i=0; i<=N; i++){
            for(int j=1; j<=M; j++){
                dp[i][j] = -32768*101;
            }
        }
		
		dp[1][1] = sum[1];
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = dp[i-1][j];
				
                for(int k = 0; k <= i-2; k++){
                    if(j == 1){
                        dp[i][j] = Math.max(dp[i][j],sum[i]);
                    }
                        dp[i][j] = Math.max(dp[i][j], dp[k][j-1]+sum[i]-sum[k+1]);
                    
                }
				
				
				
			}
		}

	}

}
