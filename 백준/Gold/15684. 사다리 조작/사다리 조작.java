import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H, answer;
	static boolean[][] ladder;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new boolean[H+1][N+1];
		for (int i = 0; i < M; i++) {
			 st = new StringTokenizer(br.readLine());
			 int start = Integer.parseInt(st.nextToken());
			 int end = Integer.parseInt(st.nextToken());
			 
			 ladder[start][end] = true;
		}
		
		answer = Integer.MAX_VALUE;
		
		//설치 안했을 때 통과하는지 확인
		if (check()) answer = 0;
		else {
			run(0, 3, 1);
		}
		
		
		if (answer == Integer.MAX_VALUE) bw.write(-1 + "\n");
		else bw.write(answer + "\n");
		br.close();
		bw.close();
	}

	private static void run(int install, int maxLadder, int startCol) {
		
		if (install > maxLadder || answer <= install) {
			return;
		}
		
		if (check()) {
			answer = Math.min(answer, install);
			return;
		}
		
		for (int i = startCol; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				//현위치, 좌, 우가 다 false일 경우만 설치 가능
				if (ladder[i][j] == false 
						&& ladder[i][j-1] == false 
						&& j+1 <= N && ladder[i][j+1] == false) {
					ladder[i][j] = true;
					run(install+1, maxLadder, i);
					ladder[i][j] = false;
				}
			}
		}
		
	}

	private static boolean check() {
		// 출발점
		for (int start = 1; start <= N; start++) {
			int col = start;
			int row = 1;
			
			while (row <= H && col >= 1 && col <= N) {
				if (ladder[row][col] == true) {
					col++;
				}
				
				else if (col-1 > 0 && ladder[row][col-1] == true) {
					col--;
				}
				
				row++;
				
			}
			
			if (col != start) return false;
			
		}
		return true;
	}

}
