import java.io.*;
import java.util.*;

/*
 * 순열로 회전 연산 순서 정하고
 * 회전하고
 * 합 더하고
 * 다음 순서로 넘어감
 * */

public class Main {
	static int N, M, K, r, c, s;
	static int[][] origin;
	static int[][] turn;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		origin = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(sts.nextToken());
			}
		}

		turn = new int[K][3];
		for (int i = 0; i < K; i++) {
			StringTokenizer ste = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				turn[i][j] = Integer.parseInt(ste.nextToken());
			}
		}

		int[][] sel = new int[K][3];
		boolean[] visited = new boolean[K];
		// 순열로 모든 순서 다 돌기
		comb(0, sel, visited);

		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static void comb(int idx, int[][] sel, boolean[] visited) {
		if (idx == sel.length) {
			int[][] newArr = new int[N][M];
			int[][] copyOrigin = new int[N][M];

			for (int i = 0; i < origin.length; i++) {
				for (int j = 0; j < origin[i].length; j++) {
					copyOrigin[i][j] = origin[i][j];
					newArr[i][j] = origin[i][j];
				}
			}
//			System.out.println("turnning");

//             for (int i = 0; i< sel.length; i++) {
//            	 System.out.println(Arrays.toString(sel[i]));
//             }
//             System.out.println();

			turnning(sel, copyOrigin, newArr);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sel[idx] = turn[i];
				comb(idx + 1, sel, visited);
				visited[i] = false;
			}

		}
	}

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void turnning(int[][] sel, int[][] copyOrigin, int[][] newArr) {
		// 연산별로 순서대로 실행
		for (int i = 0; i < K; i++) {
			int[] curOperation = sel[i];
//             System.out.println(Arrays.toString(curOperation));
			int sx = curOperation[0] - 1 - curOperation[2];
			int sy = curOperation[1] - 1 - curOperation[2];

			int ex = curOperation[0] - 1 + curOperation[2];
			int ey = curOperation[1] - 1 + curOperation[2];

			int dir = 0;
			int x = sx;
			int y = sy;

			// 정사각형 회전 5->3->1
			for (int width = ex - sx; width >= 1; width -= 2) {
				//하나의 정사각형당 4번씩
				for (int time = 1; time <= 4; time++) {
					for (int cnt = 0; cnt < width; cnt++) {
						int nx = x + dx[dir];
						int ny = y + dy[dir];

						// System.out.println(x + " " + y + " " + nx + " " + ny);

						newArr[nx][ny] = copyOrigin[x][y];
						x = nx;
						y = ny;
					}
					dir = (dir + 1) % 4;

					// 다시 원래값으로 돌아가면 정사각형 크기 줄이기
					if (x == sx && y == sy) {
						x = sx + 1;
						y = sy + 1;
						sx = x;
						sy = y;
						break;
					}
				}
				// 한 정사각형 끝나면 새로운 정사각형으로 값 업데이트 시키기
				for (int l = 0; l < newArr.length; l++) {
					for (int n = 0; n < newArr[l].length; n++) {
						copyOrigin[l][n] = newArr[l][n];
					}
				}

			}

//			print(newArr);
		}

		for (int i = 0; i < copyOrigin.length; i++) {
			int sum = 0;
			for (int j = 0; j < copyOrigin[i].length; j++) {
				sum += copyOrigin[i][j];
			}
			min = Math.min(min, sum);
		}
	}

	public static void print(int[][] copyOrigin) {
		for (int i = 0; i < copyOrigin.length; i++) {
			System.out.println(Arrays.toString(copyOrigin[i]));
		}
		System.out.println();
	}
}
