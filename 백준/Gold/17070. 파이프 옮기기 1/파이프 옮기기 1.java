import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[][] home;

	// 다음으로 갈 방향 탐색
	// 가로 방향 : dx[0] 세로 : dx[1] 대각선 dx[2]
	static int[][] dx = new int[][] { { 0, 0, 1 }, { 0, 1, 1 }, { 0, 1, 1 } };
	static int[][] dy = new int[][] { { 1, 0, 1 }, { 0, 0, 1 }, { 1, 0, 1 } };

	// 대각선으로 가기 전에 3곳 비어있는지 확인
	static int[] xcheck = new int[] { 0, 1, 1 };
	static int[] ycheck = new int[] { 1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);

		home = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기 값
		home[0][0] = 2;
		home[0][1] = 2;

		dfs(0, 1, 0);

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int r, int c, int dir) {
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}

		// i=0: 가로, 1: 세로, 2: 대각선
		for (int i = 0; i < dx[dir].length; i++) {
			if (dx[dir][i] == 0 && dy[dir][i] == 0) {
				continue;
			}
			int nx = r + dx[dir][i];
			int ny = c + dy[dir][i];
			int ndir = i;

			// 다음 갈 방향이 대각선일 때는 세 방향 다 탐색
			if (ndir == 2) {
				for (int q = 0; q < 3; q++) {
					if (r + xcheck[q] >= 0 && r + xcheck[q] < N && c + ycheck[q] >= 0 && c + ycheck[q] < N
							&& home[r + xcheck[q]][c + ycheck[q]] == 1)
						return;
				}
			}

			// 범위 탐색 + 가로나 세로로 이동할 때는 갈 방향만 탐색
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || home[nx][ny] == 1) {
				continue;
			}
			// 갈 수 있으면 재귀
			else {
				home[nx][ny] = 2;
				dfs(nx, ny, ndir);
				home[nx][ny] = 0;
			}
		}
	}

}
