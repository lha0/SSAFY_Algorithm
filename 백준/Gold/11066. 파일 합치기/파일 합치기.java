import java.io.*;
import java.util.*;

public class Main {
	
	static int T, K;
	static int[] sum;
	static int[][] mem;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			sum = new int[K+1];
			mem = new int[K+1][K+1];
			for (int i = 1; i <= K; i++) {
				sum[i] = Integer.parseInt(st.nextToken()) + sum[i-1];
			}
			
			run();
			
			bw.write(mem[1][K] + "\n");
		}
		
		br.close();
		bw.close();
 	}

	private static void run() {
		for (int i = 1; i < K; i++) { //구해야하는 범위
//			System.out.println(" ##### i " + i);
			
			for (int x = 1; x <= K - i; x++) { //시작 부분
				int y = x + i; //마지막 부분
				mem[x][y] = Integer.MAX_VALUE;
				
//				System.out.println(" #### x " + x + " , y " + y);
				
				for (int mid = x; mid < y; mid++) { //구해야 하는 범위를 2부분으로 쪼갬
//					System.out.println(" ## mid " + mid);
					mem[x][y] = Math.min(mem[x][y], mem[x][mid] + mem[mid+1][y] + sum[y] - sum[x-1]);
				}
				
//				for (int r = 0; r < mem.length; r++) {
//					System.out.println(Arrays.toString(mem[r]));
//				}
			}
			
		}
	}

}
