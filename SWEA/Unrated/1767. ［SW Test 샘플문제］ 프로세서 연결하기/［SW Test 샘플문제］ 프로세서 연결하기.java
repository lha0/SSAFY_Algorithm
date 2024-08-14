
import java.util.*;

class Process {

	int r;

	int c;

	Process(int r, int c) {

		this.r = r;

		this.c = c;

	}

}

public class Solution {

	static int n;

	static int[] rr = { -1, 1, 0, 0 };

	static int[] cc = { 0, 0, -1, 1 };

	static int[][] map;

	static ArrayList<Process> list;

	static int min, procCnt;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			map = new int[n][n];
			min = Integer.MAX_VALUE;
			procCnt = 0;
			list = new ArrayList<>(); // 새로운 테스트 케이스를 위한 초기화

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int input = sc.nextInt();
					if (input == 1) {
						if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
							map[i][j] = 3; // 가장자리에 있는 코어는 전원이 연결된 상태로 간주
						} else {
							map[i][j] = 2; // 내부 코어로 표시
							list.add(new Process(i, j));
						}
					} else {
						map[i][j] = input;
					}
				}
			}

			rec(0, 0, 0);
			System.out.println("#" + tc + " " + min);
		}

	}

	private static void rec(int idx, int proc, int len) {

		// basis part

		if (idx == list.size()) {
			if (proc > procCnt) {
				procCnt = proc;
				min = len;
			} else if (proc == procCnt) {
				min = Math.min(len, min);
			}
			return;
		}

		Process p = list.get(idx);
		int r = p.r;
		int c = p.c;
		for (int i = 0; i < 4; i++) { // 4방탐색
			if (checkLine(r, c, i)) { // 선 그리기 가능한지 조건검사 선그리기를 하ㅁ
				int line = drawLine(r, c, i, 3); // 전선 설치
				rec(idx + 1, proc + 1, len + line); // 다음 재귀 호출
				drawLine(r, c, i, 0); // 전선 제거

			}

		}
		// 이 코어를 연결하지 않고 다음 코어로 넘어가는 경우
		rec(idx + 1, proc, len); // 아무것도 설치 하지 않을 경우 확인

	}

	private static int drawLine(int r, int c, int k, int value) {
		// check 검사를 하면 무조건 그림을 그린다.
		int nr = r + rr[k];
		int nc = c + cc[k];
		// nr , nc
		int count = 0;
		// 0~n 사이 조건 검사
		while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			map[nr][nc] = value;
			count++;

			nr += rr[k];
			nc += cc[k];
		}
		return count;
	}

	private static boolean checkLine(int r, int c, int k) {
		int nr = r + rr[k];
		int nc = c + cc[k];
		// nr , nc

		// 0~n 사이 조건 검사
		while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			if (map[nr][nc] != 0) {
				return false;
			}
			nr += rr[k];
			nc += cc[k];
		}
		return true;
	}

}