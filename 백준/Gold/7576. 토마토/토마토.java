
import java.util.*;
import java.io.*;

public class Main {
	static int M, N;
	static int[][] map;
	static int[][] visited;
	static Deque<int[]> needVisit;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new int[N][M];
		needVisit = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(sts.nextToken());

				if (map[i][j] == 1) {
					needVisit.add(new int[] { i, j });
					visited[i][j] = 1;
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(visited[i][j] + " ");
//				
//			}
//			System.out.println();
//
//		}

		int result = 0;
		run();

		L: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//System.out.print(visited[i][j] + " ");
				if (map[i][j] == 0) {
					result = -1;
					break L;
				} else {
					result = Math.max(result, visited[i][j]);
					
				}
			}
			//System.out.println();

		}

		/* 출력 */
		if (result == -1) bw.write(result + "\n");
		else bw.write(result-1 + "\n");
		bw.flush();
		br.close();
		bw.close();

	}

	public static void run() {
		while (needVisit.size() != 0) {
			int[] cur = needVisit.pop();
			int i = cur[0], j = cur[1];
			
//			System.out.println("--------------");
//			for (int r = 0; r < N; r++) {
//				for (int c = 0; c < M; c++) {
//					System.out.print(visited[r][c] + " ");
//					
//				}
//				System.out.println();
//
//		}


			for (int idx = 0; idx < 4; idx++) {
				int nx = i + dx[idx];
				int ny = j + dy[idx];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == -1) {
					continue;
				} else {
					if (visited[nx][ny] == 0) {
						map[nx][ny] = 1;
						visited[nx][ny] = visited[i][j] + 1;
						needVisit.add(new int[] { nx, ny });
					}
				}
			}
		}
	}

}