import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
public class Solution {
	static int T, N, answer;
	static String[][] gameboard;
	static int[][] mark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			answer = 0;
			
			gameboard = new String[N][N];
			mark = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] inputs = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					gameboard[i][j] = inputs[j];
				}
			}
			
			run();
			
			bw.write("#" + tc + " " + answer + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
	static int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};

	private static void run() {
		// 지뢰는 -1, 다른 곳은 주변 지뢰 개수에 따른 숫자 표시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gameboard[i][j].equals(".")) {
					marking(i, j);
				} else if (gameboard[i][j].equals("*")) {
					mark[i][j] = -1;
				}
			}
		}
		
		// 0인값을 시작으로 bfs
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mark[i][j] == 0 && !visited[i][j]) {
					bfs(i, j, visited);
					answer++;
				} 
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mark[i][j] > 0 && !visited[i][j]) {
					mark[i][j] = -1;
					visited[i][j] = true;
					answer++;
				}
			}
		}
	}
	
	private static void marking(int x, int y) {
		int sum = 0;
		for (int i =0 ; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && gameboard[nx][ny].equals("*")) {
				sum++;
			}
		}
		
		mark[x][y] = sum;
	}

	public static void bfs(int x, int y, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		visited[x][y] = true;
		mark[x][y] = -1;
		queue.offer(new int[] {x, y});
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					// 그 주변 중에 0인 곳이 있으면 queue 추가
					if (!visited[nx][ny] && mark[nx][ny] == 0) {
						queue.offer(new int[] {nx, ny});
					}
					//바로 주변 8곳은 바로 방문처리
					visited[nx][ny] = true;
					mark[nx][ny] = -1;
				}
				
			}
		}
	}
	
	public static void print(int[][] arr) {
		for (int i =0 ; i < N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}
	
}
