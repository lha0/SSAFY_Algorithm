import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] init, graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		init = new int[N][N];
		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				init[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int start = 0; start < N; start++) {
			run(start);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(graph[i][j] + " ");
			}
			bw.write("\n");
		}
		
		br.close();
		bw.close();
	}

	private static void run(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		
		queue.add(start);
		
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (visited[cur]) continue;
			visited[cur] = true;
			
			for (int i = 0; i < N; i++) {
				if (init[cur][i] != 0) {
					queue.add(i);
					graph[start][i] = 1;
				}
			}
		}
		
	}

}
