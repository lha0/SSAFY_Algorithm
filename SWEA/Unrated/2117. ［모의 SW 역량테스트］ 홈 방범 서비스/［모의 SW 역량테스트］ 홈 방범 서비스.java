import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M, max;
	static int[][] land;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = 0;

			land = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					land[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			run();

			bw.write("#" + tc + " " + max + "\n");
		}

		bw.close();
		br.close();
	}

	private static void run() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bfs(i, j);
			}
		}
	}

	private static void bfs(int x, int y) {
		// level 탐색
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(new int[] { x, y, 0 });
		visited[x][y] = true;

		int level = 1;
		int count = 0; // 하나의 k당 포함되는 집의 개수

		while (!queue.isEmpty()) {
			int k = queue.size();

			int opFee = level * level + (level - 1) * (level - 1);

			while (k-- > 0) {
				int[] cur = queue.poll();

				if (land[cur[0]][cur[1]] == 1)
					count++;

				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];

					if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
						queue.offer(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			} // while end

			int earn = M * count - opFee;

			level++;

			if (earn < 0)
				continue;

			max = Math.max(count, max);

		}
	}

}
