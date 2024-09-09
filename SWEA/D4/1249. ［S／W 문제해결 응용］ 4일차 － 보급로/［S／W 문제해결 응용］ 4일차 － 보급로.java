import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, answer;
	static int[][] maps, dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			maps = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] inputs = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			
			answer = 0;
			
			dist = new int[N][N];
			boolean[][] visited= new boolean[N][N];
			bfs(visited);
			
			answer = dist[N-1][N-1];
			
			bw.write("#" + tc + " " + answer + "\n");
		}
		
		
		bw.close();
		br.close();
	}

	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	
	private static void bfs(boolean[][] visited) {
		Queue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
			
		}); 
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[0][0] = 0;
		
		queue.offer(new int[] {0, 0, 0});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			int w = cur[2];
			
			if (visited[x][y]) continue;
			visited[x][y] = true;
			
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					if (dist[nx][ny] > w + maps[nx][ny]) {
						dist[nx][ny] = maps[nx][ny] + w;
						queue.offer(new int[] {nx, ny, maps[nx][ny] + w});
					}
				}
				
				
			}
		}
	}

	
	

	

}
