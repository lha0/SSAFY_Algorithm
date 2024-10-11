import java.util.*;
import java.io.*;

public class Main {
	static int N, M, sx, sy;
	static int[][] maps, visited;
	static int [] dx = {0, 0, -1, 1};
	static int [] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		  
		  StringTokenizer st = new StringTokenizer(br.readLine());
		  N = Integer.parseInt(st.nextToken());
		  M = Integer.parseInt(st.nextToken());
		  
		  maps = new int[N][M];
		  for (int i = 0; i < N; i++) {
			  st = new StringTokenizer(br.readLine());
			  for (int j = 0; j < M; j++) {
				  int next = Integer.parseInt(st.nextToken());
				  maps[i][j] = next;
				  
				  if (next == 2) {
					  sx = i;
					  sy = j;
				  }
			  }
		  }
		  
		  visited = new int[N][M];
		  for (int i = 0; i < N; i++) {
			  Arrays.fill(visited[i], -1);
		  }
		  
		  run();
		  
		  for (int i = 0; i < N; i++) {
			  for (int j = 0; j < M; j++) {
				  
				  if (maps[i][j] >= 1 && visited[i][j] >= 0) bw.write(visited[i][j] + " ");
				  else if (maps[i][j] == 0) bw.write(0 + " ");
				  else bw.write(-1 + " ");
				  
				 
			  }
			  bw.write("\n");
		  }
		  
		  br.close();
		  bw.close();
	        
	}

	private static void run() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		queue.offer(new int[] {sx, sy});
		visited[sx][sy] = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == -1 && maps[nx][ny] == 1) {
					visited[nx][ny] = visited[x][y] + 1;
					queue.offer(new int[] {nx, ny});
					
				}
			}
			
		}
	}

}
