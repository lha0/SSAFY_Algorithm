import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M, R, C, L;
	static int[][] maps;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			maps = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] visit = new int[N][M];
			
			bfs(R, C, visit);
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(visit[i]));
//			}
//			System.out.println();
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
//					System.out.println(visit[i][j] + " " + L);
					if (visit[i][j] <= L && visit[i][j] != 0) answer++;
				}
			}
			
			bw.write("#" + tc + " " + answer + "\n");
		}
		
		bw.close();
		br.close();
	}

	private static void bfs(int r, int c, int[][] visit) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {r, c});
		visit[r][c] = 1;
		
		int[] dx = new int[] {};
		int[] dy = new int[] {};
		
		int level = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-- > 0) {
				int[] cur = queue.poll();
				int x = cur[0];
				int y = cur[1];
				
//				System.out.println("위치 " + x + " " + y);
//				System.out.println(maps[x][y]);
				
				int[][] possibleList = new int[][] {};
				
				switch(maps[x][y]) {
				case 1:
					dx = new int[] {-1, 1, 0, 0};
					dy = new int[] {0, 0, 1, -1};
					possibleList = new int[][] {{1, 2, 5, 6}, {1, 2, 4, 7}, {1, 3, 6, 7}, {1, 3, 4, 5}};
					break;
					
				case 2:
					dx = new int[] {-1, 1};
					dy = new int[] {0, 0};
					possibleList = new int[][] {{1, 2, 5, 6}, {1, 2, 4, 7}};
					break;
					
				case 3:
					dx = new int[] { 0, 0};
					dy = new int[] {1, -1};
					possibleList = new int[][] {{1, 3, 6, 7}, {1, 3, 4, 5}};
					break;
					
				case 4:
					dx = new int[] {-1, 0};
					dy = new int[] {0, 1};
					possibleList = new int[][] {{1, 2, 5, 6}, {1, 3, 6, 7}};
					break;
					
				case 5:
					dx = new int[] { 1, 0};
					dy = new int[] { 0, 1};
					possibleList = new int[][] {{1, 2, 4, 7}, {1, 3, 6, 7}};
					break;
					
				case 6:
					dx = new int[] { 1, 0};
					dy = new int[] {0, -1};
					possibleList = new int[][] {{1, 2, 4, 7}, {1, 3, 4, 5}};
					break;
					
				case 7:
					dx = new int[] {-1, 0};
					dy = new int[] {0, -1};
					possibleList = new int[][] {{1, 2, 5, 6}, {1, 3, 4, 5}};
					break;
				}
				
				for (int i = 0; i < dx.length; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					int[] canGo = possibleList[i];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && visit[nx][ny] == 0) {
						for (int j = 0; j < canGo.length; j++) {
							if (maps[nx][ny] == canGo[j]) {
								queue.offer(new int[] {nx, ny});
								visit[nx][ny] = level+1;
							}
						}
						
					}
					
				}
			}
			
			level++;
		}
	}

}
