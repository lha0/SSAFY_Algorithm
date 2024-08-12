import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] maze;
	static int[] dx = new int[] {0, 0, 1, -1};
	static int[] dy = new int[] {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		maze = new int[N][M];
		for (int i =0 ; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				maze[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		bfs();
		
		bw.write(maze[N-1][M-1] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void bfs() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {0, 0});
		
		while (queue.size() != 0) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && maze[nx][ny] == 1) {
					maze[nx][ny] = maze[x][y] + 1;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
	}
}
