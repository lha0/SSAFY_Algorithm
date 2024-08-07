import java.io.*;
import java.util.*;

public class Main {
	static class Fish {
		int no, dir, r, c;

		public Fish(int no, int dir, int r, int c) {
			this.no = no;
			this.dir = dir;
			this.r = r;
			this.c = c;
		}

	}

	// 반시계 이동 = (인덱스 + 1) % 8
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		List<Fish> fishbowl = new LinkedList<>();
		int[][] position = new int[4][4];

		Fish eat = new Fish(0, 0, 0, 0);
		fishbowl.add(eat);

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j += 2) {
				int fish = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				// dir -1 : 인덱스 0부터
				Fish fishOne = new Fish(fish, dir - 1, i, j / 2);
				fishbowl.add(fishOne);

				// 현재 물고기 위치들 저장
				position[i][j / 2] = fish;

				// 번호순으로 정렬
				Collections.sort(fishbowl, (o1, o2) -> o1.no - o2.no);

				// (0, 0)에 있는 상어는 먹고 시작
				if (i == 0 && j == 0) {
					eat = fishbowl.get(1);
					fishbowl.add(new Fish(17, eat.dir, 0, 0));
					answer = eat.no;
				}
			}
		}

		// DFS 시작
		sharkMove(eat, answer, fishbowl, position);

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static void sharkMove(Fish eat, int cnt, List<Fish> fishbowl, int[][] position) {
		// 현재 상어 정보
		Fish curShark = fishbowl.get(17);

		// 넘어온 물고기 먹기
		position[curShark.r][curShark.c] = 0;
		position[eat.r][eat.c] = curShark.no;

		fishbowl.set(17, new Fish(curShark.no, eat.dir, eat.r, eat.c));
		fishbowl.set(eat.no, new Fish(eat.no, -10, -10, -10));

		curShark = fishbowl.get(17);
		int NextR = curShark.r + dx[curShark.dir % 8];
		int NextC = curShark.c + dy[curShark.dir % 8];

		// 업데이트된 dir로 상어가 이동할 수 없으면 재귀 종료
		if (NextR < 0 || NextR >= 4 || NextC < 0 || NextC >= 4) {
			answer = Math.max(answer, cnt);
			return;
		}
		// 업데이트된 dir로 이동할 수 있으면 물고기들 이동 시키기
		else {
			// 1번부터 물고기 이동 시작
			for (Fish fish : fishbowl) {
				// idx 0은 제외
				if (fish.no == 0)
					continue;
				// 이미 먹혀서 없는 물고기
				if (fish.dir == -10)
					continue;
				// 상어 나오면 물고기 이동 끝
				else if (fish.no == 17)
					break;
				// 이동 가능하면 방향 탐색
				for (int i = 0; i < 8; i++) {
					int ndir = (fish.dir + i) % 8;
					int nr = fish.r + dx[ndir];
					int nc = fish.c + dy[ndir];

					// 범위를 벗어나거나 상어가 있다면 45도 회전
					if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || position[nr][nc] == 17) {
						continue;
					} else {
						// 빈칸이면 그 칸으로 이동
						if (position[nr][nc] == 0) {
							position[fish.r][fish.c] = 0;

							fish.r = nr;
							fish.c = nc;
							fish.dir = ndir;
							position[nr][nc] = fish.no;
							break; // 다음 물고기로
						}

						// 다른 물고기가 있는 칸이면 switch
						else if (position[nr][nc] > 0) {
							switchFish(fish.no, position[nr][nc], ndir, nr, nc, fishbowl, position);
							break; // 다음 물고기로
						}

					}
				}
			} // for loop end

			// 이동 완료 후 다음 먹을 물고기 재귀 시작
			for (int i = 1; i < 4; i++) {
				int nr = curShark.r + dx[curShark.dir % 8] * i;
				int nc = curShark.c + dy[curShark.dir % 8] * i;

				if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || position[nr][nc] == 0) {
					continue;
				} else {
					Fish nextShark = fishbowl.get(position[nr][nc]);

					// position 깊은 복사
					int[][] copyPos = new int[4][4];
					for (int l = 0; l < 4; l++) {
						copyPos[l] = Arrays.copyOf(position[l], position[l].length);
					}
					
					 // fishbowl 깊은 복사
					List<Fish> copyFishBowl = new LinkedList<>();
					for (int c = 0; c < fishbowl.size(); c++) {
						Fish temp = fishbowl.get(c);
						copyFishBowl.add(c, new Fish(temp.no, temp.dir, temp.r, temp.c));
					}

					// 다음 위치로 재귀 시작
					sharkMove(nextShark, cnt + nextShark.no, copyFishBowl, copyPos);
				}
			}
			// 재귀 못 넘어갔다면 상어가 이동 못 하는 경우 > 답 갱신
			answer = Math.max(answer, cnt);
			return;
		}
	}

	public static void switchFish(int origin, int change, int ndir, int nr, int nc, List<Fish> fishbowl,
			int[][] position) {
		Fish originFish = fishbowl.get(origin);
		Fish switchFish = fishbowl.get(change);

		// position update
		position[switchFish.r][switchFish.c] = originFish.no;
		position[originFish.r][originFish.c] = switchFish.no;

		// fish r, c 를 바꾸고, 넘버는 그대로, dir은 ndir로
		// switchFish는 r, c, 넘버는 그대로, dir도 그대로
		fishbowl.set(switchFish.no, new Fish(switchFish.no, switchFish.dir, originFish.r, originFish.c));
		fishbowl.set(originFish.no, new Fish(originFish.no, ndir, nr, nc));
	}

}
