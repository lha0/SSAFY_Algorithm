import java.io.*;
import java.util.*;

class Main {
	static int T, N, K, minVal;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			minVal = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int cur = Integer.parseInt(st.nextToken());
					
					board[i][j] = cur;
				}
			}
			
			for (int i = 0; i <= N-K; i++) {
				for (int j = 0; j <= N-K; j++) {
					run(i, j);
				}
			}
			
			bw.write(minVal + "\n");
			
		}
		
		br.close();
		bw.close();
	}
	
	public static void run(int startX, int startY) {
		int count = 0;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int val = board[startX + i][startY + j];
				if (val == 1) count++;
			}
		}
		
		if (minVal > count) minVal = count;
		
	}
}