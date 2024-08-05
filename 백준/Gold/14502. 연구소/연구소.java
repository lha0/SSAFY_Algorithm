import java.io.*;
import java.util.*;
/*
 * 벽 3개를 꼭 세워야 한다
 * 0 : 빈 칸 / 1 : 벽 / 2: 바이러스
 * 
 * 벽을 세 개 세우고, 바이러스가 퍼져나가고, 안전영역 체크
 * 
 * 벽 세울 곳 => 조합
 * */

public class Main {
	static int N, M, safeZone, maxZone;
	static int[][] sel = new int[3][2];
	static Deque<int[]> needVisit = new ArrayDeque<>();
	static ArrayList<int[]> wallPossible = new ArrayList<>();

	static int[] dx = new int[] { 1, -1, 0, 0 };
	static int[] dy = new int[] { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] lab = new int[N][M];
		int[][] copyLab = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				lab[i][j] = Integer.parseInt(sts.nextToken());
				copyLab[i][j] = lab[i][j];

				if (lab[i][j] == 2) {
					needVisit.offer(new int[] { i, j });
				}

				if (lab[i][j] == 0) {
					wallPossible.add(new int[] { i, j });
				}
			}
		}

		maxZone = Integer.MIN_VALUE;

		buildWall(lab, copyLab, 0, 0);

		bw.write(maxZone + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	// wallPossible 좌표 (0) 중에 3개 고르기
	public static void buildWall(int[][] lab, int[][] copyLab, int n, int r) {
		if (r == 3) {
			// 골랐으면 벽 세우고, virus 퍼뜨리고, 안전영역 카운팅
			for (int i = 0; i < sel.length; i++) {
				int[] coord = sel[i];
				lab[coord[0]][coord[1]] = 1;
			}
			int[][] newLab = virus(lab);
			countZero(newLab);
			// 다 끝나면 다시 초기화
			initBFS(lab, copyLab);
			return;
		}

		if (n == wallPossible.size())
			return;

		sel[r] = wallPossible.get(n);
		buildWall(lab, copyLab, n + 1, r + 1);
		buildWall(lab, copyLab, n + 1, r);

	}

	// lab을 다시 처음으로, 방문해야할 곳 다시 얻기
	public static void initBFS(int[][] lab, int[][] copyLab) {
		needVisit = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				lab[i][j] = copyLab[i][j];

				if (lab[i][j] == 2) {
					needVisit.offer(new int[] { i, j });
				}
			}
		}
	}

	// 바이러스 퍼뜨리기
	public static int[][] virus(int[][] lab) {
		while (needVisit.size() != 0) {
			int[] cur = needVisit.poll();
			int x = cur[0];
			int y = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || lab[nx][ny] >= 1) {
					continue;
				} else {
					lab[nx][ny] = 2; // 2
					needVisit.offer(new int[] { nx, ny });
				}
			}
		}
		return lab;
	}

	// 안전영역 카운팅
	public static void countZero(int[][] lab) {
		safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (lab[i][j] == 0) {
					safeZone++;
				}
			}
		}
		maxZone = Math.max(maxZone, safeZone);
	}

}
