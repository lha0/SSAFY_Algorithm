import java.util.*;
import java.io.*;

public class Solution {
	static int T, N, minTime;
	static int[][] map;
	static int startX, startY, endX, endY;

	static Deque<int[]> needVisit = new ArrayDeque<>();
	static boolean[][] visited;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] Tin = br.readLine().split(" ");
		T = Integer.parseInt(Tin[0]);

		for (int tc = 1; tc <= T; tc++) {
			String[] Nin = br.readLine().split(" ");
			N = Integer.parseInt(Nin[0]);

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}

			String[] startStr = br.readLine().split(" ");
			startX = Integer.parseInt(startStr[0]);
			startY = Integer.parseInt(startStr[1]);

			String[] endStr = br.readLine().split(" ");
			endX = Integer.parseInt(endStr[0]);
			endY = Integer.parseInt(endStr[1]);

			visited = new boolean[N][N];
			visited[startX][startY] = true;

			needVisit.add(new int[] { startX, startY, 0 });

			minTime = Integer.MAX_VALUE;

			bfs();

			if (minTime == Integer.MAX_VALUE)
				minTime = -1;

			bw.write("#" + tc + " " + minTime + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void bfs() {
		while (needVisit.size() != 0) {
			int[] cur = needVisit.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];
			int tcnt = time;

			if (x == endX && y == endY) {
				minTime = Math.min(minTime, time);
			}

			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || visited[nx][ny]) {
					continue;
				} else {
					tcnt++;
					// 소용돌이가 있는데, 3초, 6초, 9초...가 아니면 지나갈 수 없음
					// 현재 위치 다시 넣고 tcnt 증가
					if (map[nx][ny] == 2 && tcnt % 3 != 0) {
						needVisit.add(new int[] { x, y, tcnt });
						continue;
					}

					// 기다렸다가 지나갈 타이밍이 되었을 때
					else if (map[nx][ny] == 2 && tcnt % 3 == 0) {
						visited[nx][ny] = true;
						needVisit.add(new int[] { nx, ny, tcnt });
						map[nx][ny] = 0;
					}
					// 나머지 경우는 그냥 지나갈 수 있음
					else {
						visited[nx][ny] = true;
						needVisit.add(new int[] { nx, ny, time + 1 });
					}
				}
			}
		}
	}
}
