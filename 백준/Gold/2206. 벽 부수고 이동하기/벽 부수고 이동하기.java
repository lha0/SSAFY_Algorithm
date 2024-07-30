import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][][] visited;
	static Deque<int[]> nv = new ArrayDeque<>();
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new int[N][M];
		visited = new int[N][M][2];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split("");
			for (int  j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		nv.add(new int[] {0, 0, 0, 0});
		visited[0][0][0] = 1;
		visited[0][0][1] = 1;
		
		int result = bfs(bw);
		
		int min = Integer.MAX_VALUE;
		min = Math.min(visited[N-1][M-1][0], visited[N-1][M-1][1]);
		
		bw.write(result + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int bfs(BufferedWriter bw) throws IOException {
		
		while (nv.size() != 0) {
			int[] cur = nv.poll();
			int x = cur[0];
			int y = cur[1];
			int b = cur[2];
			int dist = cur[3];
			
			if (x == N-1 && y == M-1) {
				return dist+1;
			}
			
//			bw.write(x + " " + y + " " + b + " " + dist + "\n");
//			
//			bw.write("---------------\n");
//			for (int r = 0; r < N; r++) {
//				for (int c = 0; c < M; c++) {
//					bw.write(visited[r][c][0] + " ");
//				}
//			bw.write("\n");
//		}
//			bw.write("---------------\n");
//			for (int r = 0; r < N; r++) {
//				for (int c = 0; c < M; c++) {
//					bw.write(visited[r][c][1] + " ");
//				}
//			bw.write("\n");
//		}
//			bw.write("\n");
//		
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				} else {
					if (map[nx][ny] == 0) {
						if (visited[nx][ny][b] == 0) {
							nv.add(new int[]{nx, ny, b, dist+1});
							visited[nx][ny][b] = visited[x][y][b] + 1;
						}
					}
					//벽
					if (map[nx][ny] == 1) {
						//안 부셨을 때
						if (b == 0) {
							if (visited[nx][ny][1] == 0) {
								nv.add(new int[]{nx, ny, 1, dist+1});
								visited[nx][ny][1] = visited[x][y][1] + 1;
							}
						}
						
						//부셨을 때
						else {
							continue;
						}
					}
					
					
				}
			}
		}
		return -1;
	}
	

}
