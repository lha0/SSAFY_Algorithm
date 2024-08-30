
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

		@Override
		public String toString() {
			return "Enemy [x=" + x + ", y=" + y + "] \n";
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

			// enemyList 복사
			ArrayList<Enemy> copyEnemy = new ArrayList<Enemy>();
			for (int i = 0; i < enemyList.size(); i++) {
				Enemy cur = enemyList.get(i);
				copyEnemy.add(new Enemy(cur.x, cur.y));
			}
			
			Collections.sort(copyEnemy);
			
//			System.out.println("궁사 위치 " + Arrays.toString(sel));
			
			answer = 0;
			// 세 명이면 적 죽이러
			if (num == 3)
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
				int idx = 0;
				int minY = 0;
				boolean kill = false;

				// enemylist를 돌면서 거리가 D이하인 적을 찾아서 없애기
				for (int j = 0; j < copyEnemy.size(); j++) {
					Enemy cur = copyEnemy.get(j);

					int dist = Math.abs(cur.x - myX) + Math.abs(cur.y - myY);
					
//					System.out.println(i);
//					System.out.println(dist + " " + D + " "+ minDist + " " + dist + " " + idx + " " + cur.y);
					
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
				
//				System.out.println(i + " " + minJ);
				
				if (kill) killIdx.put(idx, idx);
			}
			
//			System.out.println(killIdx);
			
			//killIdx에 포함되지 않은 적들로만 새로운 리스트 구성
			//killlist에 있는 궁수 한 번에 죽이기

			ArrayList<Enemy> newEnemy = new ArrayList<Enemy>();
			for (int i =0 ; i < copyEnemy.size(); i++) {
				if (killIdx.containsKey(i)) continue;
				else {
					Enemy cur = copyEnemy.get(i);
					newEnemy.add(new Enemy(cur.x, cur.y));
				}
			}
			
			answer += copyEnemy.size() - newEnemy.size();
			
//			System.out.println("after kill " + newEnemy);
//			System.out.println("answer " + answer);

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
					copyEnemy.remove(i);
					i--;
				}
			}
			
//			System.out.println("after move " + copyEnemy);

		}

	}

}
