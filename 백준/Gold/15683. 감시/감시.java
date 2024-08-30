import java.io.*;
import java.util.*;

public class Main {
	static int N, M, min;
	static int[][] office;
	static List<CCTV> cctvList;

	static class CCTV {
		int x, y, num;
		int[][] dx, dy;

		CCTV(int x, int y, int num, int[][] dx, int[][] dy) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dx = dx;
			this.dy = dy;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;

		office = new int[N][M];
		cctvList = new ArrayList<CCTV>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());

				if (office[i][j] > 0 && office[i][j] < 6) {
					addCCTV(i, j, office[i][j]);
				}
			}
		}

		run(0);

		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(int idx) {
		if (idx == cctvList.size()) {
			checkZero(); // 0 세려서 최솟값으로 갱신
			return;
		}

		CCTV cur = cctvList.get(idx);

		int[][] dx = cur.dx;
		int[][] dy = cur.dy;

		// i 번마다 90도 회전
		for (int i = 0; i < dx.length; i++) {
			// 본인의 방향으로 뻗어나가는 for문
			for (int j = 0; j < dx[i].length; j++) {
				int mult = 1;
				while (true) {
					int nx = cur.x + dx[i][j] * mult;
					int ny = cur.y + dy[i][j] * mult;

					if (nx >= 0 && nx < N && ny >= 0 && ny < M && office[nx][ny] != 6) {
						// 빈칸이면 자신 고유의 번호로 색칠 => 추후에 다른 값이 지우지 않게 하기 위해서
						if (office[nx][ny] == 0) {
							office[nx][ny] = idx + 8;
						}
						mult++;

					} else {
						break;
					}
				}

			}

			// 하나의 방향으로 다 뻗어나간 후, 다음 CCTV로
			run(idx + 1);

			// 원복
			for (int j = 0; j < dx[i].length; j++) {
				int mult = 1;
				while (true) {
					int nx = cur.x + dx[i][j] * mult;
					int ny = cur.y + dy[i][j] * mult;

					if (nx >= 0 && nx < N && ny >= 0 && ny < M && office[nx][ny] != 6) {
						// 이미 색칠되어 있음
						if (office[nx][ny] == idx + 8) {
							office[nx][ny] = 0;
						}
						mult++;
					} else {
						break;
					}
				}

			}
			// 돌아와서 90도 회전
		}

	}

	private static void checkZero() {
		int zero = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (office[i][j] == 0) {
					zero++;
				}
			}
		}

		min = Math.min(min, zero);
	}

	private static void addCCTV(int i, int j, int num) {
		int[][] dx, dy;
		switch (num) {
		case 1:
			dx = new int[][] { { -1 }, { 0 }, { 1 }, { 0 } };
			dy = new int[][] { { 0 }, { -1 }, { 0 }, { 1 } };
			cctvList.add(new CCTV(i, j, num, dx, dy));
			break;
		case 2:
			dx = new int[][] { { 0, 0 }, { -1, 1 } };
			dy = new int[][] { { -1, 1 }, { 0, 0 } };
			cctvList.add(new CCTV(i, j, num, dx, dy));
			break;
		case 3:
			dx = new int[][] { { -1, 0 }, { -1, 0 }, { 1, 0 }, { 1, 0 } };
			dy = new int[][] { { 0, -1 }, { 0, 1 }, { 0, -1 }, { 0, 1 } };
			cctvList.add(new CCTV(i, j, num, dx, dy));
			break;
		case 4:
			dx = new int[][] { { 0, 0, -1 }, { -1, 1, 0 }, { 0, 1, 0 }, { -1, 1, 0 } };
			dy = new int[][] { { -1, 1, 0 }, { 0, 0, -1 }, { -1, 0, 1 }, { 0, 0, 1 } };
			cctvList.add(new CCTV(i, j, num, dx, dy));
			break;
		case 5:
			dx = new int[][] { { 0, 0, 1, -1 } };
			dy = new int[][] { { 1, -1, 0, 0 } };
			cctvList.add(new CCTV(i, j, num, dx, dy));
			break;
		}
	}
}
