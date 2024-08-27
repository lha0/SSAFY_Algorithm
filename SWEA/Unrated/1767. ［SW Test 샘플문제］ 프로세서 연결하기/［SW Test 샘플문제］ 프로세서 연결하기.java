import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, core;
	static int min, max;
	static int[][] board;
	static List<int[]> coord;
	static ArrayList<int[]> combList;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for (int tc = 1; tc <= T; tc++) {	
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			 min = Integer.MAX_VALUE;
			 max = 0;
			 coord = new ArrayList<>();
			 core = 0;
			
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					// 끝에있는 코어 제외 개수 세리기, 좌표 저장
					if (i != 0 && i != N-1 && j != 0 && j != N-1 && board[i][j] == 1) {
						core++;
						coord.add(new int[] {i, j});
					}
				}
			}
			
			// 좌표마다 부분조합 정하기
			comb(0, new boolean[coord.size()]);
			
			if (min == Integer.MAX_VALUE) min = 0;
			
			bw.write("#" + tc + " " + min + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	

	private static void comb(int k, boolean[] sel) {
		if (k == sel.length) {
			combList = new ArrayList<>();
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) {
					combList.add(coord.get(i));	
				}
			}
			
			//부분조합별로 코어 연결해보기
			run(0, 0, 0);
			return;
		}
		
		sel[k] = true;
		comb(k+1, sel);
		sel[k] = false;
		comb(k+1, sel);
	}

	private static void run(int idx, int core, int distResult) {
		//기존 맥스값보다 더 많은 코어를 연결하면, max랑 min 초기화
		if (max < core) {
			max = core;
			min = distResult;
		} else if (max == core) {
			min = Math.min(min, distResult);
		}
		
		if (idx == combList.size()) {
			return;
		}
		
		int[] coo = combList.get(idx);
		int x = coo[0];
		int y = coo[1];
		
		for (int dir = 0 ; dir < 4; dir++) {
			// 해당 방향으로 나아갈 수 있으면
			if (check(x, y, dir)) {
				int result = marking(x, y, dir);
				run(idx+1, core+1, distResult + result);
				unmarking(x, y, dir);
			}
		}
	}

	private static void unmarking(int x, int y, int dir) {
		int mult = 1;
		while (true) {
			int nx = x + dx[dir] * mult;
			int ny = y + dy[dir] * mult;
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (board[nx][ny] == 1) {
					board[nx][ny] = 0;
					mult++;
				}
			} else {
				break;
			}
		}
	}

	private static int marking(int x, int y, int dir) {
		int mult = 1;
		int dist = 0;
		while (true) {
			int nx = x + dx[dir] * mult;
			int ny = y + dy[dir] * mult;
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (board[nx][ny] == 0) {
					board[nx][ny] = 1;
					mult++;
					dist++;
				}
			} else {
				break;
			}
		}
		return dist;
	}

	private static boolean check(int x, int y, int dir) {
		//dir 방향으로 뻗어나갈 수 있는지 체크
		int mult = 1;
		while (true) {
			int nx = x + dx[dir] * mult;
			int ny = y + dy[dir] * mult;
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (board[nx][ny] == 0) {
					mult++;
				} else {
					return false;
				}
			} else {
				break;
			}
		}
		return true;
	}

}
