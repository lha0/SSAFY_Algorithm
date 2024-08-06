import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D;
	static int Ans = Integer.MIN_VALUE;
	static ArrayList<Enemy> enemys = new ArrayList<>();

	static class Enemy {
		int r, c;

		Enemy(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		D = Integer.parseInt(input[2]);

		for (int r = 0; r < N; r++) {
			String[] line = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				if (Integer.parseInt(line[c]) == 1)
					enemys.add(new Enemy(r, c));
			}
		}
		/*
		 * solving
		 * 
		 * 궁수의 위치를 정한다 (조합)
		 */
		combination(0, 0, new int[3]);

		bw.write(Ans + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static void combination(int idx, int k, int[] sel) {
		if (k == sel.length) {
			// 궁수 위치를 다 뽑으면, 적을 죽이자
			// 한 판마다 리스트를 복사해서 쓴다
			ArrayList<Enemy> temp = new ArrayList<>();
			for (int i = 0; i < enemys.size(); i++) {
				Enemy e = enemys.get(i);
				temp.add(new Enemy(e.r, e.c));
			}
			int cnt = 0;
			// temp에 있는 적을 죽이자
			while (temp.size() > 0) {
				ArrayList<Enemy> deathnote = new ArrayList<>();
				for (int i = 0; i < 3; i++) {
					// i번째 궁수가 화살을 쏜다
					int minD = Integer.MAX_VALUE;
					int minC = M;
					int minIdx = -1;

					for (int j = 0; j < temp.size(); j++) {
						Enemy en = temp.get(j);

						int dist = Math.abs(N - en.r) + Math.abs(sel[i] - en.c);

						// 나의 사정거리 안에 있나
						if (dist <= D) {
							// 거리가 가까운
							if (minD > dist) {
								minD = dist;
								minC = en.c;
								minIdx = j;
							} else if (minD == dist) {
								// 거리는 같은데 왼쪽에 있는거
								if (minC > en.c) {
									minD = dist;
									minC = en.c;
									minIdx = j;
								}
							}
						}

					} // 모든 적을 검사
					if (minIdx != -1) {
						deathnote.add(temp.get(minIdx));

					}
				} // 다음 궁수 \
					// 궁구들이 화살을 다 쏨
					// deathnote에 있는 적들 삭제
				for (int i = 0; i < deathnote.size(); i++) {
					for (int j = 0; j < temp.size(); j++) {
						if (deathnote.get(i) == temp.get(j)) {
							temp.remove(j--);
							cnt++;
						}
					}
				}

				// 1보 전진
				for (int i = 0; i < temp.size(); i++) {
					temp.get(i).r++;
					if (temp.get(i).r == N) {
						temp.remove(i--);
					}
				}
			} // end of while
			Ans = Math.max(Ans, cnt);
			return;
		}

		if (idx == M) {
			return;
		}

		sel[k] = idx;
		combination(idx + 1, k + 1, sel);
		combination(idx + 1, k, sel);
	}

}
