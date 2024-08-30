import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D, answer, max;
	static List<Enemy> enemyList;

	static class Enemy implements Comparable<Enemy> {
		int x, y;

		Enemy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// x가 큰 순서대로 정렬 (map 가장 아래에 위치한 적), 같다면 y가 왼쪽
		@Override
		public int compareTo(Enemy o) {
			return this.x == o.x ? this.y - o.y : o.x - this.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		max = 0;

		enemyList = new ArrayList<Enemy>();

		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				int input = Integer.parseInt(sts.nextToken());

				if (input == 1) {
					enemyList.add(new Enemy(i, j));
				}
			}
		}

		Collections.sort(enemyList);

		// 궁수 자리 부분집합
		power(0, new boolean[M]);

		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void power(int idx, boolean[] sel) {
		if (idx == sel.length) {
			int num = 0; // 궁수 몇 명인지
			for (int i = 0; i < sel.length; i++) {
				if (sel[i])
					num++;
			}
			
			if (num != 3) return;

			// enemyList 복사
			ArrayList<Enemy> copyEnemy = new ArrayList<Enemy>();
			for (int i = 0; i < enemyList.size(); i++) {
				Enemy cur = enemyList.get(i);
				copyEnemy.add(new Enemy(cur.x, cur.y));
			}
			
			Collections.sort(copyEnemy);
			
			answer = 0;
			// 세 명이면 적 죽이러
			kill(sel, copyEnemy);

			max = Math.max(max, answer);

			// 아니면 그냥 return
			return;
		}

		sel[idx] = true;
		power(idx + 1, sel);
		sel[idx] = false;
		power(idx + 1, sel);
	}

	private static void kill(boolean[] sel, ArrayList<Enemy> copyEnemy) {
		while (copyEnemy.size() != 0) {
			// sel[i] 가 true인 자리가 궁수가 있는 자리
			// killlist에 넣어두고
			HashMap<Integer, Integer> killIdx = new HashMap<Integer, Integer>();
			
			for (int i = 0; i < sel.length; i++) {
				// false면 그냥 패스
				if (!sel[i])
					continue;

				int myX = N;
				int myY = i;
				
				int minDist = D+1;
				int idx = 0;   // 죽일 적의 idx
				int minY = 0;  // 가장 왼쪽인 y
				boolean kill = false;

				// enemylist를 돌면서 거리가 D이하인 적을 찾아서 없애기
				for (int j = 0; j < copyEnemy.size(); j++) {
					Enemy cur = copyEnemy.get(j);

					int dist = Math.abs(cur.x - myX) + Math.abs(cur.y - myY);
			
					//같은 거리면 j가 더 작은게 죽어야함
					if (dist <= D && minDist == dist) {
						if (minY > cur.y) {
							minY = cur.y;
							idx = j;
						}
						minDist = dist;
						kill = true;
					}

					else if (dist <= D && minDist > dist) {
						minY = cur.y;
						minDist = dist;
						idx = j;
						kill = true;
					}
				}
				
				if (kill) killIdx.put(idx, idx);
			}
			
			// killIdx에 포함되지 않은 적들로만 새로운 리스트 구성
			// = 죽이지 않은 적들로 포함된 리스트
			ArrayList<Enemy> newEnemy = new ArrayList<Enemy>();
			for (int i =0 ; i < copyEnemy.size(); i++) {
				if (killIdx.containsKey(i)) continue;
				else {
					Enemy cur = copyEnemy.get(i);
					newEnemy.add(new Enemy(cur.x, cur.y));
				}
			}
			
			// 두 리스트의 차이가 죽인 적의 수
			answer += copyEnemy.size() - newEnemy.size();
			
			copyEnemy.clear();
			for (int i = 0; i < newEnemy.size(); i++) {
				Enemy cur = newEnemy.get(i);
				copyEnemy.add(new Enemy(cur.x, cur.y));
			}
			
			// 적 이동
			for (int i = 0; i < copyEnemy.size(); i++) {
				Enemy cur = copyEnemy.get(i);
				cur.x++;

				if (cur.x == N) {
					copyEnemy.remove(i--);
				}
			}
		}

	}

}
