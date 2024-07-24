//package hw;

import java.util.*;
import java.io.*;

public class Main {
	static int R, C, N;
	static String[][] map;
	static int[][] nums;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		nums = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			String[] inputs = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				map[r][c] = inputs[c];
			}
		}
		
		run(0);
		
		for(int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
		
		bw.write("" + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	
	public static void run(int time) {
		if (time == N) {
			return;
		}
		
		time++;
		
//		System.out.println("time " + time);
		
//		for(int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(map[i][j] + " ");
//				System.out.print(nums[i][j]  + " ");
//			}
//			System.out.println("");
//		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j].equals("O")) {
					nums[i][j] += 1;
				}
				
				if (time % 2 == 0 && map[i][j].equals(".")) {
					map[i][j] = "O";
					nums[i][j] = 0;
				}
			}
			
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (nums[r][c] == 3) {
					bomb(r, c);
					nums[r][c] = 0;
				}
			}
		}
		
//		for(int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(map[i][j]  + " " );
//				System.out.print(nums[i][j] + " " );
//			}
//			System.out.println("");
//		}
		
		run(time);
	}
	
	public static void bomb(int i, int j) {
		map[i][j] = ".";
		
		for (int n = 0; n < 4; n++) {
			int nx = i + dx[n];
			int ny = j + dy[n];
			
			if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
				continue;
			} else {
				map[nx][ny] = ".";
			}
		}
		
		
	}

}
