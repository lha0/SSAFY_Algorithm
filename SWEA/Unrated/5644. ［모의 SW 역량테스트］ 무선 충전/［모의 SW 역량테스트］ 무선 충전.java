import java.io.*;
import java.util.*;

public class Solution {
	static int T, M, A, sameNetwork;
	static int[][] MAP = new int[11][11];
	static int[][] move;
	static List<BC> network, possible1, possible2;

	static User user1, user2;

	static class User {
		int x, y, charge;

		User(int x, int y, int charge) {
			this.x = x;
			this.y = y;
			this.charge = charge;
		}
	}

	static class BC implements Comparable<BC> {
		int num, x, y, c, p;

		BC(int num, int x, int y, int c, int p) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public int compareTo(BC o) {
			return o.p - this.p;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			move = new int[2][M];
			for (int i = 0; i < 2; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					move[i][j] = Integer.parseInt(sts.nextToken());
				}
			}

			network = new ArrayList<>();

			int bcNum = 1;
			for (int i = 0; i < A; i++) {
				StringTokenizer netIn = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(netIn.nextToken());
				int x = Integer.parseInt(netIn.nextToken());
				int c = Integer.parseInt(netIn.nextToken());
				int p = Integer.parseInt(netIn.nextToken());

				network.add(new BC(bcNum, x, y, c, p));

				bcNum++;
			}

			user1 = new User(1, 1, 0);
			user2 = new User(10, 10, 0);

			// 충전 시작
			run();

			int answer = user1.charge + user2.charge;

			bw.write("#" + tc + " " + answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {

		for (int m = -1; m < M; m++) {
			if (m != -1)
				userMove(m);

//			System.out.println("the result of move");
//			System.out.println("User 1 position ( " + user1.x + ", " + user1.y + ") charge : " + user1.charge);
//			System.out.println("User 2 position ( " + user2.x + ", " + user2.y + ") charge : " + user2.charge);
//
//			System.out.println("############## m : " + m + "##############");
			// 하나의 비콘마다 각 유저가 쓸 수 있는 경우를 모두 포함해두기
			possible1 = new ArrayList<BC>();
			possible2 = new ArrayList<BC>();

			// 현재 위치에서 충전
			// 유저들이 같은 위치에 있으면
			pos();
			
			Collections.sort(possible1);
			Collections.sort(possible2);

//			System.out.println("possible1");
//			for (int i = 0; i < possible1.size(); i++) {
//				System.out.println(possible1.get(i).num);
//
//			}
//
//			System.out.println("possible2");
//			for (int i = 0; i < possible2.size(); i++) {
//				System.out.println(possible2.get(i).num);
//				
//			}

			// 하나라도 같은 네트워크가 있는지 확인
			int minLen = 0;
			int maxLen = 0;
			if (possible1.size() < possible2.size()) {
				minLen = possible1.size();
				maxLen = possible2.size();
			} else {
				minLen = possible2.size();
				maxLen = possible1.size();
			}

			boolean isSame = false;
			boolean pass = false; // 하나라도 0개면 다음 if문 패스
			int sameIdx = 0;
			// 둘 다 하나도 없으며
			if (maxLen == 0) {
				pass = true;
			}
			// 하나라도 있으면
			else if (maxLen > 0 && minLen == 0) {
				if (possible1.size() != 0) {
					user1.charge += possible1.get(0).p;
					pass = true;

				} else {
					user2.charge += possible2.get(0).p;
					pass = true;

				}
			} else {
				// 같은 네트워크가 있어서 다음 if문 들어가야함
				for (int i = 0; i < minLen; i++) {
					if (possible1.get(i).num == possible2.get(i).num) {
						isSame = true;
						pass = false;
						sameIdx = i;
						break;
					}
				}
			}

			// 같은 네트워크가 있으면
			if (isSame && !pass) {
				// 둘의 크기가 똑같이 1이고, 첫번째 인덱스 값이 같으면, 나눠서 써야함
				if (possible1.size() == 1 && possible2.size() == 1 && possible1.get(0) == possible2.get(0)) {
					user1.charge += possible1.get(0).p / 2;
					user2.charge += possible2.get(0).p / 2;
				}

				else if (sameIdx == 0) {
					// 둘 중 하나의 사이즈가 1이면 , 1인 애가 0번째 파워를 사용
					if (minLen == 1 && possible1.size() < possible2.size()) {
						user1.charge += possible1.get(0).p;
						user2.charge += possible2.get(1).p;
					} else if (minLen == 1 && possible1.size() > possible2.size()) {
						user1.charge += possible1.get(1).p;
						user2.charge += possible2.get(0).p;
					} else {
						// 같은 네트워크 위치가 1이면, 두번째 값이 큰 애가 두번째꺼 쓰기
						if (possible1.get(1).p < possible2.get(1).p) {
							user1.charge += possible1.get(0).p;
							user2.charge += possible2.get(1).p;
						} else {
							user1.charge += possible1.get(1).p;
							user2.charge += possible2.get(0).p;
						}
					}
					
				}
				// 같은 네트워크 위치가 2가 넘으면 그냥 각자 첫번째꺼 쓰면 됨
				else {
					user1.charge += possible1.get(0).p;
					user2.charge += possible2.get(0).p;
				}
			}

			// 다른 네트워크 상에 있으면
			else if (!pass) {
				user1.charge += possible1.get(0).p;
				user2.charge += possible2.get(0).p;
			}

//			System.out.println("The result of Charge ");
//			System.out.println("User 1 charge : " + user1.charge);
//			System.out.println("User 2 charge : " + user2.charge);
//			System.out.println("-------------------------------------");
		}

	}

	private static void userMove(int time) {
		for (int i = 0; i < 2; i++) {
			switch (move[i][time]) {
			case 1:
				up(i);
				break;
			case 2:
				right(i);
				break;
			case 3:
				down(i);
				break;
			case 4:
				left(i);
				break;
			default:
				break;
			}
		}
	}

	private static void left(int userNum) {
		if (userNum == 0) {
			if (user1.y - 1 < 11) {
				user1.y -= 1;
			}
		} else {
			if (user2.y - 1 < 11) {
				user2.y -= 1;
			}
		}
	}

	private static void down(int userNum) {
		if (userNum == 0) {
			if (user1.x + 1 < 11) {
				user1.x += 1;
			}
		} else {
			if (user2.x + 1 < 11) {
				user2.x += 1;
			}
		}
	}

	private static void right(int userNum) {
		if (userNum == 0) {
			if (user1.y + 1 < 11) {
				user1.y += 1;
			}
		} else {
			if (user2.y + 1 < 11) {
				user2.y += 1;
			}
		}
	}

	private static void up(int userNum) {
		if (userNum == 0) {
			if (user1.x - 1 >= 0) {
				user1.x -= 1;
			}
		} else {
			if (user2.x - 1 >= 0) {
				user2.x -= 1;
			}
		}
	}

	private static void pos() {
		for (int a = 0; a < A; a++) {
			BC cur = network.get(a); // x, y, c, p

//			System.out.println("a : " + a);
//			System.out.println("user1 " + user1.x + " " + cur.x + " " + user1.y + " " + cur.y + " " + cur.c + " " + cur.p + "cur Num :" + cur.num);
//			System.out.println("user2 " + user2.x + " " + cur.x + " " + user2.y + " " + cur.y + " " + cur.c + " " + cur.p + "cur Num :" + cur.num);

			// 범위 내에 있으면, 리스트에 추가
			if (Math.abs(cur.x - user1.x) + Math.abs(cur.y - user1.y) <= cur.c)
				possible1.add(cur);
			if (Math.abs(cur.x - user2.x) + Math.abs(cur.y - user2.y) <= cur.c)
				possible2.add(cur);
		}

		return;
	}

	private static void print() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				System.out.print(MAP[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

}