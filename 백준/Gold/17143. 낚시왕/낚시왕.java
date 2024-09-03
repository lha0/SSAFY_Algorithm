import java.io.*;
import java.util.*;

public class Main {
	static int R, C, M, answer;
	static List<Shark> sharkList;

	// 위 아래 오른쪽 왼쪽
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };

	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.c == o.c)
				return this.r - o.r;
			return this.c - o.c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharkList = new ArrayList<Shark>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sharkList.add(new Shark(r, c, s, d, z));
		}

		answer = 0;

		run();

		bw.write(answer + "\n");
		bw.close();
		br.close();
	}

	private static void run() {
		int person = 0;
		// 사람 이동
		while (person++ < C && !sharkList.isEmpty()) {
			Collections.sort(sharkList);

			// 상어 리스트 중에 같은 열, 작은 행에 있는 상어
			int idx = 0;
			for (int i = 0; i < sharkList.size(); i++) {
				if (sharkList.get(i).c == person) {
					idx = i;
					break;
				}
			}
			Shark shark = sharkList.get(idx);

			// 같은 열 내에 있으면 잡기
			if (shark.c == person) {
				answer += shark.z;
				sharkList.remove(idx);
			}

			// 상어 이동
			move();

			Collections.sort(sharkList);

			// 같은 칸에 여러 상어가 있으면 합치기
			add();

			// 이동하면서 잡아먹힌 상어들 리스트에서 삭제
			for (int i = 0; i < sharkList.size(); i++) {
				if (sharkList.get(i).d == 0 && sharkList.get(i).z == 0) {
					sharkList.remove(i);
					i--;
				}
			}
		}

	}

	private static void add() {
		// 2차원 배열에 상어 넣으면서, 이미 그 칸에 상어가 있으면 큰 값 비교해서 큰 거로 대체
		Shark[][] sharkMap = new Shark[R + 1][C + 1];

		for (int i = 0; i < sharkList.size(); i++) {
			Shark cur = sharkList.get(i);

			if (sharkMap[cur.r][cur.c] == null) {
				sharkMap[cur.r][cur.c] = cur;
			} else {
				Shark origin = sharkMap[cur.r][cur.c];
				int originZ = origin.z;
				int newZ = cur.z;

				// 새로 들어올 애가 더 크면
				if (originZ < newZ) {
					sharkMap[cur.r][cur.c] = cur;
					origin.z = 0;
					origin.d = 0;
				} else {
					cur.z = 0;
					cur.d = 0;
				}

			}
		}

	}

	private static void move() {
		for (int i = 0; i < sharkList.size(); i++) {
			Shark cur = sharkList.get(i);

			// 속력만큼 방향 d로 이동 (1씩 증가)
			for (int mult = 1; mult <= cur.s; mult++) {
				int nx = cur.r + dx[cur.d];
				int ny = cur.c + dy[cur.d];

				// 경계를 넘어서면
				if (nx <= 0 || nx > R || ny <= 0 || ny > C) {
					switch (cur.d) {
					case 1:
						cur.d = 2;
						break;
					case 2:
						cur.d = 1;
						break;
					case 3:
						cur.d = 4;
						break;
					case 4:
						cur.d = 3;
						break;
					}

					nx = cur.r + dx[cur.d];
					ny = cur.c + dy[cur.d];
				}

				//이동한 위치로 업데이트
				cur.r = nx;
				cur.c = ny;

			} // for end

		}
	}

}
