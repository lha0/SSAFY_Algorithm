import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if (i != j) graph[i][j] = 1000000005;
			}
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[start][end] = Math.min(cost, graph[start][end]);
		}
		
		run();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] >= 1000000005)
					bw.write(0 + " ");
				else
					bw.write(graph[i][j] + " ");
			}
			bw.write("\n");
		}
		
		br.close();
		bw.close();
	}
	private static void run() {
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (graph[i][j] > graph[i][k] + graph[k][j]) 
						graph[i][j] = graph[i][k] + graph[k][j];
				}
			}
		}
	}

}
