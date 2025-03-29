import java.io.*;

public class Main {
    static long[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        long answer = 0;

        if (N == 1) {
            bw.write(9 + "\n");
        }
        else {
            for (int n = 2; n <= N; n++) {
                for (int i = 0; i <= 9; i++) {
                    if (i == 0) dp[n][i] = dp[n-1][1];
                    else if (i == 9) dp[n][i] = dp[n-1][8];
                    else {
                        dp[n][i] = (dp[n-1][i-1] + dp[n-1][i+1]) % 1000000000;
                    }
                }
            }

            for (int i = 1; i <= 9; i++) {
                answer = (answer + dp[N][i]) % 1000000000;
            }

            bw.write(answer + "\n");
        }



        br.close();
        bw.close();

    }
}
