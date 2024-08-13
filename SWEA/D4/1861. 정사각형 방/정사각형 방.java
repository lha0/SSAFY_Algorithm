import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N, cnt;
	static int[][] rooms;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().split(" ")[0]);
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			rooms = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] inputs = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			
			int max = 0;
			int roomNum = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					boolean[][] visited = new boolean[N][N];
					cnt = 1;
					dfs(i, j, visited);
					
					if (max < cnt) {
//						System.out.println("max " +max + " " + cnt);
						
						max = cnt;
//						System.out.println("room " + rooms[i][j] + " " + roomNum);
						
							roomNum = rooms[i][j];
						
					} 
					else if (max  == cnt) {
					   // System.out.println("room " + rooms[i][j] + " " + roomNum);
						if (rooms[i][j] < roomNum) {
							roomNum = rooms[i][j];
						}	
					} 
				}
			}
			bw.write("#" + tc + " " + roomNum + " " + max + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && rooms[nx][ny] == rooms[x][y] + 1) {
				dfs(nx, ny, visited);
				cnt++;
			}
		}
	}

}
