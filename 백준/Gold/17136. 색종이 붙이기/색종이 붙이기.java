import java.io.*;
import java.util.*;

public class Main {
	static int[][] board = new int[10][10];
	static List<Node> paper;
	static int min = Integer.MAX_VALUE;
	
	static class Node {
		int s, e;
		
		Node(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		paper = new ArrayList<Node>();
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 
		boolean[][] visited = new boolean[10][10];
		//색종이 개수
		int[] amount = new int[] {0, 5, 5, 5, 5, 5};
		dfs(0, 0, 0, amount);
		
		if (min == Integer.MAX_VALUE) min = -1;
		
		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int x, int y, int cnt, int[] amount) {
		// 5사이즈부터 1까지 해당 위치를 붙일 수 있는지 확인
		// 붙일 수 있으면 붙이고, 방문처리 => 재귀
		// 없으면 다른 사이즈 확인
		// 오른쪽 / 아래 순으로 이동
		//print();
		
		if (x == 9 && y == 10) {
			min = Math.min(min, cnt);
			return;
		}
		
		if (y == 10) {
			dfs(x+1, 0, cnt, amount);
			return;
		}
		
		if (x == 10 && y == 0) return;
		
		if (cnt > min) return;
		
		if (board[x][y] != 1) {
			dfs(x, y+1, cnt, amount);
		} else {
			//큰 사이즈부터 확인
			for (int len = 5; len > 0; len--) {
				if (amount[len] > 0) {
					if (canAttach(x, y, len)) {
						attach(x, y, len);
						amount[len]--;
						dfs(x, y+1, cnt+1, amount);
						detach(x, y, len);
						amount[len]++;
						
					}
				}
			
			}
		}
	
	}

	private static void detach(int x, int y, int len) {
		for (int i = x; i < x+len; i++) {
			for (int j = y; j < y+len; j++) {
					board[i][j] = 1;				
			}
		}
	}

	private static void attach(int x, int y, int len) {
		for (int i = x; i < x+len; i++) {
			for (int j = y; j < y+len; j++) {
					board[i][j] = 3;				
			}
		}
		
	}

	private static boolean canAttach(int x, int y, int len) {
		
		for (int i = x; i < x+len; i++) {
			for (int j = y; j < y+len; j++) {
				if (i >= 10 || j >= 10 || board[i][j] != 1) return false;
			}
		}
		
		return true;
	}
	
	public static void print() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}

}
