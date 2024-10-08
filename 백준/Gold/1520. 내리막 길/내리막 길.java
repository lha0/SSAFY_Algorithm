import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[][] maps, visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		maps = new int[M][N];
		visited = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int next = Integer.parseInt(st.nextToken());
				
				maps[i][j] = next;
				visited[i][j] = -1;
			}
		}
		
		run(0, 0);
		
		bw.write(visited[0][0] + "\n");
		br.close();
		bw.close();
	}

	private static int run(int x, int y) {
		if (x == M-1 && y == N-1) {
			return 1;
		}
		
		if (visited[x][y] != -1) return visited[x][y];
		
		visited[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < M && ny >= 0 && ny < N
					&& maps[nx][ny] < maps[x][y]) {
				visited[x][y] = visited[x][y] + run(nx, ny);
			}
		}
		
		return visited[x][y];
	}

}
