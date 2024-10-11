import java.util.*;
import java.io.*;

public class Solution {
	static int T, N, W, H, answer;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int[][] maps = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());

				}
			}

			answer = Integer.MAX_VALUE;

			run(N, maps);

			if (answer == Integer.MAX_VALUE)
				answer = 0;
			bw.write("#" + tc + " " + answer + "\n");
		}

		br.close();
		bw.close();
	}

	private static void run(int time, int[][] maps) {
		if (time == 0) {
			// map 에 남은 벽돌 세리기
			int count = 0;
			for (int i = 0; i < maps.length; i++) {
				for (int j = 0; j < maps[i].length; j++) {
					if (maps[i][j] > 0)
						count++;
				}
			}

			answer = Math.min(answer, count);
			return;
		}

		// map의 가장 맨 위 블록들
		List<int[]> list = TopElement(maps);

		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);

			// 맵 복사
			int[][] newMap = new int[H][W];
			copyMap(newMap, maps);

			// 깨뜨리기
			Break(cur, newMap);

			// 중력
			Gravity(newMap);

			run(time - 1, newMap);

		}

	}

	private static void Gravity(int[][] newMap) {
		for (int c = 0; c < newMap[0].length; c++) {
			int row = H - 1;
			while (row > 0) {
				if (newMap[row][c] == 0) {
					int idx = row - 1;
					while (idx > 0 && newMap[idx][c] == 0) {
						idx--;
					}

					if (idx == 0 && newMap[idx][c] == 0)
						break;

					newMap[row][c] = newMap[idx][c];
					newMap[idx][c] = 0;

				}
				row--;
			}
		}

	}

	private static void Break(int[] curBlock, int[][] newMap) {
		// 깰 블록 담기
		Queue<int[]> queue = new ArrayDeque<int[]>();

		queue.offer(new int[] { curBlock[0], curBlock[1], newMap[curBlock[0]][curBlock[1]] });

		while (!queue.isEmpty()) {
			int[] curBreak = queue.poll();

			int initX = curBreak[0];
			int initY = curBreak[1];
			int weight = curBreak[2];

			// 현재 블록 깨기
			newMap[initX][initY] = 0;
			// 연쇄작용
			for (int i = 0; i < 4; i++) {
				for (int mult = 1; mult < weight; mult++) {
					int nx = initX + dx[i] * mult;
					int ny = initY + dy[i] * mult;

					if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
						if (newMap[nx][ny] > 1) { // 1보다 큰 값의 블록은 또 연쇄적으로 부서져야 함
							queue.offer(new int[] { nx, ny, newMap[nx][ny] });
						}
						newMap[nx][ny] = 0;
					}
				}
			}
		}
	}

	private static List<int[]> TopElement(int[][] originMap) {
		List<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < originMap.length; i++) {
			for (int j = 0; j < originMap[i].length; j++) {
				if (i == 0 && originMap[0][j] > 0) {
					list.add(new int[] { i, j });
				} else if (i - 1 >= 0 && originMap[i - 1][j] == 0 && originMap[i][j] > 0) {
					list.add(new int[] { i, j });
				}
			}
		}
		return list;
	}

	private static void copyMap(int[][] newMap, int[][] originMap) {

		for (int i = 0; i < originMap.length; i++) {
			for (int j = 0; j < originMap[i].length; j++) {
				newMap[i][j] = originMap[i][j];
			}
		}

	}

}
