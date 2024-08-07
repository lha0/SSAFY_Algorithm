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

		// 다음 먹이, count
		sharkMove(eat, answer, fishbowl, position);

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static void sharkMove(Fish eat, int cnt, List<Fish> fishbowl, int[][] position) {
//		System.out.println("########## count : " + cnt + " ##############");
		// 현재 상어 정보
		Fish curShark = fishbowl.get(17);

		// 넘어온 물고기 먹기
		position[curShark.r][curShark.c] = 0;
		position[eat.r][eat.c] = curShark.no;
		
//		print(position);

		fishbowl.set(17, new Fish(curShark.no, eat.dir, eat.r, eat.c));
		fishbowl.set(eat.no, new Fish(eat.no, -10, -10, -10));

		curShark = fishbowl.get(17);
		int NextR = curShark.r + dx[curShark.dir % 8];
		int NextC = curShark.c + dy[curShark.dir % 8];

		if (NextR < 0 || NextR >= 4 || NextC < 0 || NextC >= 4) {
//			System.out.println("current answer is : " + answer);
			answer = Math.max(answer, cnt);
			return;
		}
		// 업데이트된 dir로 이동할 수 있으면 물고기들 이동 시키기
		else {
			for (Fish fish : fishbowl) {
//				System.out.println("cur Fish is " + fish.no + " " + fish.dir + " " + fish.r + " " + fish.c);
				if (fish.no == 0) continue;				
				// 이미 먹혀서 없는 물고기
				if (fish.dir == -10)
					continue;
				// 상어 나오면 물고기 이동 끝
				else if (fish.no == 17)
					break;
				// 방향 탐색
				for (int i = 0; i < 8; i++) {
					int ndir = (fish.dir + i) % 8;
					int nr = fish.r + dx[ndir];
					int nc = fish.c + dy[ndir];

//					System.out.println("next is " + fish.no + " " + ndir + " " + nr + " " + nc);
					//print(position);
					// 범위를 벗어나거나 상어가 있다면 continue
					if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || position[nr][nc] == 17) {
						continue;
					} else {
						//System.out.println("next fish is " + position[nr][nc]);
						// 빈칸이면 그 칸으로 이동
						if (position[nr][nc] == 0) {
							position[fish.r][fish.c] = 0;
							
							fish.r = nr;
							fish.c = nc;
							fish.dir = ndir;
							position[nr][nc] = fish.no;
							//print(position);
							//printFishbowl(fishbowl);
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

//			System.out.println("--------- end of fish move ---------");
			
			//print(position);
			
			//printFishbowl(fishbowl);

			// 이동 완료 후 다음 먹을 물고기 재귀 시작
			for (int i = 1; i < 4; i++) {
				int nr = curShark.r + dx[curShark.dir % 8] * i;
				int nc = curShark.c + dy[curShark.dir % 8] * i;

				if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || position[nr][nc] == 0) {
					continue;
				} else {
					Fish nextShark = fishbowl.get(position[nr][nc]);
					
					int[][] copyPos = new int[4][4];
					for (int l = 0; l < 4; l++) {
					    copyPos[l] = Arrays.copyOf(position[l], position[l].length);
					}
					
					List<Fish> copyFishBowl = new LinkedList<>();
					for (int c = 0; c < fishbowl.size(); c++) {
						Fish temp = fishbowl.get(c);
						copyFishBowl.add(c, new Fish(temp.no, temp.dir, temp.r, temp.c));
					}
					
					
//					System.out.println("~~~~~~~~ start of 재귀 ~~~~~~~~~~");
//					print(position);
					
					sharkMove(nextShark, cnt + nextShark.no, copyFishBowl, copyPos);
					
//					System.out.println("~~~~~~~~ end of 재귀 ~~~~~~~~~~");
//					print(position);
				}
			}
//			System.out.println("end of shark move and current answer is : " + answer);
			answer = Math.max(answer, cnt);
			return;
		}
	}

	public static void switchFish(int origin, int change, int ndir, int nr, int nc, List<Fish> fishbowl,
			int[][] position) {
		Fish originFish = fishbowl.get(origin);
		Fish switchFish = fishbowl.get(change);

//		System.out.println(
//				"origin Fish is " + originFish.no + " " + originFish.dir + " " + originFish.r + " " + originFish.c);
//		System.out.println(
//				"switch Fish is " + switchFish.no + " " + switchFish.dir + " " + switchFish.r + " " + switchFish.c);

		// position update
		position[switchFish.r][switchFish.c] = originFish.no;
		position[originFish.r][originFish.c] = switchFish.no;

		// fish.no = 1 <-> switchFish.no = 8
		// fish r, c 를 바꾸고, 넘버는 그대로, dir은 ndir로
		// switchFish는 r, c, 넘버는 그대로, dir도 그대로
		fishbowl.set(switchFish.no, new Fish(switchFish.no, switchFish.dir, originFish.r, originFish.c));
		fishbowl.set(originFish.no, new Fish(originFish.no, ndir, nr, nc));

		originFish = fishbowl.get(origin);
		switchFish = fishbowl.get(change);

//		System.out.println("&&&&&&&& after change &&&&&&&&&&&");
//
//		System.out.println(
//				"origin Fish is " + originFish.no + " " + originFish.dir + " " + originFish.r + " " + originFish.c);
//		System.out.println(
//				"switch Fish is " + switchFish.no + " " + switchFish.dir + " " + switchFish.r + " " + switchFish.c);
//
//		System.out.println("&&&&&&&& after change &&&&&&&&&&&");

	}

	public static void printFishbowl(List<Fish> fishbowl) {
		for (int i = 0; i < fishbowl.size(); i++) {
			System.out.println(
					fishbowl.get(i).no + " " + fishbowl.get(i).dir + " " + fishbowl.get(i).r + " " + fishbowl.get(i).c);
		}
		System.out.println("--------------------------------");
	}

	public static void print(int[][] position) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(position[i][j] + " ");
			}
			System.out.println();

		}
		System.out.println("--------------------------------");
	}

}
