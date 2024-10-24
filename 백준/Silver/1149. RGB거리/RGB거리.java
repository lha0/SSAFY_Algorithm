import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] init, mem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		init = new int[3][N];
		mem = new int[3][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			init[0][i] = r;
			init[1][i] = g;
			init[2][i] = b;
		}
		
		run();
		
		int answer = mem[0][N-1];
		for (int i = 1; i < 3; i++) {
			answer = Math.min(answer, mem[i][N-1]);
		}
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}

	private static void run() {
		mem[0][0] = init[0][0];
		mem[1][0] = init[1][0];
		mem[2][0] = init[2][0];
		
		for (int home = 1; home < N; home++) {
			//R
			mem[0][home] = Math.min(init[0][home] + mem[1][home-1], init[0][home] + mem[2][home-1]);
			
			//G
			mem[1][home] = Math.min(init[1][home] + mem[0][home-1], init[1][home] + mem[2][home-1]);
			
			//B
			mem[2][home] = Math.min(init[2][home] + mem[0][home-1], init[2][home] + mem[1][home-1]);
			
		}
//		
//		for (int i = 0; i < 3; i++) {
//			System.out.println(Arrays.toString(mem[i]));
//		}
//		System.out.println();
	}

}
