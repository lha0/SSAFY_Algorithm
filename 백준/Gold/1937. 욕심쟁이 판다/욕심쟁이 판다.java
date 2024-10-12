
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] maps, mem;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int next = Integer.parseInt(st.nextToken());
				maps[i][j] = next;
			}
		}
		
		mem = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(mem[i], -1);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mem[i][j] == -1) {
					run(i, j);
					
					answer = Math.max(mem[i][j], answer);
					
//					System.out.println("i j " + i + " " + j);
//					for (int idx = 0; idx < N; idx++) {
//						System.out.println(Arrays.toString(mem[idx]));
//					}
//					System.out.println();
				}
				
			}
		}
		 
		bw.write(answer + "\n");
		
		br.close();
		bw.close();
	}

	private static int run(int x, int y) {
		if (mem[x][y] > -1) return mem[x][y];
		
		mem[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N 
					&& maps[nx][ny] > maps[x][y]) {
				if (run(nx, ny) >= mem[x][y])
					mem[x][y] = 1+ run(nx, ny);
			}
		}
		
		return mem[x][y];
	}

}
