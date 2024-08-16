import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M, ans;
	static int[][] ingrediant;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			
			ingrediant = new int[M][2];
			for (int i = 0; i < M; i++) {
				String[] abs = br.readLine().split(" ");
				for (int j = 0; j < 2; j++) {
					ingrediant[i][j] = Integer.parseInt(abs[j]) - 1;
				}
			}
			
			boolean[] sel = new boolean[N];
			ans = 0;
			comb(0, sel);
			
			bw.write("#" + tc + " " + ans+ "\n");
		}
		
		
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb(int idx, boolean[] sel) {
		
		if (idx == sel.length) {
			if (check(sel)) {
				ans++;
			}
			return;
		}
		
		sel[idx] = true;
		comb(idx+1, sel);
		sel[idx] = false;
		comb(idx+1, sel);
	}

	private static boolean check(boolean[] sel) {
		for (int i = 0; i < ingrediant.length; i++) {
			int[] cur = ingrediant[i];
			
			if (sel[cur[0]] && sel[cur[1]]) {
				return false;
			}
		}
		return true;
	}

}
