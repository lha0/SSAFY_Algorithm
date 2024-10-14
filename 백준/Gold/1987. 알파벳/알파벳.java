
import java.util.*;
import java.io.*;

public class Main {
	static int R, C, answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static String[][] maps;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R =Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maps = new String[R][C];
		for (int r = 0; r < R; r++) {
			String[] str = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				maps[r][c] = str[c];
			}
		}
		
		boolean[][] visited = new boolean[R][C];
		visited[0][0] = true;
		
		run(0, 0, maps[0][0], visited);			

		
		bw.write(answer + "\n");
		br.close();
		bw.close();
		
	}

	private static void run(int x, int y, String str, boolean[][] visited) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny]) {
				if (!str.contains(maps[nx][ny])) {
					str += maps[nx][ny];
					visited[nx][ny] = true;
					run(nx, ny, str, visited);
					visited[nx][ny] = false;
					str = str.substring(0, str.length() - 1);
					
				}
			}
		}
		
		answer = Math.max(answer, str.length());
	}
}
