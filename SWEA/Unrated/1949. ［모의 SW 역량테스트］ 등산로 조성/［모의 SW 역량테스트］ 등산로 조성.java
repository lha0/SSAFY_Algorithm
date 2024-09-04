import java.util.*;
import java.io.*;

public class Solution {
	static int T, N, K, answer;
	static int[][] maps;
	static List<int[]> start;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			maps = new int[N][N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
					
					if (max < maps[i][j]) max = maps[i][j];
				}
			}
			
			start = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (maps[i][j] == max) start.add(new int[] {i, j});
				}
			}
			
			answer = 0;
			for (int i = 0; i < start.size(); i++) {
				int[] s = start.get(i);
				
				//maps 복사
				int[][] copyMap = new int[N][N];
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						copyMap[r][c] = maps[r][c];
					}
				}
				
				boolean[][] visited = new boolean[N][N];
				
				visited[s[0]][s[1]] = true;
				dfs(copyMap, visited, s[0], s[1], K, 1, 1);
			}

			bw.write("#" + tc + " " + answer + "\n");
		}

		bw.close();
		br.close();
	}

	private static void dfs(int[][] copyMap ,boolean[][] visited , int x, int y, int k, int cnt, int cut) {	

		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				//다음 높이가 낮으면 그대로 go
				if (copyMap[nx][ny] < copyMap[x][y] && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(copyMap, visited, nx, ny, k, cnt+1, cut);
					visited[nx][ny] = false;
				}
					
				
				// 높이가 같고 k가 0보다 크면 깎고 보내기
				if (copyMap[nx][ny] == copyMap[x][y] && k > 0 && cut != 0 && !visited[nx][ny] ) {
					for (int cc = 1; cc <= k; cc++) {
						copyMap[nx][ny] -= cc;
						visited[nx][ny] = true;
						dfs(copyMap, visited, nx, ny, k-cc, cnt+1, 0);
						copyMap[nx][ny] += cc;
						visited[nx][ny] = false;
					}
					
				}
				
				
				// 높이가 높고, k보다 높이차가 작으면 높이차+1부터 k까지 깎고 보내기
				if (copyMap[nx][ny] > copyMap[x][y] &&
						k > copyMap[nx][ny] - copyMap[x][y] && cut != 0 && !visited[nx][ny]) {
					for (int cc = copyMap[nx][ny] - copyMap[x][y]+1; cc <= k; cc++) {
						copyMap[nx][ny] -= cc;
						visited[nx][ny] = true;
						dfs(copyMap, visited, nx, ny, k-cc, cnt+1, 0);
						copyMap[nx][ny] += cc;
						visited[nx][ny] = false;
					}
					
				}
					
			}
		}
		
		answer = Math.max(answer, cnt);
		
	}

}
