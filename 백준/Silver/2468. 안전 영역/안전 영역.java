import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static Deque<int[]> needVisit;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] inN = br.readLine().split(" ");
		N = Integer.parseInt(inN[0]);

		map = new int[N][N];
		int maxheight = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				maxheight = Math.max(maxheight, map[i][j]);
			}
		}

		int areaCnt = 0;
		int height = 1;
		int maxCnt = 1;

		while (height < maxheight) {
			areaCnt = 0;
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					needVisit = new ArrayDeque<>();

					// 물에 잠기지 않고, 방문한 적 없는 지역
					if (map[i][j] > height && !visited[i][j]) {
						needVisit.add(new int[] { i, j });
						visited[i][j] = true;

						bfs(i, j, height);

						// 지역 하나 찾음
						areaCnt++;

					}

				}
			}
			height++;
			maxCnt = Math.max(areaCnt, maxCnt);

		}

		bw.write(maxCnt + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static void bfs(int i, int j, int height) {

		while (needVisit.size() != 0) {
			int[] coo = needVisit.poll();

			for (int idx = 0; idx < 4; idx++) {
				int nx = coo[0] + dx[idx];
				int ny = coo[1] + dy[idx];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] <= height)
					continue;
				else {
					needVisit.add(new int[] { nx, ny, height });
					visited[nx][ny] = true;
				}
			}
		}
	}

}
