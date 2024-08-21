import java.io.*;
import java.util.*;

public class Main {
	static int N, max;
	static int[][] gameboard;
	
	//상하좌우
	// 0, 1, 2, 3
	static int[] direction = {0, 1, 2, 3};
	static String[] go = {"up", "right", "down", "left"};
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);
		
		gameboard = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				gameboard[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		comb(0, new int[5]);
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static void comb(int k, int[] sel) {
		// 방향 {0, 1, 2, 3} 중에 최대 5개 
		if (k == sel.length) {
			
			int[][] copyGameBoard = new int[N][N];
			for (int i = 0; i < gameboard.length; i++) {
				for (int j = 0; j < gameboard[i].length; j++) {
					copyGameBoard[i][j] = gameboard[i][j];
				}
			}
			
			game(sel, copyGameBoard);
			
			for (int i = 0; i < copyGameBoard.length; i++) {
				for (int j = 0; j < copyGameBoard[i].length; j++) {
					max = Math.max(max, copyGameBoard[i][j]);
				}
			}

			return;
		}
		
		for (int i = 0; i < 4; i++) {
			sel[k] = direction[i];
			comb(k+1, sel);
		}
	}

	private static void game(int[] sel, int[][] copyGameBoard) {
		for (int idx = 0; idx < sel.length; idx++) {
			String curDir = go[sel[idx]];
			
			boolean[][] visited;
			switch (curDir) {
			case "up":
				visited = new boolean[N][N];
				for (int col = 0; col < N; col++) {
					int row = 0;
					
					while (row < N-1) {
						int start = copyGameBoard[row][col];
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == copyGameBoard[row+1][col]) {
							// 내 자리에 합 더하기
							copyGameBoard[row][col] = start * 2;
							
							//다음 자리는 0으로
							copyGameBoard[row+1][col] = 0;
							
							visited[row][col] = true;
							visited[row+1][col] = true;
							
							row++;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = row+1; i < N; i++) {
								if (copyGameBoard[i][col] != 0) {
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
									int temp = copyGameBoard[row+i][col];
									copyGameBoard[row+i][col] = copyGameBoard[row+diff+i][col];
									copyGameBoard[row+diff+i][col] = temp;
								}
								
							}
							
							
							if (row-1 >= 0 && visited[row][col] != true &&  copyGameBoard[row][col] == copyGameBoard[row-1][col]) {
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
						int start = copyGameBoard[row][col];
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == copyGameBoard[row][col-1]) {
							// 내 자리에 합 더하기
							copyGameBoard[row][col] = start * 2;
							
							//다음 자리는 0으로
							copyGameBoard[row][col-1] = 0;
							
							visited[row][col] = true;
							visited[row][col-1] = true;
							
							col--;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = col-1; i >= 0; i--) {
								if (copyGameBoard[row][i] != 0) {
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
									int temp = copyGameBoard[row][col-i];
									copyGameBoard[row][col-i] = copyGameBoard[row][col-i-diff];
									copyGameBoard[row][col-i-diff] = temp;
								}
								
							}
							
							if (col+1 < N && visited[row][col] != true &&  copyGameBoard[row][col] == copyGameBoard[row][col+1]) {
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
						int start = copyGameBoard[row][col];
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == copyGameBoard[row-1][col]) {
							// 내 자리에 합 더하기
							copyGameBoard[row][col] = start * 2;
							
							//다음 자리는 0으로
							copyGameBoard[row-1][col] = 0;
							
							visited[row][col] = true;
							visited[row-1][col] = true;
							
							row--;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = row-1; i >= 0; i--) {
								if (copyGameBoard[i][col] != 0) {
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
									int temp = copyGameBoard[row-i][col];
									copyGameBoard[row-i][col] = copyGameBoard[row-i-diff][col];
									copyGameBoard[row-i-diff][col] = temp;
								}
								
							}
							
							if (row+1 < N && visited[row][col] != true &&  copyGameBoard[row][col] == copyGameBoard[row+1][col]) {
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
						int start = copyGameBoard[row][col];
						
						// 다음값이 나랑 같다면, 합치기
						if (start != 0 && start == copyGameBoard[row][col+1]) {
							// 내 자리에 합 더하기
							copyGameBoard[row][col] = start * 2;
							
							//다음 자리는 0으로
							copyGameBoard[row][col+1] = 0;
							
							visited[row][col] = true;
							visited[row][col+1] = true;
							
							col++;
						}
						//내 자리가 만약 0이라면, 땡겨 올리기
						else if (start == 0) {
							int nextIdx = -1;
							for (int i = col+1; i < N; i++) {
								if (copyGameBoard[row][i] != 0) {
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
									int temp = copyGameBoard[row][col+i];
									copyGameBoard[row][col+i] = copyGameBoard[row][col+i+diff];
									copyGameBoard[row][col+i+diff] = temp;
								}
								
							}
							
							if (col-1 >= 0 && visited[row][col] != true &&  copyGameBoard[row][col] == copyGameBoard[row][col-1]) {
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
		}
	}
	
	public static void print(int[][] copyGameBoard) {
		for (int i = 0 ; i < copyGameBoard.length; i++) {
			System.out.println(Arrays.toString(copyGameBoard[i]));
		}
		System.out.println();
	}

}
