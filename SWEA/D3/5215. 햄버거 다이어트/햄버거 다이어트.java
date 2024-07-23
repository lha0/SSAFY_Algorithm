import java.util.*;
import java.io.*;

public class Solution {
	static int T, N, L;
	static int max; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		T = Integer.parseInt(input[0]);
		
		for (int t = 1; t <= T; t++) {
			String[] inputN = br.readLine().split(" ");
			N = Integer.parseInt(inputN[0]);
			L = Integer.parseInt(inputN[1]);
			max = 0;
			
			int[] Tarr = new int[N];
			int[] Karr = new int[N];
			for (int n = 0; n < N; n++) {
				String[] gr = br.readLine().split(" ");
				Tarr[n] = Integer.parseInt(gr[0]);
				Karr[n] = Integer.parseInt(gr[1]);
			}
			
			
			for (int i = 1; i <= N; i++ ) {
				int[] selT = new int[i];
				int[] selK = new int[i];
				recursive(0, 0, Tarr, Karr, selT, selK);
			}
			
			bw.write("#" + t + " " + max + '\n');
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void recursive(int n, int r, int[] Tarr, int[] Karr, int[] selT, int[] selK) {
		if (r == selK.length) {
			test(Tarr, Karr,selT, selK);
			//System.out.println(Arrays.toString(selK));
			return;
		}
		
		if (n == N) {
			return;
		}
		
		selK[r] = Karr[n];
		selT[r] = Tarr[n];
		recursive(n+1, r+1, Tarr, Karr, selT, selK);
		recursive(n+1, r, Tarr, Karr, selT, selK);
	}
	
	public static void test(int[] Tarr, int[] Karr, int[] selT, int[] selK) {
		int sumK = 0;
		int sumT = 0;
		for (int i = 0; i < selK.length; i++) {
			sumK += selK[i];
			sumT += selT[i];
		}
		
		if (sumK > L) return;
		else {
//			System.out.println(Arrays.toString(selK));
//			System.out.println(Arrays.toString(selT));
//			System.out.println(max + " " + sumT);
			max = Math.max(max, sumT);
		}
	}

}
