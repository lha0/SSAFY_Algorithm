import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, max = 1;
	static int[][] cheeseBoard;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			cheeseBoard = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					cheeseBoard[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 1;
			run();
			
			bw.write("#" + tc + " " + max + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {
		for (int day = 1; day <= 100; day++) {
	
			boolean[][] visited = new boolean[N][N];
			int area = 0;
			for (int i =0 ; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && cheeseBoard[i][j] > day) {
						bfs(i, j, visited, day);
						area++;
					}
				}
			}
			max = Math.max(max, area);
		}

	}

	private static void bfs(int x, int y, boolean[][] visited, int day) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		visited[x][y] = true;
		queue.offer(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i =0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && cheeseBoard[nx][ny] > day) {
					queue.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		} 
	}
}
