import java.io.*;
import java.util.*;

/*
선수들 순서를 순열로 만들기
각 순열 만들 때마다 점수 계산
-> 아웃 개수, 1루, 2루, 3루 타자 여부, 
 * */

public class Main {
	static int ining;
	static int[] player = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
	static int[][] score;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		ining = Integer.parseInt(input[0]);
		
		score = new int[ining][9];
		for (int i = 0; i < ining; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		
		}
		int[] sel = new int[9];
		boolean[] visited = new boolean[9];
		game(0, sel, visited);
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static void game(int r, int[] sel, boolean[] visited) {
		if (r == 9) {
			if (sel[3] != 0) {
				return;
			} else {
//				System.out.println("player order is s"+Arrays.toString(sel));
				cal(sel);
				return;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sel[r] = player[i];
				game(r+1, sel, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void cal(int[] sel) {
		int idx = 0;
		int curScore = 0;
		for (int in = 0; in < ining; in++) {
			int out = 0;
			int[] base = new int[3];
			
			while (out != 3) {
				int curPlayer = sel[idx];
//				
//				System.out.println("current Player is " + curPlayer + " " + score[in][curPlayer]);
//				System.out.println(Arrays.toString(score[in]));
				
				switch(score[in][curPlayer]) {
				//case 1, 2, 3:
				//java 8
				case 1: case 2: case 3:
					curScore += move(base, score[in][curPlayer]);
					break;
			
				case 4: 
//					System.out.println("homerun before " + base + " " + curScore);
					//현재 주자 +1
					curScore++;
					// base에 있는 선수들 +1
					for (int i = 0; i < base.length; i++) {
						if (base[i]==1) {
							base[i] = 0;
							curScore++;
						}
					}
//					System.out.println("homerun after " + base + " " + curScore);
					break;
				
				default:
					out++;
					break;
				}
				idx = (idx + 1) % 9;
				 
//				System.out.println("ining is " + in + " base is " + Arrays.toString(base) + " curScore is " + curScore);
				
			}
			
		}
		max = Math.max(max, curScore);
	
	}
	
	public static int move(int[] base, int num) {
		int baseScore = 0;
		for (int i = base.length-1; i >= 0; i--) {
			if (base[i] == 1) {
				if (i + num < 3) {
					base[i + num] = 1;
					base[i] = 0;
				} else {
					baseScore++;
					base[i] = 0;
				}
			}
		}
		base[num-1] = 1;
		return baseScore;
	}

}
