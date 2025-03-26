import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int max = numbers[0];
        dp[0] = numbers[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            max = Math.max(dp[i], max);
        }

        bw.write(max + "\n");

        br.close();
        bw.close();

    }
}
