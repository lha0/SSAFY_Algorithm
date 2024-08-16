import java.util.*;

class Pipe {
	int r;
	int c;
	int type;

	Pipe(int r, int c, int type) {
		this.r = r;
		this.c = c;
		this.type = type;
	}
}

public class Main {

	static int[] dr = { 0, 1, 1, 1, 0, 1, 1 };
	static int[] dc = { 1, 1, 0, 1, 1, 0, 1 };
	static int[] dt = { 2, 4, 3, 4, 2, 3, 4 };
	static int result = 0;
	static int[][] map;
	static int n;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 가로 : 2 세로 : 3 대각선 : 4
		if(map[n][n]==0) {
			bfs(1, 2, 2);			
		}
		System.out.println(result);
	}

	private static void bfs(int r, int c, int t) {
		Queue<Pipe> q = new ArrayDeque<>();
		q.offer(new Pipe(r, c, t));

		while (!q.isEmpty()) {
			Pipe p = q.poll();

			if (p.r == n && p.c == n) {
				result++;
			}
			// 가로 세로 대각선 검사
			int nr;
			int nc;
			int type = p.type;
			if (type == 2) {
				for (int i = 0; i < 2; i++) {
					nr = p.r + dr[i];
					nc = p.c + dc[i];
					if (nr >= 0 && nr < n + 1 && nc >= 0 && nc < n + 1 && map[nr][nc] == 0) {
						if (dt[i] == 4) {
							if (map[nr - 1][nc] == 0 && map[nr][nc - 1] == 0) {
								q.offer(new Pipe(nr, nc, dt[i]));
							}
						} else {
							q.offer(new Pipe(nr, nc, dt[i]));
						}
					}
				}
			} else if (type == 3) {
				for (int i = 2; i < 4; i++) {
					nr = p.r + dr[i];
					nc = p.c + dc[i];
					if (nr >= 0 && nr < n + 1 && nc >= 0 && nc < n + 1 && map[nr][nc] == 0) {
						if (dt[i] == 4) {
							if (map[nr - 1][nc] == 0 && map[nr][nc - 1] == 0) {
								q.offer(new Pipe(nr, nc, dt[i]));
							}
						} else {
							q.offer(new Pipe(nr, nc, dt[i]));
						}
					}
				}
			} else if (type == 4) {
				for (int i = 4; i < 7; i++) {
					nr = p.r + dr[i];
					nc = p.c + dc[i];
					if (nr >= 0 && nr < n + 1 && nc >= 0 && nc < n + 1 && map[nr][nc] == 0) {
						if (dt[i] == 4) {
							if (map[nr - 1][nc] == 0 && map[nr][nc - 1] == 0) {
								q.offer(new Pipe(nr, nc, dt[i]));
							}
						} else {
							q.offer(new Pipe(nr, nc, dt[i]));
						}
					}
				}
			}
		}

	}

}
