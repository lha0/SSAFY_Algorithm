import java.io.*;
import java.util.*;

public class Main {
	static int T, N, M, K, answer;
	static int[][] land;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = 0;

			land = new int[N][M];
			for (int i = 0; i < K; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(sts.nextToken());
				int B = Integer.parseInt(sts.nextToken());

				land[B][A] = 1;
			}

			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && land[i][j] == 1) {
						bfs(i, j, visited);
						answer++;
					}

				}
			}

			bw.write( answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int x, int y, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M 
						&& !visited[nx][ny] && land[nx][ny] == 1) {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}

	}

}
