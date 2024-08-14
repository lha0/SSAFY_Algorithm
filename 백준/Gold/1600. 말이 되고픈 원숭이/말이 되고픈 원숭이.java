import java.io.*;
import java.util.*;

public class Main {
	static int K, W, H, answer;
	static int[][] world;
	static int[][][] visited;
	
	static int[] mx = {0, 0, 1, -1};
	static int[] my = {1, -1, 0, 0};
	static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] hy ={-2, -1, 1, 2, -2, -1, 1, 2 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputs = br.readLine().split(" ");
		K = Integer.parseInt(inputs[0]);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		world = new int[H][W];
		for (int i = 0; i < H; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				world[i][j] = Integer.parseInt(sts.nextToken());
			}
		}
		
		visited = new int[H][W][K+1];
		go(0, 0, K);
		
		int min = Integer.MAX_VALUE;
		for (int i =0 ; i < K+1; i++) {
			if (visited[H-1][W-1][i] != 0)
				min = Math.min(min, visited[H-1][W-1][i]-2);
		}
		
		if (min == Integer.MAX_VALUE) min = -1;
		
		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void go(int x, int y, int k) {
		Queue<int[]> needVisit = new ArrayDeque<int[]>();
		needVisit.offer(new int []{x, y, k, 2});
		world[x][y] = 2;
		visited[x][y][k] = 2;
		
		while (needVisit.size() != 0) {
			int[] cur = needVisit.poll();
//			
//			
//			print();
//			System.out.println(Arrays.toString(cur));
//			
			//말로 움직일 수 있으면
			if (cur[2] > 0) {
				for (int i = 0; i < 8; i++) {
					int nx = cur[0] + hx[i];
					int ny = cur[1] + hy[i];
					
					if (nx >= 0 && nx < H && ny >= 0 && ny < W
							&& visited[nx][ny][cur[2]-1] == 0 && world[nx][ny] != 1) {
						needVisit.offer(new int[] {nx, ny, cur[2]-1, cur[3]+1});
						visited[nx][ny][cur[2]-1] = visited[cur[0]][cur[1]][cur[2]] + 1;
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + mx[i];
				int ny = cur[1] + my[i];
				
				if (nx >= 0 && nx < H && ny >= 0 && ny < W
						&& visited[nx][ny][cur[2]] == 0 && world[nx][ny] != 1) {
					needVisit.offer(new int[] {nx, ny, cur[2], cur[3]+1});
					visited[nx][ny][cur[2]] = visited[cur[0]][cur[1]][cur[2]] + 1;
				}
			}
			
		}
	}
	
	public static void print() {
		System.out.println("########## print #############");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(Arrays.toString(visited[i][j]));
			}
			System.out.println();
		}
	}

}
