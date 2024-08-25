import java.io.*;
import java.util.*;

public class Main {
	static int N, M,totalCount, max = 0;
	static int[][] Paper;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Paper = new int[N][M];

		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Paper[i][j] = Integer.parseInt(sts.nextToken());
			}
		}

		int totalCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (Paper[i][j] == 1) {
					int result = bfs(i, j);
					
					max = Math.max(result, max);
					totalCount++;
				}
			}
		}

		bw.write(totalCount + "\n");
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { x, y });
		Paper[x][y] = 2;
		int count = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && Paper[nx][ny] == 1) {
					queue.offer(new int[] { nx, ny});
					Paper[nx][ny] = 2;
					count++;
				}
			}
		}
		
		return count;
	}

}
