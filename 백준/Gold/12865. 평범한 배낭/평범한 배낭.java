
import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] W, V;
	static int[][] knapsack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int[N+1];
		V = new int[N+1];
		knapsack = new int[N+1][K+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			W[i+1] = Integer.parseInt(st.nextToken());
			V[i+1] = Integer.parseInt(st.nextToken());
		}
		
		run();
		
		bw.write(knapsack[N][K] + "\n");
		bw.close();
		br.close();
	}

	private static void run() {
		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= K; k++) {
				if (k >= W[i]) knapsack[i][k] = Math.max(knapsack[i-1][k], V[i] + knapsack[i-1][k-W[i]]);
				else knapsack[i][k] = Math.max(knapsack[i-1][k], knapsack[i][k-1]);
			}
		}
	}

}
