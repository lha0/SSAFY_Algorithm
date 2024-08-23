import java.util.*;
import java.io.*;

public class Solution {
	static int T, H, W, N;
	static String[][] board;
	static String[] command;
	static Tank tank;

	static class Tank {
		int r, c;
		String dir;

		Tank(int r, int c, String dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String[] inputs = br.readLine().split(" ");
			H = Integer.parseInt(inputs[0]);
			W = Integer.parseInt(inputs[1]);

			board = new String[H][W];

			for (int i = 0; i < H; i++) {
				String[] input = br.readLine().split("");
				for (int j = 0; j < W; j++) {
					board[i][j] = input[j];

					if (board[i][j].equals("<")) {
						tank = new Tank(i, j, "<");
					} else if (board[i][j].equals(">")) {
						tank = new Tank(i, j, ">");
					} else if (board[i][j].equals("^")) {
						tank = new Tank(i, j, "^");
					} else if (board[i][j].equals("v")) {
						tank = new Tank(i, j, "v");
					}
				}
			}

			N = Integer.parseInt(br.readLine().split(" ")[0]);
			command = new String[N];
			String[] inputc = br.readLine().split("");
			for (int i = 0; i < N; i++) {
				command[i] = inputc[i];
			}
			

			run();
			
			bw.write("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					bw.write(board[i][j]);
				}
				bw.write("\n");
			}

			
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {
		for (int c = 0; c < command.length; c++) {
			switch (command[c]) {
			case "U":
				up();
				break;
			case "D":
				down();
				break;
			case "L":
				left();
				break;
			case "R":
				right();
				break;
			case "S":
				shoot();
				break;
			default: break;
			}
			
		}
	}
	
	
	private static void shoot() {
		int dx = 0; int dy = 0;
		int mult = 1;
		
		while (true) {
			switch(tank.dir) {
			case ">":
				dx = 0;
				dy = 1;
				break;
			case "<":
				dx = 0;
				dy = -1;
				break;
			case "^":
				dx = -1;
				dy = 0;
				break;
			case "v":
				dx = 1;
				dy = 0;
				break;
			}
			
			int nx = tank.r + dx * mult;
			int ny = tank.c + dy * mult;
			
			
			if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
				if (board[nx][ny].equals("#")) break;
				else if (board[nx][ny].equals("*")) {
					board[nx][ny] = ".";
					mult++;
					break;
				} else {
					mult++;
					continue;
				}
			} else break;
			
		}
	}

	private static void right() {
		tank.dir = ">";
		board[tank.r][tank.c] = ">";

		// 평지면 이동
		if (tank.c + 1 < W && board[tank.r][tank.c + 1].equals(".")) {
			 board[tank.r][tank.c + 1] = ">";
			board[tank.r][tank.c] = ".";
			tank.c += 1;
		}
	}

	private static void left() {
		tank.dir = "<";
		board[tank.r][tank.c] = "<";

		// 평지면 이동
		if (tank.c - 1 >= 0 && board[tank.r][tank.c - 1].equals(".")) {
			 board[tank.r][tank.c - 1] = "<";
			board[tank.r][tank.c] = ".";
			tank.c -= 1;
		}
	}

	private static void down() {
		tank.dir = "v";
		board[tank.r][tank.c] = "v";

		// 평지면 이동
		if (tank.r + 1 < H && board[tank.r + 1][tank.c].equals(".")) {
			board[tank.r + 1][tank.c] = "v";
			board[tank.r][tank.c] = ".";
			tank.r += 1;
		}
		
	}

	private static void up() {
		tank.dir = "^";
		board[tank.r][tank.c] = "^";

		// 평지면 이동
		if (tank.r - 1 >= 0 && board[tank.r - 1][tank.c].equals(".")) {
			board[tank.r - 1][tank.c] = "^";
			board[tank.r][tank.c] = ".";
			tank.r -= 1;
		}
	}

}
