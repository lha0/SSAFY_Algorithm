import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static String S;
	static int[][] gameboard;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();

			gameboard = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					gameboard[i][j] = Integer.parseInt(sts.nextToken());
				}
			}
			
			boolean[][] visited;

			switch (S) {
			case "up":
				visited = new boolean[N][N];
				for (int col = 0; col < N; col++) {
					int row = 0;
					
					while (row < N-1) {
						int start = gameboard[row][col];
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == gameboard[row+1][col]) {
							// 내 자리에 합 더하기
							gameboard[row][col] = start * 2;
							
							//다음 자리는 0으로
							gameboard[row+1][col] = 0;
							
							visited[row][col] = true;
							visited[row+1][col] = true;
							
							row++;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = row+1; i < N; i++) {
								if (gameboard[i][col] != 0) {
									nextIdx = i;
									break;
								}
							}
							
							if (nextIdx == -1) {
								break;
							}
							
							int diff = nextIdx - row;
							
							for (int i = 0; i < diff; i++) {
								if (row+diff+i < N) {
									int temp = gameboard[row+i][col];
									gameboard[row+i][col] = gameboard[row+diff+i][col];
									gameboard[row+diff+i][col] = temp;
								}
								
							}
							
							
							if (row-1 >= 0 && visited[row][col] != true &&  gameboard[row][col] == gameboard[row-1][col]) {
								row--;
								continue;
							}
						}
						// 다르다면 그냥 pass 
						else {
							
							
							row++;
							continue;
						}
					}
				}
				break;
			case "right":
				visited = new boolean[N][N];
				for (int row = 0; row < N; row++) {
					int col = N-1;
					
					while (col > 0) {
						int start = gameboard[row][col];
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == gameboard[row][col-1]) {
							// 내 자리에 합 더하기
							gameboard[row][col] = start * 2;
							
							//다음 자리는 0으로
							gameboard[row][col-1] = 0;
							
							visited[row][col] = true;
							visited[row][col-1] = true;
							
							col--;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = col-1; i >= 0; i--) {
								if (gameboard[row][i] != 0) {
									nextIdx = i;
									break;
								}
							}
							
							if (nextIdx == -1) {
								break;
							}
							
							int diff = col - nextIdx;
							
							for (int i = 0; i < diff; i++) {
								if (col-i-diff >= 0) {
									int temp = gameboard[row][col-i];
									gameboard[row][col-i] = gameboard[row][col-i-diff];
									gameboard[row][col-i-diff] = temp;
								}
								
							}
							
							if (col+1 < N && visited[row][col] != true &&  gameboard[row][col] == gameboard[row][col+1]) {
								col++;
								continue;
							}
						}
						// 다르다면 그냥 pass 
						else {
							col--;
							continue;
						}
					}
				}
				break;
			case "down":
				visited = new boolean[N][N];
				for (int col = 0; col < N; col++) {
					int row = N-1;
					
					while (row > 0) {
						int start = gameboard[row][col];
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == gameboard[row-1][col]) {
							// 내 자리에 합 더하기
							gameboard[row][col] = start * 2;
							
							//다음 자리는 0으로
							gameboard[row-1][col] = 0;
							
							visited[row][col] = true;
							visited[row-1][col] = true;
							
							row--;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = row-1; i >= 0; i--) {
								if (gameboard[i][col] != 0) {
									nextIdx = i;
									break;
								}
							}
							
							if (nextIdx == -1) {
								break;
							}
							
							int diff = row - nextIdx;
							
							for (int i = 0; i < diff; i++) {
								if (row-i-diff >= 0) {
									int temp = gameboard[row-i][col];
									gameboard[row-i][col] = gameboard[row-i-diff][col];
									gameboard[row-i-diff][col] = temp;
								}
								
							}
							
							if (row+1 < N && visited[row][col] != true &&  gameboard[row][col] == gameboard[row+1][col]) {
								row++;
								continue;
							}
							
						}
						// 다르다면 그냥 pass 
						else {
							row--;
							continue;
						}
					}
				}
				break;
			case "left":
				visited = new boolean[N][N];
				for (int row = 0; row < N; row++) {
					int col = 0;
					
					while (col < N-1) {
						int start = gameboard[row][col];
						
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == gameboard[row][col+1]) {
							// 내 자리에 합 더하기
							gameboard[row][col] = start * 2;
							
							//다음 자리는 0으로
							gameboard[row][col+1] = 0;
							
							visited[row][col] = true;
							visited[row][col+1] = true;
							
							col++;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = col+1; i < N; i++) {
								if (gameboard[row][i] != 0) {
									nextIdx = i;
									break;
								}
							}
							
							if (nextIdx == -1) {
								break;
							}
							
							int diff = nextIdx - col;
							
							for (int i = 0; i < diff; i++) {
								if (col+i+diff < N) {
									int temp = gameboard[row][col+i];
									gameboard[row][col+i] = gameboard[row][col+i+diff];
									gameboard[row][col+i+diff] = temp;
								}
								
							}
							
							if (col-1 >= 0 && visited[row][col] != true &&  gameboard[row][col] == gameboard[row][col-1]) {
								col--;
								continue;
							}
						}
						// 다르다면 그냥 pass 
						else {
							col++;
							continue;
						}
					}
				}
				break;
			default:
				break;

			}
			
			bw.write("#" + tc + "\n");
			for (int i = 0; i < gameboard.length; i++) {
				for (int j = 0; j < gameboard[i].length; j++) {
					bw.write(gameboard[i][j] + " ");
				}
				bw.write("\n");
			}
		}

		
		bw.flush();
		bw.close();
		br.close();
	}
}
