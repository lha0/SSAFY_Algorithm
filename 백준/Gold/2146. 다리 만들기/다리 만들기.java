
import java.util.*;

class Point {
	int r, c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;

	}

}

public class Main {

	static int n;
	static int map[][];
	static int[] rr = { -1, 1, 0, 0 };
	static int[] cc = { 0, 0, -1, 1 };

	static boolean[][] vis;

	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		vis = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 맵의 이름을 변경해야한다.
		int cnt = 2;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && !vis[i][j]) {
					checkIsland(i, j, cnt);
					cnt++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0) {
					bfsIsland(i, j, new boolean[n][n], map[i][j]);
				}
			}
		}

		System.out.println(min);

	}

	private static void bfsIsland(int r, int c, boolean[][] v, int cnt) {
		int result = 0;
		Queue<Point> q = new ArrayDeque<Point>();
		v[r][c] = true;
		q.offer(new Point(r, c));

		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				Point p = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = p.r + rr[i];
					int nc = p.c + cc[i];

					if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] != cnt && map[nr][nc] != 0) {
						min = Math.min(min, result);
						return;
					}
					if (nr >= 0 && nr < n && nc >= 0 && nc < n && !v[nr][nc] && map[nr][nc] == 0) {
						q.offer(new Point(nr, nc));
						v[nr][nc]=true;
						
					}
				}
			}
			result++;

		}

	}

	private static void checkIsland(int r, int c, int cnt) {
		Queue<Point> q = new ArrayDeque<>();
		vis[r][c] = true;
		q.offer(new Point(r, c));
		map[r][c] = cnt;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = p.r + rr[i];
				int nc = p.c + cc[i];

				if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == 1 && !vis[nr][nc]) {
					q.offer(new Point(nr, nc));
					map[nr][nc] = cnt;
					vis[nr][nc]=true;
				}

			}

		}

	}

}
