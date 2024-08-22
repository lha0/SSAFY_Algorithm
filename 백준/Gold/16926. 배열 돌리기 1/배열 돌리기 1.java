import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(sts.nextToken());
			}
		}
		
		turn();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.write(arr[i][j] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void turn() {
//		R = R % ((N+M) * 2 - 4);
		
		for (int r = 0; r < R; r++) {
			int sx = 0;
			int sy = 0;
			int width = N;
			int height = M;
			int nx = 0;
			int ny = 0;
			
			//줄이기
			while (width > 1 && height > 1) {
				int initial = arr[nx][ny];
				
				//윗변
				int cnt = 0;
				while (cnt < height-1) {
					arr[nx][ny] = arr[nx][ny+1];
					ny++;
					cnt++;
				}
				
				//오른쪽
				cnt = 0;
				while (cnt < width-1) {
					arr[nx][ny] = arr[nx+1][ny];
					nx++;
					cnt++;
				}
				
				//아래
				cnt = 0;
				while (cnt < height-1) {
					arr[nx][ny] = arr[nx][ny-1];
					ny--;
					cnt++;
				}
				
				//왼쪽
				cnt = 0;
				while (cnt < width-2) {
					arr[nx][ny] = arr[nx-1][ny];
					nx--;
					cnt++;
				}
				
				arr[nx][ny] = initial;
				
				nx = sx+1;
				ny = sy+1;
				
				width = width -= 2;
				height = height -= 2;
				
				sx = nx;
				sy = ny;
				
//				System.out.println(nx + " " + ny + " " + width + " " + height);
				
//				System.out.println(r);
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(arr[i][j] + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println();
				
			}
			
			
		}
	}

}
