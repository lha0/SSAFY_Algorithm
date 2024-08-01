import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H;
	static int[][][] box;
	static Deque<int[]> needVisit = new ArrayDeque<>();
	
	static int[] dh = {1, -1, 0, 0, 0, 0};
	static int[] dn = {0, 0, 1, -1, 0, 0};
	static int[] dm = {0, 0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(sts.nextToken());
					
					if (box[i][j][k] == 1) {
						needVisit.add(new int[] {i, j, k, 1});
					}
				}
			}
		}
		
		bfs();
		
		int maxVal = -1;
		L: for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (box[i][j][k] == 0) {
						maxVal = 0;
						break L;
					}
					maxVal = Math.max(maxVal, box[i][j][k]);
				}
			}
		}
		
		
		bw.write(maxVal-1 + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void bfs() {
		while (needVisit.size() != 0) {
			int[] cur = needVisit.poll();
			int h = cur[0];
			int n = cur[1];
			int m = cur[2];
			int time = cur[3];
			
			for (int idx = 0; idx < 6; idx++) {
				int nh = h + dh[idx];
				int nn = n + dn[idx];
				int nm = m + dm[idx];
				
				//System.out.println("nh : " + nh + " nn : " + nn + " nm : " + nm);
				
				if (nh < 0 || nh >= H || nn < 0 || nn >= N || nm < 0 || nm >= M ||
						box[nh][nn][nm] != 0 || box[nh][nn][nm] == -1) {
					
					continue;
				} else {
					box[nh][nn][nm] = box[h][n][m] + 1;
					needVisit.add(new int[] {nh, nn, nm, time++});
				}
			}
		}
		
	}

}
