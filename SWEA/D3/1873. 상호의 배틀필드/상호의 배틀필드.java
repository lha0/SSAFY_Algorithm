import java.util.*;
import java.io.*;

public class Solution {
	static int T, H, W, N, longer;
	
	//위, 아래, 왼, 오
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		T = Integer.parseInt(input);
		
		for (int t = 1; t <= T; t++) {
			String[] HW = br.readLine().split(" ");
			H = Integer.parseInt(HW[0]);
			W = Integer.parseInt(HW[1]);
			
			longer = Math.max(H, W);
			
			String[][] map = new String[H][W];
			
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().split("");
			}
			
			N = Integer.parseInt(br.readLine());
			
			String[] command = new String[N];
			command = br.readLine().split("");
			
			int curX = 0;
			int curY = 0;
			L: for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j].equals("<") || map[i][j].equals(">") || map[i][j].equals("^") || map[i][j].equals("v")) {
						curX = i;
						curY = j;
						i = H;
						break L;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				String curSee = map[curX][curY];
				//System.out.println(command[i] + " " + curSee);
				
				switch(command[i]) {
				case "U":
					map[curX][curY] = "^";
					if (curX - 1 >= 0 && map[curX-1][curY].equals(".")) {
						map[curX-1][curY] = "^";
						map[curX][curY] = ".";
						curX = curX-1;
					}
					break;
					
				case "D":
					map[curX][curY] = "v";
					if (curX + 1 < H && map[curX+1][curY].equals(".")) {
						map[curX+1][curY] = "v";
						map[curX][curY] = ".";
						curX = curX+1;
					}
					break;
					
				case "L":
					map[curX][curY] = "<";
					if (curY - 1 >= 0 && map[curX][curY - 1].equals(".")) {
						map[curX][curY - 1] = "<";
						map[curX][curY] = ".";
						curY = curY - 1;
					}
					break;
					
				case "R":
					map[curX][curY] = ">";
					if (curY+1 < W && map[curX][curY+1].equals(".")) {
						map[curX][curY+1] = ">";
						map[curX][curY] = ".";
						curY = curY+1;
					}
					break;
					
				case "S":
					switch(curSee) {
					case "<":
						shoot(0, -1, curX, curY, map);
						break;
					case ">":
						shoot(0, 1, curX, curY, map);
						break;
					case "^":
						shoot(-1, 0, curX, curY, map);
						break;
					case "v":
						shoot(1, 0, curX, curY, map);
						break;
					}
					break;
				}
				
//				for (int x = 0; x < H; x++) {
//					for (int y = 0; y < W; y++) {
//						System.out.print(map[x][y]);
//					}
//					System.out.println();
//				}
//				System.out.println();
	
			
			}
			
			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
//			System.out.println();
		}
		

	}
	
	public static void shoot(int i, int j, int curX, int curY, String[][] map) {
		for (int d = 1; d <= longer; d++) {
			int nx = curX + i*d;
			int ny = curY + j*d;
			
			if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny].equals("#")) {
				break;
			} else if (map[nx][ny].equals("*")) {
				map[nx][ny] = ".";
				break;
			} else {
				continue;
			}
		}
		return;
	}

}
