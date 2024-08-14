
import java.util.*;

class Point2 {
	int r, c;

	Point2(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {

	static int[][] map;
	static int n, m;
	static boolean[][] v;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		v = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				map[i][j] = Integer.parseInt(String.valueOf(c));
			}

		}

		System.out.println(bfs(0, 0) - 1);

	}

	private static int bfs(int r, int c) {
		int cnt = 1;
		Queue<Point2> q = new ArrayDeque();
		q.offer(new Point2(r, c));
		v[r][c] = true;
		map[r][c] = 1;
		cnt++;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point2 p = q.poll();

				if (p.r == n - 1 && p.c == m - 1) {
					return cnt;
				}

				for (int j = 0; j < 4; j++) {
					int nr = p.r + dr[j];
					int nc = p.c + dc[j];

					if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 1 && !v[nr][nc]) {
						map[nr][nc] = cnt;
						q.offer(new Point2(nr, nc));
						v[nr][nc] = true;
					}
				}
			}
			cnt++;
		}

		return cnt;

	}

}
