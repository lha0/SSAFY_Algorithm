import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M, K, answer;

	static List<Bio> bioList;

	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };

	static class Bio implements Comparable<Bio> {
		int x, y, num, dir;

		Bio(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Bio o) {
			if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = 0;

			bioList = new ArrayList<>();
			for (int k = 0; k < K; k++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(sts.nextToken());
				int y = Integer.parseInt(sts.nextToken());
				int num = Integer.parseInt(sts.nextToken());
				int dir = Integer.parseInt(sts.nextToken());

				bioList.add(new Bio(x, y, num, dir));
			}

			Collections.sort(bioList);

			run();

			bw.write("#" + tc + " " + answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {
		ArrayList<Bio> copyBio = new ArrayList<Bio>();

		for (int i = 0; i < K; i++) {
			Bio cur = bioList.get(i);
			copyBio.add(new Bio(cur.x, cur.y, cur.num, cur.dir));
		}

		for (int time = 1; time <= M; time++) {
			Collections.sort(copyBio);

			// 이동
			move(copyBio);

			Collections.sort(copyBio);

			// 합치기
			// 같은 위치로 모인 군집 있으면 합치기
			ArrayList<Bio> newList = new ArrayList<Bio>();
			add(copyBio, newList);
		}

		// 최종 개수
		for (int i = 0; i < copyBio.size(); i++) {
			answer += copyBio.get(i).num;
		}
	}

	private static void add(ArrayList<Bio> copyBio, ArrayList<Bio> newList) {

		for (int k = 0; k < copyBio.size(); k++) {

			List<Bio> sameList = new ArrayList<Bio>();

			Bio cur = copyBio.get(k);

			boolean sameFind = false;

			for (int l = k + 1; l < copyBio.size(); l++) {
				Bio next = copyBio.get(l);

				if (cur.x == next.x && cur.y == next.y) {
					sameFind = true;
					sameList.add(new Bio(next.x, next.y, next.num, next.dir));
					copyBio.remove(l);
					l--;
				}
			}

			// 같은 위치가 있으면
			if (sameFind) {
				sameList.add(new Bio(cur.x, cur.y, cur.num, cur.dir));

				int maxNum = 0;
				int sumNum = 0;
				int maxDir = 0;

				for (int i = 0; i < sameList.size(); i++) {
					if (sameList.get(i).num > maxNum) {
						maxNum = sameList.get(i).num;
						maxDir = sameList.get(i).dir;
					}
					sumNum += sameList.get(i).num;
				}

				newList.add(new Bio(cur.x, cur.y, sumNum, maxDir));
			}
			// 없으면 바로 반환할 새 리스트에 넣기
			else {
				newList.add(new Bio(cur.x, cur.y, cur.num, cur.dir));
			}

		}

		copyBio.clear();
		for (int i = 0; i < newList.size(); i++) {
			Bio cur = newList.get(i);
			copyBio.add(new Bio(cur.x, cur.y, cur.num, cur.dir));
		}
	}

	private static void move(ArrayList<Bio> copyBio) {
		// 하나의 군집마다 이동
		for (int k = 0; k < copyBio.size(); k++) {
			Bio cur = copyBio.get(k);

			int nx = cur.x + dx[cur.dir];
			int ny = cur.y + dy[cur.dir];

			cur.x = nx;
			cur.y = ny;

			// 약품이 있는 곳이면
			if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
				// 절반
				cur.num = cur.num / 2;
				// 방향 반대
				if (cur.dir == 1) {
					cur.dir = 2;
				} else if (cur.dir == 2) {
					cur.dir = 1;
				} else if (cur.dir == 3) {
					cur.dir = 4;
				} else {
					cur.dir = 3;
				}
			}
		}
	}

}
